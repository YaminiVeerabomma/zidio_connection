package com.example.service;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // ✅ Save
    public SubscriptionPlanDTO createPlan(SubscriptionPlanDTO dto) {
        SubscriptionPlan plan = mapToEntity(dto);
        return mapToDTO(subscriptionPlanRepository.save(plan));
    }

    // ✅ Get by ID
    public SubscriptionPlanDTO getPlanById(Long id) {
        SubscriptionPlan plan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription Plan not found with id: " + id));
        return mapToDTO(plan);
    }

    // ✅ Update
    public SubscriptionPlanDTO updateSubscriptionPlan(Long id, SubscriptionPlanDTO dto) {
        SubscriptionPlan existing = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription plan not found with id " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setDurationInDays(dto.getDurationInDays());
        existing.setRozorpayorderId(dto.getRozorpayorderId());
        existing.setRozorpayPaymentId(dto.getRozorpayPaymentId());

        SubscriptionPlan updated = subscriptionPlanRepository.save(existing);
        return mapToDTO(updated);
    }

    // ✅ Delete
    public void deletePlan(Long id) {
        SubscriptionPlan existing = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription Plan not found with id: " + id));
        subscriptionPlanRepository.delete(existing);
    }

    // ✅ Get All (with pagination + sorting + exception handling)
    public Page<SubscriptionPlanDTO> getAllSubscriptionPlans(int page, int size, String sortBy, String direction) {
        try {
            Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<SubscriptionPlan> plansPage = subscriptionPlanRepository.findAll(pageable);

            if (plansPage.isEmpty()) {
                throw new ResourceNotFoundException("No subscription plans found for page: " + page);
            }

            return plansPage.map(this::mapToDTO);

        } catch (org.springframework.data.mapping.PropertyReferenceException ex) {
            throw new ResourceNotFoundException("Invalid sort field: " + sortBy);
        } catch (Exception ex) {
            throw new RuntimeException("Error while fetching subscription plans: " + ex.getMessage());
        }
    }

    // ✅ Filter by Price
    public List<SubscriptionPlanDTO> getPlansByPrice(Double price) {
        return subscriptionPlanRepository.findByPrice(price)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Filter by Duration
    public List<SubscriptionPlanDTO> getPlansByDuration(Integer duration) {
        return subscriptionPlanRepository.findByDurationInDays(duration)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Filter by Price + Duration
    public List<SubscriptionPlanDTO> getPlansByPriceAndDuration(Double price, Integer duration) {
        return subscriptionPlanRepository.findByPriceAndDurationInDays(price, duration)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Search by name/description
    public List<SubscriptionPlanDTO> searchPlans(String keyword) {
        return subscriptionPlanRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ----------------- 🔄 Mapper Methods -----------------

    private SubscriptionPlanDTO mapToDTO(SubscriptionPlan plan) {
        SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
        dto.setId(plan.getId());
        dto.setName(plan.getName());
        dto.setDescription(plan.getDescription());
        dto.setPrice(plan.getPrice());
        dto.setDurationInDays(plan.getDurationInDays());
        return dto;
    }

    private SubscriptionPlan mapToEntity(SubscriptionPlanDTO dto) {
        SubscriptionPlan plan = new SubscriptionPlan();
        plan.setId(dto.getId());
        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        plan.setPrice(dto.getPrice());
        plan.setDurationInDays(dto.getDurationInDays());
        return plan;
    }
}
