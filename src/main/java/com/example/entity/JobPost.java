package com.example.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="jobPosts")
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private  String jobTitle;
	private String jobDescription;
	private String jobLocation;
	private String jobType;
	private String companyName;
	private String postedByEmail;
	private Date postedDate;
	
	public JobPost() {}
	
	public JobPost(Long id, String jobTitle, String jobDescription, String jobLocation, String jobType,
			String companyName, String postByEmail, Date postedDate) {
		
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.jobLocation = jobLocation;
		this.jobType = jobType;
		this.companyName = companyName;
		this.postedByEmail = postedByEmail;
		this.postedDate = postedDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPosedtByEmail() {
		return postedByEmail;
	}
	public void setPostByEmail(String postByEmail) {
		this.postedByEmail = postedByEmail;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

		

}
