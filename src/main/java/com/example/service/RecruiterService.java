package com.example.service;

import com.example.DTO.RecruiterDTO;
import com.example.Enum.Designation;
import com.example.entity.Recruiter;
import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.RecruiterRepository;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterService {

    private static final Logger log = LoggerFactory.getLogger(RecruiterService.class);

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private UserRepository userRepository;

    // ---------------- CREATE OR UPDATE RECRUITER ----------------
    public RecruiterDTO saveOrUpdateRecruiter(RecruiterDTO dto, Long userId) {
        log.info("📝 saveOrUpdateRecruiter called for userId={}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("❌ User not found with id={}", userId);
                    return new UserNotFoundException("User not found with id: " + userId);
                });

        // Check if recruiter exists
        Recruiter recruiter = recruiterRepository.findById(user.getId())
                .orElse(new Recruiter());

        // Update fields
        recruiter.setUser(user); // MapsId sets recruiter.id = user.id
        recruiter.setName(dto.name);
        recruiter.setEmail(dto.email);
        recruiter.setPhone(dto.phone);
        recruiter.setCompanyName(dto.companyName);
        recruiter.setCompanyDescription(dto.companyDescription);
        recruiter.setCompanyWebsite(dto.companyWebsite);
        recruiter.setCompanyAddress(dto.companyAddress);
        recruiter.setDesignation(dto.designation);

        Recruiter saved = recruiterRepository.save(recruiter);
        log.info("✅ Recruiter saved/updated successfully with id={}", saved.getId());

        return convertToDTO(saved);
    }

    // ---------------- GET RECRUITER BY ID ----------------
    public RecruiterDTO getRecruiterById(Long id) {
        log.info("🔍 getRecruiterById called for id={}", id);

        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("⚠️ Recruiter not found with id={}", id);
                    return new UserNotFoundException("Recruiter not found with id: " + id);
                });

        log.info("✅ Recruiter found with id={}", id);
        return convertToDTO(recruiter);
    }

    // ---------------- GET ALL RECRUITERS ----------------
    public List<RecruiterDTO> getAllRecruiters() {
        log.info("📚 getAllRecruiters called");

        List<RecruiterDTO> recruiters = recruiterRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        log.info("✅ Total recruiters fetched: {}", recruiters.size());
        return recruiters;
    }

    // ---------------- DELETE RECRUITER ----------------
    public void deleteRecruiter(Long id) {
        log.info("🗑️ deleteRecruiter called for id={}", id);

        if (!recruiterRepository.existsById(id)) {
            log.warn("⚠️ Recruiter not found with id={}", id);
            throw new UserNotFoundException("Recruiter not found with id: " + id);
        }

        recruiterRepository.deleteById(id);
        log.info("✅ Recruiter deleted successfully with id={}", id);
    }

    // ---------------- GET RECRUITERS BY DESIGNATION ----------------
    public List<RecruiterDTO> getRecruitersByDesignation(Designation designation) {
        log.info("🎯 getRecruitersByDesignation called for designation={}", designation);

        List<RecruiterDTO> recruiters = recruiterRepository.findByDesignation(designation)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (recruiters.isEmpty()) {
            log.warn("⚠️ No recruiters found with designation={}", designation);
            throw new UserNotFoundException("No recruiters found with designation: " + designation);
        }

        log.info("✅ {} recruiter(s) found with designation={}", recruiters.size(), designation);
        return recruiters;
    }

    // ---------------- DTO CONVERTER ----------------
    private RecruiterDTO convertToDTO(Recruiter recruiter) {
        log.debug("🔧 convertToDTO called for recruiterId={}", recruiter.getId());

        return new RecruiterDTO(
                recruiter.getId(),
                recruiter.getName(),
                recruiter.getEmail(),
                recruiter.getCompanyName(),
                recruiter.getPhone(),
                recruiter.getCompanyDescription(),
                recruiter.getCompanyWebsite(),
                recruiter.getCompanyAddress(),
                recruiter.getDesignation()
        );
    }
}
