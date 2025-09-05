package com.example.DTO;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.Enum.JobType;
import com.example.Enum.RequiredExperience;

public class JobPostDTO {

    public Long id;
    @NotBlank(message = "Job title cannot be blank")
    public  String jobTitle;

    @NotNull(message = "Job type is required")
    public  JobType jobType;

    @NotBlank(message = "Job location cannot be blank")
    public  String jobLocation;

    @NotBlank(message = "Job description cannot be blank")
    @Size(min = 10, message = "Job description must be at least 10 characters long")
    public  String jobDescription;

    @NotBlank(message = "Company name cannot be blank")
    public  String companyName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "PostedBy email cannot be blank")
    public  String postedByEmail;

    @PastOrPresent(message = "Posted date cannot be in the future")
    public  Date postedDate;

    @NotNull(message = "Required experience is required")
    public  RequiredExperience requiredExperience;

    @NotNull(message = "Minimum salary cannot be null")
    @Positive(message = "Minimum salary must be greater than 0")
    public  Double salaryMin;

    @NotNull(message = "Maximum salary cannot be null")
    @Positive(message = "Maximum salary must be greater than 0")
    @AssertTrue(message = "Maximum salary must be greater than minimum salary")
    public  boolean isSalaryValid() {
        return salaryMax == null || salaryMin == null || salaryMax >= salaryMin;
    }

    public  Double salaryMax;

    @NotBlank(message = "Education cannot be blank")
    public  String education;

    @NotBlank(message = "Skills cannot be blank")
    public  String skills;

    public  boolean isActive = true;

    @NotNull(message = "Number of vacancies cannot be null")
    @Min(value = 1, message = "At least one vacancy is required")
    public  Integer numberOfVacancies;
    public JobPostDTO() {}

    // ✅ Add this constructor
    public JobPostDTO(Long id, String jobTitle, JobType jobType, String jobLocation, String jobDescription,
                      String companyName, String postedByEmail, Date postedDate,RequiredExperience requiredExperience,
                      Double salaryMin, Double salaryMax, String education, String skills, boolean isActive,Integer numberOfVacancies) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.companyName = companyName;
        this.postedByEmail = postedByEmail;
        this.postedDate = postedDate;
        this.requiredExperience =requiredExperience;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.education = education;
        this.skills = skills;
        this.isActive = isActive;
    	this.numberOfVacancies=numberOfVacancies;
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


	public RequiredExperience getRequiredExperience() {
		return requiredExperience;
	}

	public void setRequiredExperience(RequiredExperience requiredExperience) {
		this.requiredExperience = requiredExperience;
	}

	public Integer getNumberOfVacancies() {
		return numberOfVacancies;
	}

	public void setNumberOfVacancies(Integer numberOfVacancies) {
		this.numberOfVacancies = numberOfVacancies;
	}

	


	
	
    
}

