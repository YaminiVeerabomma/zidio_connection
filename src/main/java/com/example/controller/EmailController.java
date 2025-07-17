package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.EmailRequest;
import com.example.service.EmailService;

@RestController
@RequestMapping("/api/notify")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping(value="/send",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>send(@RequestBody EmailRequest request){
		emailService.sendEmail(request);
		return ResponseEntity.ok("Email sent Successsfully");
	}
	

}
