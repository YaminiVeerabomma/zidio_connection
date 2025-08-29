package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Enum.JobType;
import com.example.Enum.RequiredExperience;
import com.example.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByPostedByEmail(String email);
    List<JobPost> findByJobTitle(String jobTitle);
    List<JobPost> findByJobType(JobType jobType);
    List<JobPost> findByCompanyName(String companyName);
    List<JobPost> findByIsActiveTrue();
    List<JobPost> findByJobLocation(String jobLocation);
    List<JobPost> findByRequiredExperience(RequiredExperience requiredExperience);
    
}
