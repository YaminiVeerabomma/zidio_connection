package com.example.controller;

import com.example.DTO.PaymentDTO;
import com.example.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "Endpoints to manage payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Make payment", description = "Process a payment for a user subscription")
    public ResponseEntity<PaymentDTO> pay(
            @Parameter(description = "Payment data", required = true) @RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.makePayment(dto));
    }

    @GetMapping
    @Operation(summary = "Get all payments", description = "Retrieve a list of all payments")
    public ResponseEntity<List<PaymentDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping(value="/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get payments by user", description = "Retrieve all payments made by a specific user")
    public List<PaymentDTO> getPaymentsByUser(
            @Parameter(description = "ID of the user", required = true) @PathVariable Long userId) {
        return paymentService.getPaymentsByUserId(userId);
    }
}
