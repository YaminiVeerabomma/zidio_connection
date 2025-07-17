package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.PaymentDTO;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping
	public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentDTO dto ){
		return ResponseEntity.ok(paymentService.makePayment(dto));
	}
	@GetMapping
	public ResponseEntity<List<PaymentDTO>>getAll(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	  @GetMapping(value="/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<PaymentDTO> getPaymentsByUser(@PathVariable Long userId) {
	        return paymentService.getPaymentsByUserId(userId);
	    }
}