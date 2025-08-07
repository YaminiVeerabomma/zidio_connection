package com.example.service;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // GET all plans
    public List<SubscriptionPlanDTO> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // CREATE a new plan
    public SubscriptionPlanDTO createSubscription(SubscriptionPlanDTO dto) {
        SubscriptionPlan subscription = mapToEntity(dto);
        SubscriptionPlan saved = subscriptionPlanRepository.save(subscription);
        return mapToDTO(saved);
    }

    // GET plan by ID
    public SubscriptionPlanDTO getSubscriptionPlanById(Long id) {
        Optional<SubscriptionPlan> optional = subscriptionPlanRepository.findById(id);
        return optional.map(this::mapToDTO).orElse(null);
    }

    // UPDATE plan
    public SubscriptionPlanDTO updateSubscriptionPlan(Long id, SubscriptionPlanDTO dto) {
        Optional<SubscriptionPlan> optional = subscriptionPlanRepository.findById(id);
        if (optional.isPresent()) {
            SubscriptionPlan existing = optional.get();
            existing.setName(dto.getName());
            existing.setDescription(dto.getDescription());
            existing.setPrice(dto.getPrice());
            existing.setDurationInDays(dto.getDurationInDays());
            existing.setRozorpayorderId(dto.getRozorpayorderId());
            existing.setRozorpayPaymentId(dto.getRozorpayPaymentId());
            SubscriptionPlan updated = subscriptionPlanRepository.save(existing);
            return mapToDTO(updated);
        }
        return null;
    }

    // DELETE plan
    public boolean deleteSubscriptionPlan(Long id) {
        if (subscriptionPlanRepository.existsById(id)) {
            subscriptionPlanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // === Mapping Helpers ===

    private SubscriptionPlanDTO mapToDTO(SubscriptionPlan sub) {
        SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
        dto.setId(sub.getId());
        dto.setName(sub.getName());
        dto.setPrice(sub.getPrice());
        dto.setDescription(sub.getDescription());
        dto.setDurationInDays(sub.getDurationInDays());
        dto.setRozorpayorderId(sub.getRozorpayorderId());
        dto.setRozorpayPaymentId(sub.getRozorpayPaymentId());
        return dto;
    }

    private SubscriptionPlan mapToEntity(SubscriptionPlanDTO dto) {
        SubscriptionPlan sub = new SubscriptionPlan();
        sub.setId(dto.getId());
        sub.setName(dto.getName());
        sub.setPrice(dto.getPrice());
        sub.setDescription(dto.getDescription());
        sub.setDurationInDays(dto.getDurationInDays());
        sub.setRozorpayorderId(dto.getRozorpayorderId());
        sub.setRozorpayPaymentId(dto.getRozorpayPaymentId());
        return sub;
    }
}
