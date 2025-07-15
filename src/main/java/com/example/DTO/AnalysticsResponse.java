package com.example.DTO;

public class AnalysticsResponse {
	
	public Long totalStudents;
	public Long totalRecruiters;
	public Long totalJobPosts;
	public Long totalApplications;
        public Long totalAdmins;
	public String totalFileUpload;
	public String totalEmails;
	
	
	
	public AnalysticsResponse() {}
	
	public AnalysticsResponse(Long totalStudents,Long totalRecruiters, Long totalJobPosts, Long totalApplications,Long totalAdmins,String totalFileUplaod,String totalEmails ) {
		this.totalStudents=totalStudents;
		this.totalRecruiters=totalRecruiters;
		this.totalJobPosts=totalJobPosts;
		this.totalApplications=totalApplications;
                this.totalAdmins=totalAdmins;
		this.totalFileUpload=totalFileUplaod;
		this.totalEmails=totalEmails;
	}

}
