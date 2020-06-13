package com.ca.billdesk.common;

public class Constants {
	public static final String TXN_STATUS_INPROGRESS="In Progress";
	public static final String TXN_STATUS_PENDING="Pending";
	public static final String TXN_STATUS_REJECTED="Rejected";
	public static final String TXN_STATUS_COMPLETED="Completed";
	
	public static final String REQUEST_HEADER_TOKEN = "token";
	public static final String ROLE_ADMIN="ADMIN";
	public static final String ROLE_USER="USER";
	
	public enum Responses{SUCCESS("200", "Success"),
		INTERNAL_ERROR("500", "Internal error occurred"), 
		NO_RECORDS_FOUND("501", "No records found"),
		INVALID_LOGIN("502", "Login failed! Please enter valid credentails."),
		SESSION_EXPIRED("503", "Session expired! Please login again"),
		INVALID_TXN("504", "Please login or enter valid mobile no."),
		LOGIN_AGAIN("505", "Please login again"),
		UNAUTHORIZED_ACCESS("506", "Unathorized Access!"),
		PAYMENT_ERROR("507", "Error occurred while doing the payment. Please contact our customer service."),
		TRANSACTION_FAILED("509", "Error occurred while doing the payment. Please contact our customer service."),
		;
		private String errorCode;
		private String errorMessage;
		
		private Responses(String errorCode, String errorMessage)
		{
			this.errorCode=errorCode;
			this.errorMessage=errorMessage;
		}
		
		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
	}
	public static class PROTOCOLS {
		public static final String HTTP = "http";
		public static final String HTTPS = "https";
	}
}
