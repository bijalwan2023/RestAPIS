package com.loginAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import com.loginAPI.Entity.Users;
@Service
public interface UserRepository extends JpaRepository<Users, Long> {
	
   // String findByUserName(String userName);

	Users findByPhoneNumber(String phoneNumber);

	
   
}