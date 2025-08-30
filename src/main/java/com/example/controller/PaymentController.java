package com.example.controller;

import com.example.DTO.PaymentDTO;
import com.example.Enum.PaymentStatus;
import com.example.Enum.PaymentType;
import com.example.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "APIs for managing payments and subscriptions")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ✅ Create / Make a Payment
    @PostMapping
    @Operation(summary = "Make a Payment", description = "Create a payment and update subscription if completed")
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.makePayment(dto));
    }

    // ✅ Get All Payments
    @GetMapping
    @Operation(summary = "Get All Payments")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // ✅ Get Payment by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Payment by ID")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // ✅ Get Payment by Transaction ID
    @GetMapping("/transaction/{transactionId}")
    @Operation(summary = "Get Payment by Transaction ID")
    public ResponseEntity<PaymentDTO> getPaymentByTransactionId(@PathVariable String transactionId) {
        return ResponseEntity.ok(paymentService.getPaymentByTransactionId(transactionId));
    }

    // ✅ Get Payments by User ID
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get Payments by User ID")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    // ✅ Get Payments by Plan ID
    @GetMapping("/plan/{planId}")
    @Operation(summary = "Get Payments by Plan ID")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(paymentService.getPaymentsByPlanId(planId));
    }

    // ✅ Get Payments by Plan & Status
    @GetMapping("/plan/{planId}/status/{status}")
    @Operation(summary = "Get Payments by Plan and Status")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByPlanAndStatus(@PathVariable Long planId,
                                                                       @PathVariable PaymentStatus status) {
        return ResponseEntity.ok(paymentService.getPaymentsByPlanIdAndStatus(planId, status));
    }

    // ✅ Get Payments by User & Type
    @GetMapping("/user/{userId}/type/{type}")
    @Operation(summary = "Get Payments by User and Payment Type")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserAndType(@PathVariable Long userId,
                                                                     @PathVariable PaymentType type) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserAndType(userId, type));
    }

    // ✅ Delete Payment
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Payment")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        boolean deleted = paymentService.deletePayment(id);
        return deleted
                ? ResponseEntity.ok("Payment deleted successfully")
                : ResponseEntity.badRequest().body("Payment not found");
    }
}
