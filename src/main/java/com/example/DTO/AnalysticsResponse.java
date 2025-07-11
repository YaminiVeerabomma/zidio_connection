package com.example.DTO;

public class AnalysticsResponse {

	public Long  totalStudents;
	public Long totalRecruiters;
	public Long  totalJobPosts;
	public Long totalApplications;
	
	public AnalysticsResponse() {}
	
	public AnalysticsResponse(Long totalStudents, Long totalRecruiters, Long totalJobPosts, Long totalApplications) {
		
		this.totalStudents = totalStudents;
		this.totalRecruiters = totalRecruiters;
		this.totalJobPosts = totalJobPosts;
		this.totalApplications = totalApplications;
	}
	
	
	
}
