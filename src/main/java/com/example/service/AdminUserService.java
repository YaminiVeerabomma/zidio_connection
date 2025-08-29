package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.AdminUserDTO;
import com.example.Enum.Role;
import com.example.entity.AdminUser;
import com.example.entity.User;
import com.example.repository.AdminUserRepository;
import com.example.repository.UserRepository;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private UserRepository userRepository;

    // Create AdminUser
    public AdminUserDTO createAdmin(AdminUserDTO dto) {
        // Find linked User by email
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assign ADMIN role
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        // Map DTO to Entity
        AdminUser admin = new AdminUser();
        admin.setUser(user);
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setRole(Role.ADMIN);
        admin.setActive(true);
        admin.setBlocked(false);

        admin = adminUserRepository.save(admin);
        return toDTO(admin);
    }

    public AdminUserDTO updateAdmin(Long id, AdminUserDTO dto) {
        AdminUser admin = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());

        // Update role
        if(dto.getRole() != null) {
            admin.setRole(dto.getRole());
        }

        // Update blocked status
        admin.setBlocked(dto.isBlocked());

        // Update active status if needed
        admin.setActive(dto.isActive());

        adminUserRepository.save(admin);

        return toDTO(admin);
    }


    // Existing methods: getAllUsers, blockUser, unBlockUser, getUserByRole, updateStatus
    public List<AdminUserDTO> getAllUsers() {
        return adminUserRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AdminUserDTO blockUser(Long id) {
        AdminUser user = adminUserRepository.findById(id).orElse(null);
        if(user != null) {
            user.setBlocked(true);
            adminUserRepository.save(user);
            return toDTO(user);
        }
        return null;
    }

    public AdminUserDTO unBlockUser(Long id) {
        AdminUser user = adminUserRepository.findById(id).orElse(null);
        if(user != null) {
            user.setBlocked(false);
            adminUserRepository.save(user);
            return toDTO(user);
        }
        return null;
    }

    public List<AdminUserDTO> getUserByRole(Role role) {
        return adminUserRepository.findByRole(role).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AdminUserDTO updateStatus(Long id, boolean isActive) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(isActive);
        adminUserRepository.save(user);
        return toDTO(user);
    }

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
