package com.example.service;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.SubscriptionPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionPlanService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionPlanService.class);

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // ----------------- CREATE -----------------
    public SubscriptionPlanDTO createPlan(SubscriptionPlanDTO dto) {
        log.info("🆕 createPlan called for planName={}", dto.getName());
        SubscriptionPlan plan = mapToEntity(dto);
        SubscriptionPlan saved = subscriptionPlanRepository.save(plan);
        log.info("✅ Plan created successfully with planId={}", saved.getId());
        return mapToDTO(saved);
    }

    // ----------------- GET BY ID -----------------
    public SubscriptionPlanDTO getPlanById(Long id) {
        log.info("🔍 getPlanById called for planId={}", id);
        SubscriptionPlan plan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("⚠️ Plan not found with id={}", id);
                    return new ResourceNotFoundException("Subscription Plan not found with id: " + id);
                });
        log.info("✅ Plan fetched successfully with planId={}", plan.getId());
        return mapToDTO(plan);
    }

    // ----------------- UPDATE -----------------
    public SubscriptionPlanDTO updateSubscriptionPlan(Long id, SubscriptionPlanDTO dto) {
        log.info("📝 updateSubscriptionPlan called for planId={}", id);

        SubscriptionPlan existing = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("⚠️ Plan not found with id={}", id);
                    return new ResourceNotFoundException("Subscription plan not found with id " + id);
                });

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setDurationInDays(dto.getDurationInDays());
        existing.setRozorpayorderId(dto.getRozorpayorderId());
        existing.setRozorpayPaymentId(dto.getRozorpayPaymentId());

        SubscriptionPlan updated = subscriptionPlanRepository.save(existing);
        log.info("✅ Plan updated successfully with planId={}", updated.getId());
        return mapToDTO(updated);
    }

    // ----------------- DELETE -----------------
    public void deletePlan(Long id) {
        log.info("🗑️ deletePlan called for planId={}", id);

        SubscriptionPlan existing = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("⚠️ Plan not found with id={}", id);
                    return new ResourceNotFoundException("Subscription Plan not found with id: " + id);
                });

        subscriptionPlanRepository.delete(existing);
        log.info("✅ Plan deleted successfully with planId={}", id);
    }

    // ----------------- GET ALL (Pagination + Sorting) -----------------
    public Page<SubscriptionPlanDTO> getAllSubscriptionPlans(int page, int size, String sortBy, String direction) {
        log.info("📚 getAllSubscriptionPlans called for page={}, size={}, sortBy={}, direction={}", page, size, sortBy, direction);
        try {
            Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<SubscriptionPlan> plansPage = subscriptionPlanRepository.findAll(pageable);

            if (plansPage.isEmpty()) {
                log.warn("⚠️ No subscription plans found for page={}", page);
                throw new ResourceNotFoundException("No subscription plans found for page: " + page);
            }

            log.info("✅ {} plan(s) fetched successfully for page={}", plansPage.getContent().size(), page);
            return plansPage.map(this::mapToDTO);

        } catch (org.springframework.data.mapping.PropertyReferenceException ex) {
            log.error("❌ Invalid sort field: {}", sortBy);
            throw new ResourceNotFoundException("Invalid sort field: " + sortBy);
        } catch (Exception ex) {
            log.error("❌ Error while fetching subscription plans: {}", ex.getMessage());
            throw new RuntimeException("Error while fetching subscription plans: " + ex.getMessage());
        }
    }

    // ----------------- FILTERS -----------------
    public List<SubscriptionPlanDTO> getPlansByPrice(Double price) {
        log.info("🎯 getPlansByPrice called for price={}", price);
        List<SubscriptionPlanDTO> plans = subscriptionPlanRepository.findByPrice(price)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        log.info("✅ {} plan(s) found for price={}", plans.size(), price);
        return plans;
    }

    public List<SubscriptionPlanDTO> getPlansByDuration(Integer duration) {
        log.info("🎯 getPlansByDuration called for duration={} days", duration);
        List<SubscriptionPlanDTO> plans = subscriptionPlanRepository.findByDurationInDays(duration)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        log.info("✅ {} plan(s) found for duration={} days", plans.size(), duration);
        return plans;
    }

    public List<SubscriptionPlanDTO> getPlansByPriceAndDuration(Double price, Integer duration) {
        log.info("🎯 getPlansByPriceAndDuration called for price={}, duration={} days", price, duration);
        List<SubscriptionPlanDTO> plans = subscriptionPlanRepository.findByPriceAndDurationInDays(price, duration)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        log.info("✅ {} plan(s) found for price={}, duration={} days", plans.size(), price, duration);
        return plans;
    }

    public List<SubscriptionPlanDTO> searchPlans(String keyword) {
        log.info("🔍 searchPlans called for keyword='{}'", keyword);
        List<SubscriptionPlanDTO> plans = subscriptionPlanRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        log.info("✅ {} plan(s) found for keyword='{}'", plans.size(), keyword);
        return plans;
    }

    // ----------------- 🔄 Mapper Methods -----------------
    private SubscriptionPlanDTO mapToDTO(SubscriptionPlan plan) {
        log.debug("🔧 mapToDTO called for planId={}", plan.getId());
        SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
        dto.setId(plan.getId());
        dto.setName(plan.getName());
        dto.setDescription(plan.getDescription());
        dto.setPrice(plan.getPrice());
        dto.setDurationInDays(plan.getDurationInDays());
        return dto;
    }

    private SubscriptionPlan mapToEntity(SubscriptionPlanDTO dto) {
        log.debug("🔧 mapToEntity called for planName={}", dto.getName());
        SubscriptionPlan plan = new SubscriptionPlan();
        plan.setId(dto.getId());
        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        plan.setPrice(dto.getPrice());
        plan.setDurationInDays(dto.getDurationInDays());
        return plan;
    }
}
