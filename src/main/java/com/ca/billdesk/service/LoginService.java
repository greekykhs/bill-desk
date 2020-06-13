package com.ca.billdesk.service;

import com.ca.billdesk.model.LoginRequest;
import com.ca.billdesk.model.LoginResponse;

public interface LoginService {
	LoginResponse validate(LoginRequest loginRequest);
}
