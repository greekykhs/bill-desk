package com.ca.billdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.billdesk.model.LoginRequest;
import com.ca.billdesk.model.LoginResponse;
import com.ca.billdesk.service.LoginService;
import com.sun.istack.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("This controller contains all Login related webservices.")
public class LoginController {
	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	@ApiOperation("This webservice is for login")
	public LoginResponse login(@NotNull @RequestBody LoginRequest loginRequest) {
		return loginService.validate(loginRequest);
	}

	//TODO: web-services to create user, update password, forgot password, activate and invativate users
}
