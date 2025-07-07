package com.example.DTO;

import java.util.Date;

import com.example.Enum.Status;

public class ApplicationDTO {
	
	public Long id;
	public Long studentId;
	public Long jobId;
	public String resumeURL;
	public Status status;
	 
	public Date appliedDate;

	public ApplicationDTO(Long id, Long studentId, Long jobId, Status status,String resumeURL, Date appliedDate) {
		
		this.id = id;
		this.studentId = studentId;
		this.jobId = jobId;
		this.status = status;
		this.resumeURL=resumeURL;
		this.appliedDate = appliedDate;
	}

}
