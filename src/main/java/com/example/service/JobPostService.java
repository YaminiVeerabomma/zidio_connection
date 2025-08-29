package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.JobPostDTO;
import com.example.Enum.JobType;
import com.example.Enum.RequiredExperience;
import com.example.entity.JobPost;
import com.example.repository.JobPostRepository;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    // ✅ Save job post
    public JobPostDTO postJob(JobPostDTO dto) {
        JobPost jobPost = new JobPost();
        jobPost.setJobTitle(dto.getJobTitle());
        jobPost.setJobType(dto.getJobType());
        jobPost.setJobLocation(dto.getJobLocation());
        jobPost.setJobDescription(dto.getJobDescription());
        jobPost.setCompanyName(dto.getCompanyName());
        jobPost.setPostedByEmail(dto.getPostedByEmail());
        jobPost.setPostedDate(dto.getPostedDate());
        jobPost.setSalaryMin(dto.getSalaryMin());
        jobPost.setSalaryMax(dto.getSalaryMax());
        jobPost.setEducation(dto.getEducation());
        jobPost.setSkills(dto.getSkills());
        jobPost.setRequiredExperience (dto.getRequiredExperience());
        jobPost.setActive(dto.isActive());
        jobPost.setNumberOfVacancies(dto.getNumberOfVacancies());

        JobPost saved = jobPostRepository.save(jobPost);
        return mapToDTO(saved);
    }
 // ✅ Update Job Post
    public JobPostDTO updateJobPost(Long id, JobPostDTO dto) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Post not found with id " + id));

        // Update fields
        jobPost.setJobTitle(dto.getJobTitle());
        jobPost.setJobType(dto.getJobType());
        jobPost.setJobLocation(dto.getJobLocation());
        jobPost.setJobDescription(dto.getJobDescription());
        jobPost.setCompanyName(dto.getCompanyName());
        jobPost.setPostedByEmail(dto.getPostedByEmail());
        jobPost.setPostedDate(dto.getPostedDate());
        jobPost.setSalaryMin(dto.getSalaryMin());
        jobPost.setSalaryMax(dto.getSalaryMax());
        jobPost.setEducation(dto.getEducation());
        jobPost.setSkills(dto.getSkills());
        jobPost.setRequiredExperience(dto.getRequiredExperience());
        jobPost.setActive(dto.isActive());
        jobPost.setNumberOfVacancies(dto.getNumberOfVacancies());

        JobPost updated = jobPostRepository.save(jobPost);
        return mapToDTO(updated);
    }


    // ✅ Get jobs by email
    public List<JobPostDTO> getByPostedByEmail(String email) {
        return jobPostRepository.findByPostedByEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get jobs by title
    public List<JobPostDTO> getByJobTitle(String jobTitle) {
        return jobPostRepository.findByJobTitle(jobTitle)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get jobs by job type
    public List<JobPostDTO> getByJobType(JobType jobType) {
        return jobPostRepository.findByJobType(jobType)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get jobs by company name
    public List<JobPostDTO> getByCompanyName(String companyName) {
        return jobPostRepository.findByCompanyName(companyName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 // Get all active jobs
    public List<JobPostDTO> getActiveJobs() {
        return jobPostRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<JobPostDTO> getByJobLocation(String jobLocation) {
        return jobPostRepository.findByJobLocation(jobLocation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<JobPostDTO> getByRequiredExperience(RequiredExperience requiredExperience) {
        return jobPostRepository.findByRequiredExperience(requiredExperience)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }



    // ✅ Convert Entity to DTO
    private JobPostDTO mapToDTO(JobPost job) {
        return new JobPostDTO(
                job.getId(),
                job.getJobTitle(),
                job.getJobType(),
                job.getJobLocation(),
                job.getJobDescription(),
                job.getCompanyName(),
                job.getPostedByEmail(),
                job.getPostedDate(),
                job.getRequiredExperience (),
                job.getSalaryMin(),
                job.getSalaryMax() ,
                job.getEducation(),
                job.getSkills(),
                job.isActive(),
                job.getNumberOfVacancies()
               );
    }
}



