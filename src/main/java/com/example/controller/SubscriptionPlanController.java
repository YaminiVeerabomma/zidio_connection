package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.SubscriptionPlanService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionPlanController {
	
	@Autowired
	private SubscriptionPlanService  subscriptionPlanService;
	
	
	@GetMapping
	public ResponseEntity<List<SubscriptionPlanDTO>>getAll(){
		return ResponseEntity.ok(subscriptionPlanService.getAllSubscriptionPlan());
	}
	
	@PostMapping
	public ResponseEntity<SubscriptionPlanDTO> create(@RequestBody SubscriptionPlanDTO dto) {
		return ResponseEntity.ok(subscriptionPlanService.createSubscription(dto));
	}

}