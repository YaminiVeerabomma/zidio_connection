package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AnalysticsResponse;
import com.example.service.AnalysticsService;

@RestController
@RequestMapping("/api/Analystics")
public class AnalysticsController {
	
	@Autowired
	private  AnalysticsService  analysticsService;
	
	@GetMapping(value="/summery")
	public ResponseEntity< AnalysticsResponse> getSummery(){
		return  ResponseEntity.ok(null);
	}
	
	

}
