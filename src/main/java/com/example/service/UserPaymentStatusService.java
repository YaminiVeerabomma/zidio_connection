package com.example.service;

import com.example.DTO.UserPaymentStatusDTO;
import com.example.Enum.PaidStatus;
import com.example.entity.UserPaymentStatus;
import com.example.exception.UserPaymentStatusNotFoundException;
import com.example.repository.UserPaymentStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPaymentStatusService {

    private static final Logger log = LoggerFactory.getLogger(UserPaymentStatusService.class);

    @Autowired
    private UserPaymentStatusRepository userPaymentStatusRepository;

    // 🔹 Convert Entity → DTO
    private UserPaymentStatusDTO convertToDTO(UserPaymentStatus entity) {
        if (entity == null) return null;
        log.debug("🔧 convertToDTO called for userPaymentStatusId={}", entity.getId());
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
        log.debug("🔧 convertToEntity called for userId={}", dto.getUserId());
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

    // ----------------- SAVE / UPDATE -----------------
    public UserPaymentStatusDTO save(UserPaymentStatusDTO dto) {
        log.info("💾 save called for userId={} and planId={}", dto.getUserId(), dto.getPlanId());
        UserPaymentStatus entity = convertToEntity(dto);
        UserPaymentStatus saved = userPaymentStatusRepository.save(entity);
        log.info("✅ Payment status saved with id={}", saved.getId());
        return convertToDTO(saved);
    }

    // ----------------- GET BY USER ID -----------------
    public UserPaymentStatusDTO getByUserId(Long userId) {
        log.info("🔍 getByUserId called for userId={}", userId);
        return userPaymentStatusRepository.findByUserId(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> {
                    log.warn("⚠️ No payment status found for userId={}", userId);
                    return new UserPaymentStatusNotFoundException(
                            "No payment status found for userId: " + userId
                    );
                });
    }

    // ----------------- ACTIVE SUBSCRIPTIONS -----------------
    public List<UserPaymentStatusDTO> getActiveSubscriptions() {
        log.info("🔄 getActiveSubscriptions called");
        List<UserPaymentStatusDTO> subscriptions = userPaymentStatusRepository
                .findBySubscriptionEndAfter(LocalDate.now())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            log.warn("⚠️ No active subscriptions found");
            throw new UserPaymentStatusNotFoundException("No active subscriptions found");
        }
        log.info("✅ {} active subscription(s) found", subscriptions.size());
        return subscriptions;
    }

    // ----------------- EXPIRED SUBSCRIPTIONS -----------------
    public List<UserPaymentStatusDTO> getExpiredSubscriptions() {
        log.info("🔄 getExpiredSubscriptions called");
        List<UserPaymentStatusDTO> subscriptions = userPaymentStatusRepository
                .findBySubscriptionEndBefore(LocalDate.now())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            log.warn("⚠️ No expired subscriptions found");
            throw new UserPaymentStatusNotFoundException("No expired subscriptions found");
        }
        log.info("✅ {} expired subscription(s) found", subscriptions.size());
        return subscriptions;
    }

    // ----------------- GET BY PLAN ID -----------------
    public List<UserPaymentStatusDTO> getByPlanId(Long planId) {
        log.info("🎯 getByPlanId called for planId={}", planId);
        List<UserPaymentStatusDTO> plans = userPaymentStatusRepository.findByPlanId(planId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (plans.isEmpty()) {
            log.warn("⚠️ No subscriptions found for planId={}", planId);
            throw new UserPaymentStatusNotFoundException("No subscriptions found for planId: " + planId);
        }
        log.info("✅ {} subscription(s) found for planId={}", plans.size(), planId);
        return plans;
    }

    // ----------------- DELETE BY USER ID -----------------
    public void deleteByUserId(Long userId) {
        log.info("🗑️ deleteByUserId called for userId={}", userId);
        if (!userPaymentStatusRepository.existsByUserId(userId)) {
            log.warn("⚠️ No payment record found for userId={}", userId);
            throw new UserPaymentStatusNotFoundException("No payment record found for userId: " + userId);
        }
        userPaymentStatusRepository.deleteByUserId(userId);
        log.info("✅ Payment record deleted for userId={}", userId);
    }

    // ----------------- GET BY PAID STATUS -----------------
    public List<UserPaymentStatusDTO> getByPaidStatus(PaidStatus paidStatus) {
        log.info("🎯 getByPaidStatus called for status={}", paidStatus);
        List<UserPaymentStatusDTO> payments = userPaymentStatusRepository.findByPaidStatus(paidStatus)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (payments.isEmpty()) {
            log.warn("⚠️ No payments found with status={}", paidStatus);
            throw new UserPaymentStatusNotFoundException("No payments found with status: " + paidStatus);
        }
        log.info("✅ {} payment(s) found with status={}", payments.size(), paidStatus);
        return payments;
    }
}
