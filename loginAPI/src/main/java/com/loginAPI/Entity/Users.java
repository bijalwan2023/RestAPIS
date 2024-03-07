package com.loginAPI.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//User.java
@Entity
public class Users {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(unique = true)
 private String phoneNumber;

 private String otp;
 
 
 



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}



public Users(Long id, String phoneNumber, String otp, String userName, String email, String password) {
	super();
	this.id = id;
	this.phoneNumber = phoneNumber;
	this.otp = otp;
	
}

public Users() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "User [id=" + id + ", phoneNumber=" + phoneNumber + ", otp=" + otp + "]";
}



}


