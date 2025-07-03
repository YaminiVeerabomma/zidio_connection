package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.JobPostDTO;
import com.example.entity.JobPost;
import com.example.repository.JobPostRepository;

@Service
public class JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	public JobPostDTO postJob(JobPostDTO dto){
		JobPost jobPost=new JobPost
				(dto.id,
				dto.jobTitle,
				dto.jobType,
				dto.jobLocation,
				dto.jobDescription,
				dto.comapanyName,
				dto.postedByEmail,
				dto.postedDate);
		JobPost saved=jobPostRepository.save(jobPost);
		return mapToDTO(saved);
		
	}
	private JobPostDTO mapToDTO(JobPost jobPost) {
		return new JobPostDTO(jobPost.getId(),
				jobPost.getJobTitle(),
				jobPost.getJobType(),
				jobPost.getJobLocation(),
				jobPost.getJobDescription(),
				jobPost.getCompanyName(),
				jobPost.getPosedtByEmail(),
				jobPost.getPostedDate());
	}
	

}
