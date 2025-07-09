package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.SystemUserDTO;
import com.example.Enum.Role;
import com.example.entity.SystemUser;
import com.example.repository.SystemUserRepository;

@Service
public class AdminService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    public List<SystemUserDTO> getAllUsers() {
        return systemUserRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void blockUser(Long id) {
        SystemUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(false);
        systemUserRepository.save(user);
    }

    public void unBlockUser(Long id) {
        SystemUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(true);
        systemUserRepository.save(user);
    }

    public List<SystemUserDTO> getUserByRole(Role role) {
        return systemUserRepository.findByRole(role).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SystemUserDTO updateStatus(Long id, Boolean isActive) {
        SystemUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(isActive);
        return toDTO(systemUserRepository.save(user));
    }

    private SystemUserDTO toDTO(SystemUser user) {
        return new SystemUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive()
        );
    }
}

