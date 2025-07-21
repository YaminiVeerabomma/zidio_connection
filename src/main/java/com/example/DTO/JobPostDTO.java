package com.example.DTO;

import java.util.Date;

import com.example.Enum.JobType;

public class JobPostDTO {

    public Long id;
    public String jobTitle;
    public JobType jobType;
    public String jobLocation;
    public String jobDescription;
    public String companyName;
    public String postedByEmail;
    public Date postedDate;
    public String experienceLevel;
    public Double salaryMin;
    public Double salaryMax;
    public String education;
    public String skills;
    public boolean isActive = true;
    public JobPostDTO() {}

    // ✅ Add this constructor
    public JobPostDTO(Long id, String jobTitle, JobType jobType, String jobLocation, String jobDescription,
                      String companyName, String postedByEmail, Date postedDate, String experienceLevel,
                      Double salaryMin, Double salaryMax, String education, String skills, boolean isActive) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.companyName = companyName;
        this.postedByEmail = postedByEmail;
        this.postedDate = postedDate;
        this.experienceLevel = experienceLevel;
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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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

