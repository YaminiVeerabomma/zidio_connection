package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.service.UserPaymentStatusService;

@RestController
@RequestMapping("/api/user_subscrptions_Status")
public class UserPaymentStatusController {
	
	@Autowired
	private UserPaymentStatusService userPaymentStatusService;
	
	
	@PostMapping
	public ResponseEntity<UserPaymentStatusDTO>assign(@RequestBody UserPaymentStatusDTO dto ){
		return ResponseEntity.ok(userPaymentStatusService.assignSubscriptionPlan(dto));
	}

	@GetMapping(value="{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<UserPaymentStatusDTO>> getStatus(@PathVariable Long userId){
		return ResponseEntity.ok(userPaymentStatusService.getStatusByUserId(userId));
	}
}