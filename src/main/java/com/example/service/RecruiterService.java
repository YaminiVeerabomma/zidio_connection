package com.example.service;

import com.example.DTO.RecruiterDTO;
import com.example.Enum.Designation;
import com.example.entity.Recruiter;
import com.example.entity.User;
import com.example.repository.RecruiterRepository;
import com.example.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Create or Update Recruiter
    public RecruiterDTO saveOrUpdateRecruiter(RecruiterDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if Recruiter exists for this user
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

        // Save entity
        return convertToDTO(recruiterRepository.save(recruiter));
    }

    // 🔹 Get Recruiter by ID
    public RecruiterDTO getRecruiterById(Long id) {
        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found with id: " + id));
        return convertToDTO(recruiter);
    }

    // 🔹 Get All Recruiters
    public List<RecruiterDTO> getAllRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Delete Recruiter
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }

    // 🔹 Get by Designation
    public List<RecruiterDTO> getRecruitersByDesignation(Designation designation) {
        return recruiterRepository.findByDesignation(designation)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ========== DTO Converters ==========
    private RecruiterDTO convertToDTO(Recruiter recruiter) {
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
