package com.loginAPI.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginAPI.Entity.User;
import com.loginAPI.Repository.UserRepository;
import com.loginAPI.Service.UserService;
import com.loginAPI.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;

// UserController.java
@RestController
@RequestMapping("/api/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	UserService userService;

	@PostMapping("/generate-otp")
	public ResponseEntity<String> generateOTP(@RequestBody User request) {
	    String phoneNumber = request.getPhoneNumber();
	    String otp = userService.generateRandomOTP();

	    // Generate JWT token
	    String jwtToken = jwtTokenProvider.generateToken(phoneNumber);

	    // Save user details or update OTP if user already exists
	    User user = userRepository.findByPhoneNumber(phoneNumber);
	    if (user == null) {
	        user = new User();
	        user.setPhoneNumber(phoneNumber);
	    }
	    user.setOtp(otp);
	    userRepository.save(user);

	    // Return JWT token along with OTP
	    String response = "{\"jwtToken\": \"" + jwtToken + "\", \"otp\": \"" + otp + "\"}";
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	@PostMapping("/validate-otp")
	public ResponseEntity<String> validateOtp(@RequestBody User user) {
		String phoneNumber=user.getPhoneNumber();
		String enteredOtp=user.getOtp();
		boolean isValidOtp = userService.validateOtp(phoneNumber, enteredOtp);
		if (isValidOtp) {
			//return token with status
			 String jwtToken = jwtTokenProvider.generateToken(phoneNumber);
		        
		        // Return success response with status 200 OK and JWT token
		        String response = "{\"jwtToken\": \"" + jwtToken + "\", \"message\": \"OTP validation successful for phone number: " + phoneNumber + "\"}";
		        return ResponseEntity.ok(response);
			//return ResponseEntity.ok("OTP validation successful for phone number: " + phoneNumber);
		} else {
			//send status code =failure message(500)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid OTP for phone number: " + phoneNumber);

			//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP for phone number: " + phoneNumber);
		}
	}

	@GetMapping("/allUsers")
	public List<User>allUsers(){
		List<User> user=userRepository.findAll();
		return user;

	}
	// @PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/public")
	public String publicM() {
		return "i am public method";
	}
	 @GetMapping("/private")
	    public ResponseEntity<String> privateM(HttpServletRequest request) {
	        // Extract token from request header
	        String token = request.getHeader("Authorization");

	        // Validate token
	        if (token != null && jwtTokenProvider.validateToken(token)) {
	        	String response = "{\"authorized\"}";
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	        	String response = "{\"Unauthorized\"}";
	    	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	            //return "Unauthorized";
	        }
	    }
	}


