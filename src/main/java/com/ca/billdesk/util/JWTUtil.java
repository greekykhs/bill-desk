package com.ca.billdesk.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	//TODO should be taken from property file.
    private String SECRET_KEY = "secret";

    public String extractJwtBody(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractMobileNoFromJWT(String token) {
    	String jwtBody=extractClaim(token, Claims::getSubject);
        return jwtBody.substring(0, jwtBody.indexOf("|"));
    }
    public String extractEmailIdFromJWT(String token) {
    	String jwtBody=extractClaim(token, Claims::getSubject);
        return jwtBody.substring(jwtBody.indexOf("|")+1);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String jwtBody) {
        Map<String, Object> claims = new HashMap<>();
        //jwtBody is mobile no.|email id
        return createToken(claims, jwtBody);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        		//TODO valid for 10 hours, should be taken from property file
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public String generateToken(String jwtBody, int expirationTime) {
        Map<String, Object> claims = new HashMap<>();
        //jwtBody is mobile no.|email id
        return createToken(claims, jwtBody, expirationTime);
    }
    private String createToken(Map<String, Object> claims, String subject, int expirationTime) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        		//TODO valid for 10 hours, should be taken from property file
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, String jwtBody) {
        return (extractJwtBody(token).equals(jwtBody)) && !isTokenExpired(token);
    }
    
    public Boolean validateToken(String token) {
        return (decodeJWT(token)!=null && !isTokenExpired(token));
    }
    
    public Claims decodeJWT(String token) {
        //This will throw an exception if it is not a signed JWS
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims;
    }
}