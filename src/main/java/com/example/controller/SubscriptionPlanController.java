package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "*")
@Tag(name = "Subscription Plan API", description = "APIs to manage subscription plans")
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    // ✅ Create
    @PostMapping
    @Operation(summary = "Create Subscription Plan", description = "Create a new subscription plan")
    public ResponseEntity<SubscriptionPlanDTO> createSubscription(
            @RequestBody
            @Parameter(description = "Subscription plan object to create", required = true) SubscriptionPlanDTO dto) {
        return ResponseEntity.ok(subscriptionPlanService.createPlan(dto));
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Subscription Plan by ID", description = "Retrieve a subscription plan by its ID")
    public ResponseEntity<SubscriptionPlanDTO> getSubscriptionPlanById(
            @Parameter(description = "ID of the subscription plan", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(subscriptionPlanService.getPlanById(id));
    }

    // ✅ Update
    @PutMapping("/{id}")
    @Operation(summary = "Update Subscription Plan", description = "Update an existing subscription plan by ID")
    public ResponseEntity<SubscriptionPlanDTO> updateSubscriptionPlan(
            @Parameter(description = "ID of the subscription plan to update", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody
            @Parameter(description = "Updated subscription plan object", required = true) SubscriptionPlanDTO dto) {
        return ResponseEntity.ok(subscriptionPlanService.updateSubscriptionPlan(id, dto));
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subscription Plan", description = "Delete a subscription plan by its ID")
    public ResponseEntity<Void> deleteSubscriptionPlan(
            @Parameter(description = "ID of the subscription plan to delete", required = true, example = "1")
            @PathVariable Long id) {
        subscriptionPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get All (with pagination + sorting)
    @GetMapping
    @Operation(summary = "Get All Subscription Plans", description = "Retrieve all subscription plans with pagination and sorting")
    public ResponseEntity<Page<SubscriptionPlanDTO>> getAllSubscriptionPlans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(subscriptionPlanService.getAllSubscriptionPlans(page, size, sortBy, direction));
    }

    // ✅ Filter by Price
    @GetMapping("/price/{price}")
    @Operation(summary = "Get Subscription Plans by Price", description = "Retrieve subscription plans by price")
    public ResponseEntity<List<SubscriptionPlanDTO>> getPlansByPrice(
            @Parameter(description = "Price of the subscription plan", required = true, example = "99.99")
            @PathVariable Double price) {
        return ResponseEntity.ok(subscriptionPlanService.getPlansByPrice(price));
    }

    // ✅ Filter by Duration
    @GetMapping("/duration/{days}")
    @Operation(summary = "Get Subscription Plans by Duration", description = "Retrieve subscription plans by duration (days)")
    public ResponseEntity<List<SubscriptionPlanDTO>> getPlansByDuration(
            @Parameter(description = "Duration in days of the subscription plan", required = true, example = "30")
            @PathVariable Integer days) {
        return ResponseEntity.ok(subscriptionPlanService.getPlansByDuration(days));
    }

    // ✅ Filter by Price + Duration
    @GetMapping("/filter")
    @Operation(summary = "Get Subscription Plans by Price and Duration", description = "Retrieve subscription plans by price and duration")
    public ResponseEntity<List<SubscriptionPlanDTO>> getPlansByPriceAndDuration(
            @RequestParam Double price,
            @RequestParam Integer duration) {
        return ResponseEntity.ok(subscriptionPlanService.getPlansByPriceAndDuration(price, duration));
    }

    // ✅ Search (by name/description)
    @GetMapping("/search")
    @Operation(summary = "Search Subscription Plans", description = "Search subscription plans by keyword (name or description)")
    public ResponseEntity<List<SubscriptionPlanDTO>> searchPlans(
            @RequestParam String keyword) {
        return ResponseEntity.ok(subscriptionPlanService.searchPlans(keyword));
    }
}
