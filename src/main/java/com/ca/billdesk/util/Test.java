package com.ca.billdesk.util;

public class Test {

	public static void main(String[] args) {
		JWTUtil jwtUtil=new JWTUtil();
		String token=jwtUtil.generateToken("9999999999|gabriel@gmail.com");
		System.out.println(token);
		System.out.println(jwtUtil.decodeJWT(token));
		
		String payLoad="9999999999|gabriel@gmail.com";
		token=jwtUtil.generateToken(payLoad, 1);
	
		boolean isTokenExpired=false;
		try {
			Thread.sleep(100);
			isTokenExpired=jwtUtil.isTokenExpired(token);
		} catch (Exception e) {
			isTokenExpired=true;
		}
		System.out.println(isTokenExpired);
		
		

	}

}
