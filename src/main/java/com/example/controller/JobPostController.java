package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.JobPostDTO;
import com.example.Enum.JobType;
import com.example.service.JobPostService;

@RestController
@RequestMapping("/api/jobPosts")
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;
	
	@PostMapping
	public ResponseEntity<JobPostDTO>createJob(@RequestBody JobPostDTO dto){
		return ResponseEntity.ok(jobPostService.postJob(dto));
	}
	@GetMapping(value="/recruiter" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobPostDTO>>getByPostedEmail(@RequestParam String email){
		return ResponseEntity.ok(jobPostService.getByPostedByEmail(email));
	}
	@GetMapping(value="/jobTitle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobPostDTO>>getByJobTitle(@RequestParam String jobTitle){
		return ResponseEntity.ok(jobPostService.getByJobTitle(jobTitle));
	}
	@GetMapping(value="/jobType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobPostDTO>>getByJobType(@RequestParam JobType jobType){
		return ResponseEntity.ok(jobPostService.getByJobType(jobType));
	}
	
	@GetMapping(value="/companyName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobPostDTO>>getByCompanyName(@RequestParam String companyName){
		return ResponseEntity.ok(jobPostService.getByCompanyName(companyName));
	}
	   // ✅ Get all active jobs
    @GetMapping(value="/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobPostDTO> getActiveJobs() {
        return jobPostService.getActiveJobs();
    }
    @GetMapping(value="/location/{location},produces = MediaType.APPLICATION_JSON_VALUE")
    public ResponseEntity<List<JobPostDTO>> getJobsByLocation(@PathVariable String location) {
        List<JobPostDTO> jobs = jobPostService.getByJobLocation(location);
        return ResponseEntity.ok(jobs);
    }
	
	
	
	
}
