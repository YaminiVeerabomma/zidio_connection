package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;


@Service
public class UserPaymentStatusService {
	
	@Autowired
	private UserPaymentStatusRepository userPaymentStatusRepository;
	
	public UserPaymentStatusDTO assignSubscriptionPlan(UserPaymentStatusDTO dto) {
		UserPaymentStatus paymentStatus  = new UserPaymentStatus();
		paymentStatus.setUserId(dto.userId);
		paymentStatus.setPlanId(dto.planId);
		paymentStatus.setSubscriptionStart(dto.subscriptionStart);
		paymentStatus.setSubscriptionEnd(dto.subscriptionEnd);
		paymentStatus.setTransactionId(dto.transactionId);
		paymentStatus.setStatus(dto.status);
		
//		userPaymentStatusRepository.save(paymentStatus);
		
		UserPaymentStatus saved = userPaymentStatusRepository.save(paymentStatus);
		dto.id=saved.getId();
		dto.subscriptionStart=saved.getSubscriptionStart();
		dto.subscriptionEnd=saved.getSubscriptionEnd();
		dto.transactionId=saved.getTransactionId();
		dto.status=saved.getStatus();
		return dto;
	}
	
	public Optional<UserPaymentStatusDTO> getStatusByUserId(Long id){
		return userPaymentStatusRepository.findByUserId(id).map(status->{
			UserPaymentStatusDTO dto = new UserPaymentStatusDTO();
			dto.id=status.getId();
			dto.planId=status.getPlanId();
			dto.userId=status.getUserId();
			dto.subscriptionStart=status.getSubscriptionStart();
			dto.subscriptionEnd=status.getSubscriptionEnd();
			dto.status=status.getStatus();
			dto.transactionId=status.getTransactionId();
			return dto;
			
		});
	}
	
	

}
