package com.ca.billdesk.model;

public class BillDeskResponse {
	private String applicationCode;
	private String applicationResponse;
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplicationResponse() {
		return applicationResponse;
	}
	public void setApplicationResponse(String applicationResponse) {
		this.applicationResponse = applicationResponse;
	}

	public BillDeskResponse(String applicationCode, String applicationResponse) {
		this.applicationCode = applicationCode;
		this.applicationResponse = applicationResponse;
	}

	public BillDeskResponse() {
		super();
	}
	@Override
	public String toString() {
		return "BillDeskResponse [applicationCode=" + applicationCode + ", applicationResponse=" + applicationResponse
				+ "]";
	}
}
