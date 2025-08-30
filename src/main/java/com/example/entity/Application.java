package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.Enum.Status;

@Entity
@Table(name="applications")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;

    @Column(name = "student_id", nullable = false)
	private Long studentId;

    @Column(name = "job_id", nullable = false)
	private Long jobId;
    @Column(name = "resume_url")
	private String resumeURL;

    @Enumerated(EnumType.STRING)
	private Status status;
    @Temporal(TemporalType.TIMESTAMP)
	private Date appliedDate;
	
	public Application() {}
	
	public Application(Long id, Long studentId, Long jobId, String resumeURL,Status status, Date appliedDate) {
		
		this.id = id;
		this.studentId = studentId;
		this.jobId = jobId;
		this.resumeURL=resumeURL;
		this.status = status;
		this.appliedDate = appliedDate;
		this.resumeURL = resumeURL;
	}

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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getResumeURL() {
		return resumeURL;
	}

	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	

}
