package com.example.DTO;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.example.Enum.Status;

public class ApplicationDTO {
	
	public Long id;
	  @NotNull(message = "Student ID is required")
	  public  Long studentId;

	    @NotNull(message = "Job ID is required")
	    public  Long jobId;

	    @NotBlank(message = "Resume URL cannot be blank")
	    @Pattern(regexp = "^(http|https)://.*$", message = "Resume URL must be a valid link")
	    public  String resumeURL;

	    @NotNull(message = "Status must be provided")
	    public  Status status;

	    @PastOrPresent(message = "Applied date cannot be in the future")
	    public  Date appliedDate;

	// Constructor
	public ApplicationDTO(Long id, Long studentId, Long jobId, String resumeURL, Status status, Date appliedDate) {
		this.id = id;
		this.studentId = studentId;
		this.jobId = jobId;
		this.resumeURL = resumeURL;
		this.status = status;
		this.appliedDate = appliedDate;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getResumeURL() {
		return resumeURL;
	}

	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
}

