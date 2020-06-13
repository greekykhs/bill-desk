package com.ca.billdesk.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.TransactionAuditTrail;
import com.ca.billdesk.model.TransactionDetails;
import com.ca.billdesk.model.TransactionRequest;
import com.ca.billdesk.model.TransactionResponse;
import com.ca.billdesk.repository.TransactionAuditTrailRepository;
import com.ca.billdesk.repository.TransactionRepository;
import com.ca.billdesk.util.JWTUtil;

import io.jsonwebtoken.SignatureException;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger logger = LogManager.getLogger();
	@Autowired
	@Lazy
	JWTUtil jwtUtil;
	
	@Autowired
	@Lazy
	TransactionRepository transactionRepository;
	
	@Autowired
	@Lazy
	TransactionAuditTrailRepository transactionAuditTrailRepository;
	
	@Autowired
	@Lazy
	PaymentService paymentService;
	
	@Override
	@Transactional
	public TransactionResponse doTransaction(TransactionRequest transactionRequest, String token) {
		TransactionResponse transactionresponse=new TransactionResponse();
		boolean isJwtValid=true;
		//TODO validations
		
		//either user need to login or enter mobile no. for quick bill pay
		if((token==null || "".equals(token)) && transactionRequest.getMobileNo()==null) {
			isJwtValid=false;
			transactionresponse.setApplicationCode(Constants.Responses.INVALID_TXN.getErrorCode());
			transactionresponse.setApplicationResponse(Constants.Responses.INVALID_TXN.getErrorMessage());
		}
		else if(token!=null && !"".equals(token)){			
			//check if jwt token is not tempered and not expired
			try {
				//this will thrown SignatureException if jwt is not valid
				isJwtValid=jwtUtil.validateToken(token);
				if(!isJwtValid) {
					transactionresponse.setApplicationCode(Constants.Responses.SESSION_EXPIRED.getErrorCode());
					transactionresponse.setApplicationResponse(Constants.Responses.SESSION_EXPIRED.getErrorMessage());
				}	
			}
			catch(SignatureException e)
			{
				isJwtValid=false;
				transactionresponse.setApplicationCode(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorCode());
				transactionresponse.setApplicationResponse(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorMessage());
				logger.error("*****TransactionService: inside doTransaction(). Invalid JWT Token received.");
			}
		}
		if(isJwtValid) {
			//TODO check if the user is present and is active, if yes then proceed else throw an error
			TransactionDetails transactionDetails=TransactionDetails.getTxnDtlsFromRequest(transactionRequest);
			transactionDetails.setTxnStatus(Constants.TXN_STATUS_INPROGRESS);
			TransactionDetails savedTransactionDetails=null;
			try {
				savedTransactionDetails=insertTxnDetails(transactionDetails);
			}
			catch(Exception e) {
				//TODO in case of exception, we can retry
				transactionresponse.setApplicationCode(Constants.Responses.PAYMENT_ERROR.getErrorCode());
				transactionresponse.setApplicationResponse(Constants.Responses.PAYMENT_ERROR.getErrorMessage());
				
				logger.error("*****TransactionService: inside doTransaction(). Error occurred while inserting the details in DB.");
			}
			
			if(savedTransactionDetails!=null) {
				transactionresponse.setTxnId(savedTransactionDetails.getTxnId());
				boolean isPaymentSuccesful=false;
				try {
					//call the payment webservice
					isPaymentSuccesful=paymentService.doTransaction(savedTransactionDetails);
				}
				catch(Exception e) {
					transactionresponse.setApplicationCode(Constants.Responses.PAYMENT_ERROR.getErrorCode());
					transactionresponse.setApplicationResponse(Constants.Responses.PAYMENT_ERROR.getErrorMessage());
					
					logger.error("*****TransactionService: inside doTransaction(). Error occurred while payment for transaction id:"+savedTransactionDetails.getTxnId());
				}
				if(isPaymentSuccesful)				
					savedTransactionDetails.setTxnStatus(Constants.TXN_STATUS_COMPLETED);				
				else
					savedTransactionDetails.setTxnStatus(Constants.TXN_STATUS_REJECTED);
				
				try {
					updateTxnDetails(savedTransactionDetails);
				}
				catch(Exception e) {
					//TODO in case of exception, we can retry or write a cron which we fetch updated status 
					//from the third party payment webserice and update the details in our DB.
					logger.error("*****TransactionService: inside doTransaction(). Error occurred while updating the status for transaction id:"+savedTransactionDetails.getTxnId());
				}
				
				transactionresponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
				transactionresponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());				
			}	
		}
		return transactionresponse;
	}
	
	@Transactional
	public TransactionDetails insertTxnDetails(TransactionDetails transactionDetails) {
		//insert the details in DB with the transaction status as IN PROGRESS			
		TransactionDetails savedTransactionDetails=transactionRepository.save(transactionDetails);
		
		//insert the details in audit trail table
		TransactionAuditTrail transactionAuditTrail=new TransactionAuditTrail(savedTransactionDetails);
		transactionAuditTrailRepository.save(transactionAuditTrail);
		
		return savedTransactionDetails;
	}
	
	@Transactional
	public void updateTxnDetails(TransactionDetails savedTransactionDetails) {
		//update the transaction status in DB
		transactionRepository.updateTxnStatus(savedTransactionDetails.getTxnId(), savedTransactionDetails.getTxnStatus());
				
		//insert the details in audit trail table
		TransactionAuditTrail transactionAuditTrail=new TransactionAuditTrail(savedTransactionDetails);
		transactionAuditTrailRepository.save(transactionAuditTrail);
	}
}
