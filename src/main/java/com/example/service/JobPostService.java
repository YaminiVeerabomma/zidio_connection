package com.example.service;

import com.example.DTO.JobPostDTO;
import com.example.Enum.JobType;
import com.example.Enum.RequiredExperience;
import com.example.entity.JobPost;
import com.example.exception.JobPostNotFoundException;
import com.example.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    // ✅ Save job post
    public JobPostDTO postJob(JobPostDTO dto) {
        if (dto.getJobTitle() == null || dto.getJobTitle().isBlank()) {
            throw new IllegalArgumentException("Job title cannot be null or empty");
        }

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
        jobPost.setRequiredExperience(dto.getRequiredExperience());
        jobPost.setActive(dto.isActive());
        jobPost.setNumberOfVacancies(dto.getNumberOfVacancies());

        JobPost saved = jobPostRepository.save(jobPost);
        return mapToDTO(saved);
    }

    // ✅ Update job post
    public JobPostDTO updateJobPost(Long id, JobPostDTO dto) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new JobPostNotFoundException("Job post not found with id: " + id));

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

    // ✅ Delete job post
    public void deleteJob(Long id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new JobPostNotFoundException("Job post not found with id: " + id));
        jobPostRepository.delete(jobPost);
    }

    // ✅ Get jobs by email
    public List<JobPostDTO> getByPostedByEmail(String email) {
        List<JobPostDTO> jobs = jobPostRepository.findByPostedByEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found for email: " + email);
        }
        return jobs;
    }

    // ✅ Get jobs by title
    public List<JobPostDTO> getByJobTitle(String jobTitle) {
        List<JobPostDTO> jobs = jobPostRepository.findByJobTitle(jobTitle)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found with title: " + jobTitle);
        }
        return jobs;
    }

    // ✅ Get jobs by job type
    public List<JobPostDTO> getByJobType(JobType jobType) {
        List<JobPostDTO> jobs = jobPostRepository.findByJobType(jobType)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found with type: " + jobType);
        }
        return jobs;
    }

    // ✅ Get jobs by company name
    public List<JobPostDTO> getByCompanyName(String companyName) {
        List<JobPostDTO> jobs = jobPostRepository.findByCompanyName(companyName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found for company: " + companyName);
        }
        return jobs;
    }

    // ✅ Get all active jobs
    public List<JobPostDTO> getActiveJobs() {
        List<JobPostDTO> jobs = jobPostRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No active job posts available");
        }
        return jobs;
    }

    // ✅ Get jobs by location
    public List<JobPostDTO> getByJobLocation(String jobLocation) {
        List<JobPostDTO> jobs = jobPostRepository.findByJobLocation(jobLocation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found in location: " + jobLocation);
        }
        return jobs;
    }

    // ✅ Get jobs by required experience
    public List<JobPostDTO> getByRequiredExperience(RequiredExperience requiredExperience) {
        List<JobPostDTO> jobs = jobPostRepository.findByRequiredExperience(requiredExperience)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (jobs.isEmpty()) {
            throw new JobPostNotFoundException("No job posts found with required experience: " + requiredExperience);
        }
        return jobs;
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
                job.getRequiredExperience(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getEducation(),
                job.getSkills(),
                job.isActive(),
                job.getNumberOfVacancies()
        );
    }
}
