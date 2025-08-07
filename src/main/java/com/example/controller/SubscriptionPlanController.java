package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "*") // Allow frontend access (adjust origin if needed)
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    // ✅ Create a new subscription plan
    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> createSubscription(@RequestBody SubscriptionPlanDTO dto) {
        SubscriptionPlanDTO created = subscriptionPlanService.createSubscription(dto);
        return ResponseEntity.ok(created);
    }

    // ✅ Get all subscription plans
    @GetMapping
    public ResponseEntity<List<SubscriptionPlanDTO>> getAllSubscriptionPlans() {
        List<SubscriptionPlanDTO> plans = subscriptionPlanService.getAllSubscriptionPlans();
        return ResponseEntity.ok(plans);
    }

    // ✅ Get subscription plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlanDTO> getSubscriptionPlanById(@PathVariable Long id) {
        SubscriptionPlanDTO dto = subscriptionPlanService.getSubscriptionPlanById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // ✅ Update a subscription plan
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlanDTO> updateSubscriptionPlan(@PathVariable Long id,
                                                                       @RequestBody SubscriptionPlanDTO dto) {
        SubscriptionPlanDTO updated = subscriptionPlanService.updateSubscriptionPlan(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ✅ Delete a subscription plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriptionPlan(@PathVariable Long id) {
        boolean deleted = subscriptionPlanService.deleteSubscriptionPlan(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
