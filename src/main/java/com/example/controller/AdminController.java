package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.SystemUserDTO;
import com.example.Enum.Role;
import com.example.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ✅ Get all users
    @GetMapping("/users")
    public ResponseEntity<List<SystemUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // ✅ Get users by role
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<SystemUserDTO>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(adminService.getUserByRole(role));
    }

    // ✅ Update user status (active/inactive)
    @PutMapping("/users/status/{id}")
    public ResponseEntity<SystemUserDTO> updateUserStatus(
            @PathVariable Long id,
            @RequestParam Boolean isActive) {
        return ResponseEntity.ok(adminService.updateStatus(id, isActive));
    }

    // ✅ Block user (set isActive to false)
    @PutMapping("/users/{id}/block")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        adminService.blockUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been blocked.");
    }

    // ✅ Unblock user (set isActive to true)
    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable Long id) {
        adminService.unBlockUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been unblocked.");
    }
}

