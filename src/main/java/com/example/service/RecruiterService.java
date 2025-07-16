package com.example.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.RecruiterDTO;
import com.example.Enum.Designation;
import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    public RecruiterDTO createrRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter(
            dto.id,
            dto.name,
            dto.email,
            dto.companyName,
            dto.phone,
            dto.companydiscription,
            dto.companyWebsite,
            dto.designation
        );
        recruiter = recruiterRepository.save(recruiter);
        return mapToDTO(recruiter);
    }

    public RecruiterDTO getRecruiterByEmail(String email) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findByEmail(email);
        if (!optionalRecruiter.isPresent()) return null;
        return mapToDTO(optionalRecruiter.get());
    }

    public RecruiterDTO getRecruiterById(Long id) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(id);
        if (!optionalRecruiter.isPresent()) return null;
        return mapToDTO(optionalRecruiter.get());
    }
    public List<RecruiterDTO> getRecruitersByDesignation(Designation designation) {
        List<Recruiter> recruiters = recruiterRepository.findByDesignation(designation);
        return recruiters.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

 

    private RecruiterDTO mapToDTO(Recruiter recruiter) {
        return new RecruiterDTO(
            recruiter.getId(),
            recruiter.getName(),
            recruiter.getEmail(),
            recruiter.getCompanyName(),
            recruiter.getPhone(),
            recruiter.getCompanydescription(),
            recruiter.getCompanyWebsit(),
            recruiter.getDesignation()
        );
    }
}

