package com.loginAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import com.loginAPI.Entity.User;
@Service
public interface UserRepository extends JpaRepository<User, Long> {
	
   // String findByUserName(String userName);

	User findByPhoneNumber(String phoneNumber);

	
   
}