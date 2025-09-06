package com.example.service;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.Enum.PaidStatus;
import com.example.entity.UserPaymentStatus;
import com.example.exception.UserPaymentStatusNotFoundException;
import com.example.repository.UserPaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        return convertToDTO(saved);
    }

    // ✅ Get by userId
    public UserPaymentStatusDTO getByUserId(Long userId) {
        return userPaymentStatusRepository.findByUserId(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new UserPaymentStatusNotFoundException(
                        "No payment status found for userId: " + userId
                ));
    }

    // ✅ Active subscriptions
    public List<UserPaymentStatusDTO> getActiveSubscriptions() {
        List<UserPaymentStatusDTO> subscriptions = userPaymentStatusRepository
                .findBySubscriptionEndAfter(LocalDate.now())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            throw new UserPaymentStatusNotFoundException("No active subscriptions found");
        }
        return subscriptions;
    }

    // ✅ Expired subscriptions
    public List<UserPaymentStatusDTO> getExpiredSubscriptions() {
        List<UserPaymentStatusDTO> subscriptions = userPaymentStatusRepository
                .findBySubscriptionEndBefore(LocalDate.now())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            throw new UserPaymentStatusNotFoundException("No expired subscriptions found");
        }
        return subscriptions;
    }

    // ✅ By planId
    public List<UserPaymentStatusDTO> getByPlanId(Long planId) {
        List<UserPaymentStatusDTO> plans = userPaymentStatusRepository.findByPlanId(planId)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (plans.isEmpty()) {
            throw new UserPaymentStatusNotFoundException("No subscriptions found for planId: " + planId);
        }
        return plans;
    }

    // ✅ Delete by userId
    public void deleteByUserId(Long userId) {
        if (!userPaymentStatusRepository.existsByUserId(userId)) {
            throw new UserPaymentStatusNotFoundException("No payment record found for userId: " + userId);
        }
        userPaymentStatusRepository.deleteByUserId(userId);
    }

    // ✅ By paid status
    public List<UserPaymentStatusDTO> getByPaidStatus(PaidStatus paidStatus) {
        List<UserPaymentStatusDTO> payments = userPaymentStatusRepository.findByPaidStatus(paidStatus)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (payments.isEmpty()) {
            throw new UserPaymentStatusNotFoundException("No payments found with status: " + paidStatus);
        }
        return payments;
    }
}
