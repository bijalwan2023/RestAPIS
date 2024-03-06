package com.loginAPI.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginAPI.Entity.User;
import com.loginAPI.Repository.UserRepository;
@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

	public String generateRandomOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int randomOTP = random.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
        return String.valueOf(randomOTP);
    }
	
	public boolean validateOtp(String phoneNumber, String enteredOtp) {
        // Validate the entered OTP for the provided phone number
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            String storedOtp = user.getOtp();
            if (storedOtp != null && storedOtp.equals(enteredOtp)) {
                return true;
            }
        }
        
        return false;
    }
    }
	

