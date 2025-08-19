package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.JobPostDTO;
import com.example.Enum.JobType;
import com.example.service.JobPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jobPosts")
@Tag(name = "Job Post API", description = "Operations related to Job Post management")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @PostMapping
    @Operation(summary = "Create Job Post", description = "Post a new job")
    public ResponseEntity<JobPostDTO> createJob(@RequestBody JobPostDTO dto){
        return ResponseEntity.ok(jobPostService.postJob(dto));
    }

    @GetMapping(value="/recruiter", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Jobs by Recruiter Email", description = "Fetch all jobs posted by a specific recruiter using their email")
    public ResponseEntity<List<JobPostDTO>> getByPostedEmail(
            @Parameter(description = "Recruiter's email", required = true, example = "recruiter@example.com")
            @RequestParam String email){
        return ResponseEntity.ok(jobPostService.getByPostedByEmail(email));
    }

    @GetMapping(value="/jobTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Jobs by Job Title", description = "Fetch all jobs matching the given job title")
    public ResponseEntity<List<JobPostDTO>> getByJobTitle(
            @Parameter(description = "Job title to filter", required = true, example = "Software Engineer")
            @RequestParam String jobTitle){
        return ResponseEntity.ok(jobPostService.getByJobTitle(jobTitle));
    }

    @GetMapping(value="/jobType", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Jobs by Job Type", description = "Fetch all jobs filtered by job type (FULL_TIME, PART_TIME, etc.)")
    public ResponseEntity<List<JobPostDTO>> getByJobType(
            @Parameter(description = "Job type enum", required = true, example = "FULL_TIME")
            @RequestParam JobType jobType){
        return ResponseEntity.ok(jobPostService.getByJobType(jobType));
    }

    @GetMapping(value="/companyName", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Jobs by Company Name", description = "Fetch all jobs posted by a specific company")
    public ResponseEntity<List<JobPostDTO>> getByCompanyName(
            @Parameter(description = "Company name", required = true, example = "TechCorp")
            @RequestParam String companyName){
        return ResponseEntity.ok(jobPostService.getByCompanyName(companyName));
    }

    @GetMapping(value="/active", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Active Jobs", description = "Retrieve all currently active job posts")
    public List<JobPostDTO> getActiveJobs() {
        return jobPostService.getActiveJobs();
    }

    @GetMapping(value="/location/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Jobs by Location", description = "Fetch all jobs available in a specific location")
    public ResponseEntity<List<JobPostDTO>> getJobsByLocation(
            @Parameter(description = "Job location", required = true, example = "Bangalore")
            @PathVariable String location) {
        List<JobPostDTO> jobs = jobPostService.getByJobLocation(location);
        return ResponseEntity.ok(jobs);
    }
}
