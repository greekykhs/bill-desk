package com.ca.billdesk;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.LoginRequest;
import com.ca.billdesk.model.LoginResponse;
import com.ca.billdesk.model.MerchantResponse;
import com.ca.billdesk.model.TransactionDetails;
import com.ca.billdesk.model.TransactionRequest;
import com.ca.billdesk.model.TransactionResponse;
import com.ca.billdesk.service.LoginService;
import com.ca.billdesk.service.MerchantService;
import com.ca.billdesk.service.TransactionService;
import com.ca.billdesk.util.JWTUtil;

@SpringBootTest
class BillDeskApplicationTests {

	@Autowired
	LoginService loginService;
	
	@Autowired
	MerchantService merchantService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	TransactionService transactionService;
	
	@Test
	public void validLoginTest() {
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setEmail("gabriel@gmail.com");
		loginRequest.setPassword("qwert");
		LoginResponse loginResponse=loginService.validate(loginRequest);
		Assert.assertEquals(true, (loginResponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(loginResponse.getApplicationCode())));
	}
	
	@Test
	public void invalidLoginTest() {
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setEmail("gabriel@gmail.com");
		loginRequest.setPassword("wert");
		LoginResponse loginResponse=loginService.validate(loginRequest);
		Assert.assertEquals(false, (loginResponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(loginResponse.getApplicationCode())));
	}
	
	@Test
	public void validateJWT() {
		String payLoad="9999999999|gabriel@gmail.com";
		String token=jwtUtil.generateToken(payLoad);		
		Assert.assertEquals(true, jwtUtil.validateToken(token));
	}
	
	@Test
	public void verifyEmailFromJWT() {
		String payLoad="9999999999|gabriel@gmail.com";
		String token=jwtUtil.generateToken(payLoad);		
		Assert.assertEquals("gabriel@gmail.com", jwtUtil.extractEmailIdFromJWT(token));
	}
	
	@Test
	public void isJWTExpired() {
		String payLoad="9999999999|gabriel@gmail.com";
		String token=jwtUtil.generateToken(payLoad, 1);	
		boolean isTokenExpired=false;
		try {
			Thread.sleep(100);
			isTokenExpired=jwtUtil.isTokenExpired(token);
		} catch (Exception e) {
			isTokenExpired=true;
		}
		Assert.assertEquals(true, isTokenExpired);
	}
	
	@Test
	public void isMerchantsPresent() {
		MerchantResponse merchantResponse=merchantService.retrieveAllMerchants();
		Assert.assertEquals(true, (merchantResponse!=null && merchantResponse.getMerchantTypes()!=null && !merchantResponse.getMerchantTypes().isEmpty()));
	}
	
	@Test
	public void merchantsSize3() {
		MerchantResponse merchantResponse=merchantService.retrieveAllMerchants();
		Assert.assertEquals(3, merchantResponse.getMerchantTypes().size());
	}
	
	@Test
	public void doTransactionAfterLogin() {
		String payLoad="9999999999|gabriel@gmail.com";
		String token=jwtUtil.generateToken(payLoad);		
		
		TransactionRequest transactionRequest=new TransactionRequest();
		transactionRequest.setAmount("1000");
		transactionRequest.setIsPartial("Y");
		transactionRequest.setMerchantKey("MOB_VOD");
		//transactionRequest.setMobileNo("9988776655");
		transactionRequest.setTxnMode("NETBANKING");
		
		TransactionResponse transactionresponse=transactionService.doTransaction(transactionRequest, token);
		Assert.assertEquals(true, (transactionresponse!=null 
				&& transactionresponse.getTxnId()!=0
				&& Constants.Responses.SUCCESS.getErrorCode().equals(transactionresponse.getApplicationCode())));
	}
	
	@Test
	public void doTransactionWithoutLoginAndMobileNo() {			
		TransactionRequest transactionRequest=new TransactionRequest();
		transactionRequest.setAmount("1000");
		transactionRequest.setIsPartial("Y");
		transactionRequest.setMerchantKey("MOB_VOD");
		transactionRequest.setTxnMode("NETBANKING");
		
		TransactionResponse transactionresponse=transactionService.doTransaction(transactionRequest, "");
		Assert.assertEquals(false, (transactionresponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(transactionresponse.getApplicationCode())));
	}
	@Test
	public void doTransactionWithMobileWithoutLogin() {		
		TransactionRequest transactionRequest=new TransactionRequest();
		transactionRequest.setAmount("1000");
		transactionRequest.setIsPartial("Y");
		transactionRequest.setMerchantKey("MOB_VOD");
		transactionRequest.setMobileNo("9988776655");
		transactionRequest.setTxnMode("NETBANKING");
		
		TransactionResponse transactionresponse=transactionService.doTransaction(transactionRequest, "");
		Assert.assertEquals(true, (transactionresponse!=null 
				&& transactionresponse.getTxnId()!=0
				&& Constants.Responses.SUCCESS.getErrorCode().equals(transactionresponse.getApplicationCode())));
	}
}
