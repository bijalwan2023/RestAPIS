package com.loginAPI.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtTokenProvider {
	 @Value("${jwt.secret}")
	    private String jwtSecret;

	    @Value("${jwt.expiration}")
	    private int jwtExpiration;

	    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	    public String generateToken(String phoneNumber) {
	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpiration);

	        return Jwts.builder()
	                .setSubject(phoneNumber)
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(jwtSecretKey)
	                .compact();
	    }

	    public boolean validateToken(String token) {
	        try {
	           // token = token.replace("Bearer ", "");
	            Claims claims = Jwts.parser()
	                    .setSigningKey(jwtSecretKey)
	                    .parseClaimsJws(token)
	                    .getBody();

	            Date expirationDate = claims.getExpiration();
	            return expirationDate.after(new Date()); // Check if token has expired
	        } catch (Exception e) {
	            // Token is invalid
	            return false;
	        }
	    }
	}