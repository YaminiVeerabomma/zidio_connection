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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@Tag(name = "Admin API", description = "Endpoints to manage users and roles")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/role/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminUserDTO>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(adminUserService.getUserByRole(role));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDTO> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(adminUserService.updateStatus(id, active));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDTO> blockUser(@PathVariable Long id) {
        AdminUserDTO dto = adminUserService.blockUser(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/{id}/unBlock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDTO> unBlockUser(@PathVariable Long id) {
        AdminUserDTO dto = adminUserService.unBlockUser(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // ✅ Create Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDTO> createAdmin(@RequestBody AdminUserDTO dto) {
        return ResponseEntity.ok(adminUserService.createAdmin(dto));
    }

    // ✅ Update Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminUserDTO dto) {
        return ResponseEntity.ok(adminUserService.updateAdmin(id, dto));
    }
}
