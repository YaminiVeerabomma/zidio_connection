package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Enum.Role;
import com.example.entity.SystemUser;
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    SystemUser findByEmail(String email);
    List<SystemUser> findByRole(Role role);
}
