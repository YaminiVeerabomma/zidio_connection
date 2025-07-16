package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.entity.UserPaymentStatus;



public interface UserPaymentStatusRepository  extends JpaRepository<UserPaymentStatus,Long>{

	Optional<UserPaymentStatusDTO> findByUserId(Long id);

}
