package com.example.DTO;

import java.util.Date;

public class JobPostDTO {
	public Long id;
	public String jobTitle;
	public String jobType;
	public String jobLocation;
	public String jobDescription;
	public String comapanyName;
	public String postedByEmail;
	public Date postedDate;
	
public JobPostDTO() {}
	
	public JobPostDTO(Long id, String jobtitle, String jobDescription, String jobLocation, String jobType,
			String companyName, String postByEmail, Date postedDate) {
		
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.jobLocation = jobLocation;
		this.jobType = jobType;
		this.comapanyName = companyName;
		this.postedByEmail = postByEmail;
		this.postedDate = postedDate;
	}
}
