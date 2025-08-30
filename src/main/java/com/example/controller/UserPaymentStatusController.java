package com.example.controller;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.Enum.PaidStatus;
import com.example.service.UserPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-payments_status")
public class UserPaymentStatusController {

    @Autowired
    private UserPaymentStatusService userPaymentStatusService;

    // ✅ Create or Update Payment
    @PostMapping
    public ResponseEntity<UserPaymentStatusDTO> savePayment(@RequestBody UserPaymentStatusDTO dto) {
        return ResponseEntity.ok(userPaymentStatusService.save(dto));
    }

    // ✅ Get by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserPaymentStatusDTO> getByUserId(@PathVariable Long userId) {
        return userPaymentStatusService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Active Subscriptions
    @GetMapping("/active")
    public ResponseEntity<List<UserPaymentStatusDTO>> getActiveSubscriptions() {
        return ResponseEntity.ok(userPaymentStatusService.getActiveSubscriptions());
    }

    // ✅ Expired Subscriptions
    @GetMapping("/expired")
    public ResponseEntity<List<UserPaymentStatusDTO>> getExpiredSubscriptions() {
        return ResponseEntity.ok(userPaymentStatusService.getExpiredSubscriptions());
    }

    // ✅ Get by Plan ID
    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<UserPaymentStatusDTO>> getByPlanId(@PathVariable Long planId) {
        return ResponseEntity.ok(userPaymentStatusService.getByPlanId(planId));
    }

    // ✅ Delete by User ID
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable Long userId) {
        userPaymentStatusService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
    // ✅ Get by PaidStatus (example: PAID, UNPAID, PENDING)
    @GetMapping("/paidStatus/{paidStatus}")
    public ResponseEntity<List<UserPaymentStatusDTO>> getByPaidStatus(@PathVariable PaidStatus paidStatus) {
        return ResponseEntity.ok(userPaymentStatusService.getByPaidStatus(paidStatus));
    }
}
