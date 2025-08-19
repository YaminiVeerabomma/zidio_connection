package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "*")
@Tag(name = "Subscription Plan API", description = "APIs to manage subscription plans")
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping
    @Operation(summary = "Create Subscription Plan", description = "Create a new subscription plan")
    public ResponseEntity<SubscriptionPlanDTO> createSubscription(
            @RequestBody
            @Parameter(description = "Subscription plan object to create", required = true) SubscriptionPlanDTO dto) {
        SubscriptionPlanDTO created = subscriptionPlanService.createSubscription(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @Operation(summary = "Get All Subscription Plans", description = "Retrieve all subscription plans")
    public ResponseEntity<List<SubscriptionPlanDTO>> getAllSubscriptionPlans() {
        List<SubscriptionPlanDTO> plans = subscriptionPlanService.getAllSubscriptionPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Subscription Plan by ID", description = "Retrieve a subscription plan by its ID")
    public ResponseEntity<SubscriptionPlanDTO> getSubscriptionPlanById(
            @Parameter(description = "ID of the subscription plan", required = true, example = "1") @PathVariable Long id) {
        SubscriptionPlanDTO dto = subscriptionPlanService.getSubscriptionPlanById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Subscription Plan", description = "Update an existing subscription plan by ID")
    public ResponseEntity<SubscriptionPlanDTO> updateSubscriptionPlan(
            @Parameter(description = "ID of the subscription plan to update", required = true, example = "1") @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Updated subscription plan object", required = true) SubscriptionPlanDTO dto) {
        SubscriptionPlanDTO updated = subscriptionPlanService.updateSubscriptionPlan(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subscription Plan", description = "Delete a subscription plan by its ID")
    public ResponseEntity<Void> deleteSubscriptionPlan(
            @Parameter(description = "ID of the subscription plan to delete", required = true, example = "1") @PathVariable Long id) {
        boolean deleted = subscriptionPlanService.deleteSubscriptionPlan(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
