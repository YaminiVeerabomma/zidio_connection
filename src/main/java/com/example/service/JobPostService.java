package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

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
		JobPost jobPost = new JobPost(
			dto.id,
			dto.jobTitle,
			dto.jobType,
			dto.jobLocation,
			dto.jobDescription,
			dto.companyName,
			dto.postedByEmail,
			dto.postedDate
		);
		JobPost saved = jobPostRepository.save(jobPost);
		return mapToDTO(saved);
	}

	public List<JobPostDTO> getByPostedByEmail(String email) {
		return jobPostRepository.findByPostedByEmail(email)
			.stream().map(this::mapToDTO)
			.collect(Collectors.toList());
	}

	public List<JobPostDTO> getByJobTitle(String jobtitle) {
		return jobPostRepository.findByJobTitle(jobtitle)
			.stream().map(this::mapToDTO)
			.collect(Collectors.toList());
	}

	public List<JobPostDTO> getByJobType(String jobtype) {
		return jobPostRepository.findByJobType(jobtype)
			.stream().map(this::mapToDTO)
			.collect(Collectors.toList());
	}

	public List<JobPostDTO> getByCompanyName(String companyName) {
		return jobPostRepository.findByCompanyName(companyName)
			.stream().map(this::mapToDTO)
			.collect(Collectors.toList());
	}

	private JobPostDTO mapToDTO(JobPost jobPost) {
		return new JobPostDTO(
			jobPost.getId(),
			jobPost.getJobTitle(),
			jobPost.getJobType(),
			jobPost.getJobLocation(),
			jobPost.getJobDescription(),
			jobPost.getCompanyName(),
			jobPost.getPostedByEmail(),
			jobPost.getPostedDate()
		);
	}
}


