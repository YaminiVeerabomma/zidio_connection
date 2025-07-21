package com.example.entity;

import java.util.Date;

import javax.persistence.*;

import com.example.Enum.JobType;

@Entity
@Table(name = "jobPosts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    private String companyName;
    private String postedByEmail;
    private Date postedDate;
    private String experienceLevel; 
    private Double salaryMin;          
    private Double salaryMax;           

    private String education;         
    private String skills; 
    private boolean isActive = true; 

    public JobPost() {}

    public JobPost(Long id, String jobTitle, String jobDescription, String jobLocation,JobType jobType,
                   String companyName, String postedByEmail, Date postedDate, String experienceLevel, Double salaryMin, Double salaryMax, String education, String skills, boolean isActive) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.companyName = companyName;
        this.postedByEmail = postedByEmail;
      this. experienceLevel=experienceLevel; 
        this.postedDate = postedDate;
    	this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.education = education;
		this.skills = skills;
		this.isActive = isActive;
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

    public void setJobTitle(String jobTitle) {
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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPostedByEmail() {
        return postedByEmail;
    }

    public void setPostedByEmail(String postedByEmail) {
        this.postedByEmail = postedByEmail;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

	public Double getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Double salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Double getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Double salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		
		this.isActive = isActive;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	
    
}

