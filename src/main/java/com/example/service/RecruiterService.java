package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.RecruiterDTO;
import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;
@Service
public class RecruiterService {
	@Autowired
	 private RecruiterRepository recruiterRepository;
	
	public RecruiterDTO createrRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter(dto.id,dto.name,dto.email,dto.companyName,dto.phone,dto.companydiscription,dto.companyWebsite);
        Recruiter saved = ( Recruiter)recruiterRepository.save(recruiter);
        return dto;
    }

//    public RecruiterDTO getRecruiterByEmail(String email) {
//        Optional<Recruiter> recruiter = recruiterRepository.findByEmail(email);
//         if(recruiter==null)return null;
//        return new  RecruiterDTO(
//        		recruiter.getId(),
//        		recruiter.getEmail(),
//        		recruiter.getPhone(),
//        		recruiter.getCompanyName(),
//        		recruiter.getCompanydescription(),
//        		recruiter.getCompanyWebsit());
//    }
//    
//    public RecruiterDTO getRecruiterById(Long id) {
//    Recruiter recruiter=recruiterRepository.findById(id);
//    if(recruiter==null)return null;
//    return new  RecruiterDTO(
//    		recruiter.getId(),
//    		recruiter.getEmail(),
//    		recruiter.getPhone(),
//    		recruiter.getCompanyName(),
//    		recruiter.getCompanydescription(),
//    		recruiter.getCompanyWebsit());
//
//    	
//    }
//
//   
}
		



