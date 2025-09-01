package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Create AdminUser
    public AdminUserDTO createAdmin(AdminUserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + dto.getEmail()));

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

        return toDTO(adminUserRepository.save(admin));
    }

    // 🔹 Update AdminUser
    public AdminUserDTO updateAdmin(Long id, AdminUserDTO dto) {
        AdminUser admin = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Admin not found with id: " + id));

        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());

        if (dto.getRole() != null) {
            admin.setRole(dto.getRole());
        }

        admin.setBlocked(dto.isBlocked());
        admin.setActive(dto.isActive());

        return toDTO(adminUserRepository.save(admin));
    }

    // 🔹 Get All Admin Users
    public List<AdminUserDTO> getAllUsers() {
        return adminUserRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Block User
    public AdminUserDTO blockUser(Long id) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setBlocked(true);
        return toDTO(adminUserRepository.save(user));
    }

    // 🔹 Unblock User
    public AdminUserDTO unBlockUser(Long id) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setBlocked(false);
        return toDTO(adminUserRepository.save(user));
    }

    // 🔹 Get Users by Role
    public List<AdminUserDTO> getUserByRole(Role role) {
        return adminUserRepository.findByRole(role)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Update Active Status
    public AdminUserDTO updateStatus(Long id, boolean isActive) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setActive(isActive);
        return toDTO(adminUserRepository.save(user));
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
