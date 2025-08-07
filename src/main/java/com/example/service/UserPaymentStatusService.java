package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;


@Service
public class UserPaymentStatusService {
	
	@Autowired
	private UserPaymentStatusRepository userPaymentStatusRepository;
	
	
	    public UserPaymentStatusDTO create(UserPaymentStatusDTO dto) {
	        UserPaymentStatus payment = mapToEntity(dto);
	        UserPaymentStatus saved =userPaymentStatusRepository.save(payment);
	        return mapToDTO(saved);
	    }

	    public List<UserPaymentStatusDTO> getAll() {
	        return userPaymentStatusRepository.findAll()
	                .stream()
	                .map(this::mapToDTO)
	                .collect(Collectors.toList());
	    }

	    public UserPaymentStatusDTO getById(Long id) {
	        Optional<UserPaymentStatus> optional = userPaymentStatusRepository.findById(id);
	        return optional.map(this::mapToDTO).orElse(null);
	    }

	    public UserPaymentStatusDTO update(Long id, UserPaymentStatusDTO dto) {
	        Optional<UserPaymentStatus> optional = userPaymentStatusRepository.findById(id);
	        if (optional.isPresent()) {
	            UserPaymentStatus existing = optional.get();
	            existing.setPlanId(dto.getPlanId());
	            existing.setUserId(dto.getUserId());
	            existing.setSubscriptionStart(dto.getSubscriptionStart());
	            existing.setSubscriptionEnd(dto.getSubscriptionEnd());
	            existing.setStatus(dto.getStatus());
	            existing.setTransactionId(dto.getTransactionId());
	            return mapToDTO(userPaymentStatusRepository.save(existing));
	        }
	        return null;
	    }

	    public boolean delete(Long id) {
	        if (userPaymentStatusRepository.existsById(id)) {
	        	userPaymentStatusRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }

	    // Mapping helpers

	    private UserPaymentStatusDTO mapToDTO(UserPaymentStatus entity) {
	        return new UserPaymentStatusDTO(
	                entity.getId(),
	                entity.getPlanId(),
	                entity.getUserId(),
	                entity.getSubscriptionStart(),
	                entity.getSubscriptionEnd(),
	                entity.getStatus(),
	                entity.getTransactionId()
	        );
	    }

	    private UserPaymentStatus mapToEntity(UserPaymentStatusDTO dto) {
	        UserPaymentStatus entity = new UserPaymentStatus();
	        entity.setId(dto.getId());
	        entity.setPlanId(dto.getPlanId());
	        entity.setUserId(dto.getUserId());
	        entity.setSubscriptionStart(dto.getSubscriptionStart());
	        entity.setSubscriptionEnd(dto.getSubscriptionEnd());
	        entity.setStatus(dto.getStatus());
	        entity.setTransactionId(dto.getTransactionId());
	        return entity;
	    }
	}
