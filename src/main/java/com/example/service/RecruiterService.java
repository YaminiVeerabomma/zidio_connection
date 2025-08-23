package com.example.service;

import com.example.DTO.RecruiterDTO;
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
