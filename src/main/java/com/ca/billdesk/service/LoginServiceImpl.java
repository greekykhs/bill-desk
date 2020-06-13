package com.ca.billdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.LoginRequest;
import com.ca.billdesk.model.LoginResponse;
import com.ca.billdesk.repository.UserRepository;
import com.ca.billdesk.util.JWTUtil;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Lazy
	JWTUtil jwtUtil;
	
	@Autowired
	@Lazy
	UserRepository userRepository;
	
	@Override
	public LoginResponse validate(LoginRequest loginRequest) {
		//TODO: validation is pending
		LoginRequest userDetails=userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		String jwtToken="";
		LoginResponse loginResponse;
		
		if(userDetails==null){
			loginResponse=new LoginResponse(jwtToken);
			loginResponse.setApplicationCode(Constants.Responses.INVALID_LOGIN.getErrorCode());
			loginResponse.setApplicationResponse(Constants.Responses.INVALID_LOGIN.getErrorMessage());
		}
		else {
			//if login successful generate JWT
			String jwtBody=userDetails.getMobileNo()+"|"+userDetails.getEmail();
			jwtToken=jwtUtil.generateToken(jwtBody);
			
			loginResponse=new LoginResponse(jwtToken);
			loginResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
			loginResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
		}		
		return loginResponse;
	}
}
