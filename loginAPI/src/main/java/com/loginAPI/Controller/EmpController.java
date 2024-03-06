package com.loginAPI.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loginAPI.Entity.Employee;
import com.loginAPI.Repository.EmpRepository;


@RestController
public class EmpController {
	@Autowired
	EmpRepository empRepository;
	@PostMapping("/saveEmp")
	public ResponseEntity<String> getEmp(@RequestBody Employee emp) {
	   
		empRepository.save(emp);

		        return ResponseEntity.ok("Employee saved successfully");
		    }
	
	}
	
		
	


