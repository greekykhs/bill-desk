package com.ca.billdesk.model;

public class LoginResponse extends BillDeskResponse {
	private String jwt;
	public LoginResponse(){
		super();
	}
	public LoginResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}
