package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.AdminUserDTO;
import com.example.Enum.Role;
import com.example.entity.AdminUser;
import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.AdminUserRepository;
import com.example.repository.UserRepository;

@Service
public class AdminUserService {

    private static final Logger log = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Create AdminUser
    public AdminUserDTO createAdmin(AdminUserDTO dto) {
        log.info("📩 Create Admin request for email: {}", dto.getEmail());

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> {
                    log.warn("❌ User not found while creating Admin -> {}", dto.getEmail());
                    return new UserNotFoundException("User not found with email: " + dto.getEmail());
                });

        // Assign ADMIN role
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        AdminUser admin = new AdminUser();
        admin.setUser(user);
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setRole(Role.ADMIN);
        admin.setActive(true);
        admin.setBlocked(false);

        AdminUser savedAdmin = adminUserRepository.save(admin);
        log.info("✅ Admin created successfully with email: {}", savedAdmin.getEmail());

        return toDTO(savedAdmin);
    }

    // 🔹 Update AdminUser
    public AdminUserDTO updateAdmin(Long id, AdminUserDTO dto) {
        log.info("✏️ Update Admin request for ID: {}", id);

        AdminUser admin = adminUserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("❌ Admin not found with ID: {}", id);
                    return new UserNotFoundException("Admin not found with id: " + id);
                });

        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());

        if (dto.getRole() != null) {
            admin.setRole(dto.getRole());
        }

        admin.setBlocked(dto.isBlocked());
        admin.setActive(dto.isActive());

        AdminUser updated = adminUserRepository.save(admin);
        log.info("✅ Admin updated successfully -> ID: {}, Email: {}", updated.getId(), updated.getEmail());

        return toDTO(updated);
    }

    // 🔹 Get All Admin Users
    public List<AdminUserDTO> getAllUsers() {
        log.info("📋 Fetching all Admin users...");
        List<AdminUserDTO> admins = adminUserRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        log.info("✅ Found {} Admin users", admins.size());
        return admins;
    }

    // 🔹 Block User
    public AdminUserDTO blockUser(Long id) {
        log.info("⛔ Block Admin request for ID: {}", id);

        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("❌ Cannot block: Admin not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        user.setBlocked(true);
        AdminUser blocked = adminUserRepository.save(user);

        log.info("✅ Admin blocked -> ID: {}, Email: {}", blocked.getId(), blocked.getEmail());
        return toDTO(blocked);
    }

    // 🔹 Unblock User
    public AdminUserDTO unBlockUser(Long id) {
        log.info("✅ Unblock Admin request for ID: {}", id);

        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("❌ Cannot unblock: Admin not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        user.setBlocked(false);
        AdminUser unblocked = adminUserRepository.save(user);

        log.info("✅ Admin unblocked -> ID: {}, Email: {}", unblocked.getId(), unblocked.getEmail());
        return toDTO(unblocked);
    }

    // 🔹 Get Users by Role
    public List<AdminUserDTO> getUserByRole(Role role) {
        log.info("🔎 Fetching Admin users with role: {}", role);

        List<AdminUserDTO> users = adminUserRepository.findByRole(role)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        log.info("✅ Found {} users with role: {}", users.size(), role);
        return users;
    }

    // 🔹 Update Active Status
    public AdminUserDTO updateStatus(Long id, boolean isActive) {
        log.info("🔄 Update active status for Admin ID: {} -> Active: {}", id, isActive);

        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("❌ Cannot update status: Admin not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        user.setActive(isActive);
        AdminUser updated = adminUserRepository.save(user);

        log.info("✅ Admin status updated -> ID: {}, Active: {}", updated.getId(), updated.isActive());
        return toDTO(updated);
    }

    // 🔹 Convert Entity to DTO
    private AdminUserDTO toDTO(AdminUser admin) {
        return new AdminUserDTO(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getRole(),
                admin.isActive(),
                admin.isBlocked()
        );
    }
}
