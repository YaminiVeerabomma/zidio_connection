package com.example.service;

import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    // Create or Update Recruiter
    public Recruiter saveOrUpdateRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    // Get all recruiters
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    // Get recruiter by ID
    public Optional<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id);
    }

    // Delete recruiter by ID
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }
}
