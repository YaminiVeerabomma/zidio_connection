package com.example.service;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.Enum.PaidStatus;
import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPaymentStatusService {

    @Autowired
    private UserPaymentStatusRepository userPaymentStatusRepository;

    // 🔹 Convert Entity → DTO
    private UserPaymentStatusDTO convertToDTO(UserPaymentStatus entity) {
        if (entity == null) return null;
        return new UserPaymentStatusDTO(
                entity.getId(),
                entity.getPlanId(),
                entity.getUserId(),
                entity.getSubscriptionStart(),
                entity.getSubscriptionEnd(),
                entity.getPaidStatus(),
                entity.getTransactionId()
        );
    }

    // 🔹 Convert DTO → Entity
    private UserPaymentStatus convertToEntity(UserPaymentStatusDTO dto) {
        if (dto == null) return null;
        return new UserPaymentStatus(
                dto.getId(),
                dto.getPlanId(),
                dto.getUserId(),
                dto.getSubscriptionStart(),
                dto.getSubscriptionEnd(),
                dto.getPaidStatus(),
                dto.getTransactionId()
        );
    }

    // ✅ Save or update
    public UserPaymentStatusDTO save(UserPaymentStatusDTO dto) {
        UserPaymentStatus entity = convertToEntity(dto);
        UserPaymentStatus saved = userPaymentStatusRepository.save(entity);
        return convertToDTO(saved);   // ✅ FIXED: pass entity, not dto
    }

    // ✅ Get by userId
    public Optional<UserPaymentStatusDTO> getByUserId(Long userId) {
        return userPaymentStatusRepository.findByUserId(userId).map(this::convertToDTO);
    }

    // ✅ Active subscriptions
    public List<UserPaymentStatusDTO> getActiveSubscriptions() {
        return userPaymentStatusRepository.findBySubscriptionEndAfter(LocalDate.now())
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // ✅ Expired subscriptions
    public List<UserPaymentStatusDTO> getExpiredSubscriptions() {
        return userPaymentStatusRepository.findBySubscriptionEndBefore(LocalDate.now())
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // ✅ By planId
    public List<UserPaymentStatusDTO> getByPlanId(Long planId) {
        return userPaymentStatusRepository.findByPlanId(planId)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // ✅ Delete by userId
    public void deleteByUserId(Long userId) {
        userPaymentStatusRepository.deleteByUserId(userId);
    }
    public List<UserPaymentStatusDTO> getByPaidStatus(PaidStatus paidStatus) {
        return   userPaymentStatusRepository.findByPaidStatus(paidStatus).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
