package com.example.controller;

import com.example.DTO.AdminUserDTO;
import com.example.Enum.Role;
import com.example.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@Tag(name = "Admin API", description = "Endpoints to manage users and roles")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    @GetMapping(value="/role/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get users by role", description = "Retrieve users filtered by their role")
    public ResponseEntity<List<AdminUserDTO>> getByRole(
            @Parameter(description = "Role to filter users", required = true) @PathVariable Role role) {
        return ResponseEntity.ok(adminUserService.getUserByRole(role));
    }

    @PutMapping(value="/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update user status", description = "Activate or deactivate a user by ID")
    public ResponseEntity<AdminUserDTO> updateUserStatus(
            @Parameter(description = "User ID", required = true) @PathVariable Long id,
            @Parameter(description = "Active status", required = true) @RequestParam boolean active) {
        return ResponseEntity.ok(adminUserService.upadateStatus(id, active));
    }

    @PutMapping(value="/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Block user", description = "Block a user account by ID")
    public ResponseEntity<AdminUserDTO> blockUser(
            @Parameter(description = "User ID to block", required = true) @PathVariable Long id) {
        AdminUserDTO dto = adminUserService.blockUser(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping(value="/{id}/unBlock", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Unblock user", description = "Unblock a previously blocked user by ID")
    public ResponseEntity<AdminUserDTO> unBlockUser(
            @Parameter(description = "User ID to unblock", required = true) @PathVariable Long id) {
        AdminUserDTO dto = adminUserService.blockUser(id); // Note: Should probably call unblock method
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}
