package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.PaymentDTO;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;
import com.example.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
@Tag(name = "Payment API", description = "APIs to manage payments, check status, type, plan usage, and transaction")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ✅ Create Payment
    @PostMapping
    @Operation(summary = "Create Payment", description = "Create a new payment record")
    public ResponseEntity<PaymentDTO> createPayment(
            @RequestBody
            @Parameter(description="Payment object to create", required=true) PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.makePayment(dto));
    }

    // ✅ Get all Payments
    @GetMapping
    @Operation(summary = "Get all Payments", description = "Retrieve all payment records")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // ✅ Get Payment by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Payment by ID", description = "Retrieve a payment by its ID")
    public ResponseEntity<PaymentDTO> getPaymentById(
            @PathVariable
            @Parameter(description="ID of the payment", required=true) Long id) {
        PaymentDTO dto = paymentService.getPaymentById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // ✅ Get Payment by Transaction ID
    @GetMapping("/transaction/{transactionId}")
    @Operation(summary = "Get Payment by Transaction ID", description = "Retrieve a payment using its transaction ID")
    public ResponseEntity<PaymentDTO> getPaymentByTransaction(
            @PathVariable
            @Parameter(description="Transaction ID", required=true) String transactionId) {
        PaymentDTO dto = paymentService.getPaymentByTransactionId(transactionId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // ✅ Get Payments by User
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get Payments by User", description = "Retrieve all payments for a specific user")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUser(
            @PathVariable
            @Parameter(description="User ID", required=true) Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    // ✅ Get Payments by Plan
    @GetMapping("/plan/{planId}")
    @Operation(summary = "Get Payments by Plan", description = "Retrieve all payments associated with a subscription plan")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByPlan(
            @PathVariable
            @Parameter(description="Plan ID", required=true) Long planId) {
        return ResponseEntity.ok(paymentService.getPaymentsByPlanId(planId));
    }

    // ✅ Get Payments by Plan and Status
    @GetMapping("/plan/{planId}/status/{status}")
    @Operation(summary = "Get Payments by Plan and Status", description = "Retrieve all payments for a plan filtered by payment status")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByPlanAndStatus(
            @PathVariable
            @Parameter(description="Plan ID", required=true) Long planId,
            @PathVariable
            @Parameter(description="Payment Status (e.g., COMPLETED, FAILED, PENDING)", required=true) PaymentStatus status) {
        return ResponseEntity.ok(paymentService.getPaymentsByPlanIdAndStatus(planId, status));
    }

    // ✅ Get Payments by User and Payment Type
    @GetMapping("/user/{userId}/type/{type}")
    @Operation(summary = "Get Payments by User and Type", description = "Retrieve all payments of a specific user filtered by payment type")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserAndType(
            @PathVariable
            @Parameter(description="User ID", required=true) Long userId,
            @PathVariable
            @Parameter(description="Payment Type (e.g., CARD, NETBANKING, UPI)", required=true) PaymentType type) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserAndType(userId, type));
    }

    // ✅ Delete Payment
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Payment", description = "Delete a payment record by its ID")
    public ResponseEntity<Void> deletePayment(
            @PathVariable
            @Parameter(description="Payment ID", required=true) Long id) {
        return paymentService.deletePayment(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
