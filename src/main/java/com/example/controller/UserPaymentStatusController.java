package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.service.UserPaymentStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user_subscrptions_Status")
@Tag(name = "User Payment Status API", description = "APIs for managing user subscription/payment status")
public class UserPaymentStatusController {

    @Autowired
    private UserPaymentStatusService service;

    @PostMapping
    @Operation(summary = "Create User Payment Status", description = "Create a new payment/subscription status record for a user")
    public ResponseEntity<UserPaymentStatusDTO> create(
            @RequestBody 
            @Parameter(description = "UserPaymentStatus object to create", required = true) UserPaymentStatusDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    @Operation(summary = "Get All User Payment Status", description = "Retrieve all user payment/subscription status records")
    public ResponseEntity<List<UserPaymentStatusDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User Payment Status by ID", description = "Retrieve a specific user payment status by its ID")
    public ResponseEntity<UserPaymentStatusDTO> getById(
            @Parameter(description = "ID of the payment status", required = true, example = "1")
            @PathVariable Long id) {
        UserPaymentStatusDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User Payment Status", description = "Update an existing user payment status by its ID")
    public ResponseEntity<UserPaymentStatusDTO> update(
            @Parameter(description = "ID of the payment status to update", required = true, example = "1")
            @PathVariable Long id, 
            @RequestBody 
            @Parameter(description = "Updated UserPaymentStatus object", required = true) UserPaymentStatusDTO dto) {
        UserPaymentStatusDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User Payment Status", description = "Delete a user payment status record by its ID")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the payment status to delete", required = true, example = "1")
            @PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
