package com.example.service;

import com.example.DTO.RecruiterDTO;
import com.example.Enum.Designation;
import com.example.entity.Recruiter;
import com.example.entity.User;
import com.example.repository.RecruiterRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Create or Update Recruiter
    public RecruiterDTO saveRecruiter(RecruiterDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Recruiter recruiter = convertToEntity(dto);
        recruiter.setUser(user); // @MapsId will map User.id → Recruiter.id

        return convertToDTO(recruiterRepository.save(recruiter));
    }
    public Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter) {
        return recruiterRepository.findById(id)
                .map(recruiter -> {
                    recruiter.setName(updatedRecruiter.getName());
                    recruiter.setEmail(updatedRecruiter.getEmail());
                    recruiter.setPhone(updatedRecruiter.getPhone());
                    recruiter.setCompanyName(updatedRecruiter.getCompanyName());
                    recruiter.setCompanyDescription(updatedRecruiter.getCompanyDescription());
                    recruiter.setCompanyWebsite(updatedRecruiter.getCompanyWebsite());
                    recruiter.setCompanyAddress(updatedRecruiter.getCompanyAddress());
                    recruiter.setCompanySize(updatedRecruiter.getCompanySize());
                    recruiter.setDesignation(updatedRecruiter.getDesignation());
                    return recruiterRepository.save(recruiter);
                })
                .orElseThrow(() -> new RuntimeException("Recruiter not found with id: " + id));
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
    public List<RecruiterDTO> getRecruitersByDesignation(Designation designation) {
        return recruiterRepository.findByDesignation(designation)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
  
    public List<RecruiterDTO> getRecruitersBySkill(String skill) {
        return recruiterRepository.findBySkillsContaining(skill)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ========== DTO Converters ==========
    private Recruiter convertToEntity(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter();
        recruiter.setName(dto.name);
        recruiter.setEmail(dto.email);
        recruiter.setPhone(dto.phone);
        recruiter.setCompanyName(dto.companyName);
        recruiter.setCompanyDescription(dto.companydiscription);
        recruiter.setCompanyWebsite(dto.companyWebsite);
        recruiter.setDesignation(dto.designation);
        return recruiter;
    }

    private RecruiterDTO convertToDTO(Recruiter recruiter) {
        return new RecruiterDTO(
                recruiter.getId(),
                recruiter.getName(),
                recruiter.getEmail(),
                recruiter.getCompanyName(),
                recruiter.getPhone(),
                recruiter.getCompanyDescription(),
                recruiter.getCompanyWebsite(),
                recruiter.getDesignation()
        );
    }
}
