package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
	List<JobPost>findByPostedyEmail(String email);
	List<JobPost>findByJobTitle(String jobtitle);
	List<JobPost>findByJobType(String jobType);
	List<JobPost>findByCompanyName(String comapanyName);
	

}
