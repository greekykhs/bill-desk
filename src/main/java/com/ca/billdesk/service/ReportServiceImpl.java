package com.ca.billdesk.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.LoginRequest;
import com.ca.billdesk.model.ReportRequest;
import com.ca.billdesk.model.ReportResponse;
import com.ca.billdesk.model.TransactionDetails;
import com.ca.billdesk.repository.TransactionRepository;
import com.ca.billdesk.repository.UserRepository;
import com.ca.billdesk.util.JWTUtil;

import io.jsonwebtoken.SignatureException;
@Service
public class ReportServiceImpl implements ReportService{
	public static final Logger logger = LogManager.getLogger();
	@Autowired
	@Lazy
	JWTUtil jwtUtil;
	
	@Autowired
	@Lazy
	TransactionRepository transactionRepository;
	
	@Autowired
	@Lazy
	UserRepository userRepository;
	
	@Override
	public ReportResponse generateReport(ReportRequest reportRequest, String token) {
		ReportResponse reportResponse=new ReportResponse();
		// TODO validations
		
		//Validate if the JWT token is valid
		// either user need to login or enter mobile no. for quick bill pay
		if (token == null || "".equals(token)) {
			reportResponse.setApplicationCode(Constants.Responses.LOGIN_AGAIN.getErrorCode());
			reportResponse.setApplicationResponse(Constants.Responses.LOGIN_AGAIN.getErrorMessage());
		}
		else {
			boolean isJwtValid=true;
			//check if jwt token is not tempered and not expired
			try {
				//this will thrown SignatureException if jwt is not valid
				isJwtValid=jwtUtil.validateToken(token);
				if(!isJwtValid) {
					reportResponse.setApplicationCode(Constants.Responses.SESSION_EXPIRED.getErrorCode());
					reportResponse.setApplicationResponse(Constants.Responses.SESSION_EXPIRED.getErrorMessage());
				}	
			}
			catch(SignatureException e)
			{
				isJwtValid=false;
				reportResponse.setApplicationCode(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorCode());
				reportResponse.setApplicationResponse(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorMessage());
				logger.error("*****ReportService: inside generateReport(). Invalid JWT Token received.");
			}
			if(isJwtValid) {
				//check the user role
				String email=jwtUtil.extractEmailIdFromJWT(token);
				
				LoginRequest userDetails=userRepository.findByEmail(email);
				
				//only the user with admin role can view the report
				if(userDetails!=null && Constants.ROLE_ADMIN.equals(userDetails.getUserRole())) {
					List<TransactionDetails> transactionRequests=transactionRepository.findAllByTxnDateBetween
							(reportRequest.getTxnFromDate(), reportRequest.getTxnToDate());
					
					//List<TransactionRequest> transactionRequests=transactionRepository.findAllWithTxnDateBefore(reportRequest.getTxnToDate());
					
					reportResponse.setTransactionRequests(transactionRequests);
					reportResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
					reportResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
				}
				else {
					reportResponse.setApplicationCode(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorCode());
					reportResponse.setApplicationResponse(Constants.Responses.UNAUTHORIZED_ACCESS.getErrorMessage());
				}			
			}
		}		
		return reportResponse;
	}

}
