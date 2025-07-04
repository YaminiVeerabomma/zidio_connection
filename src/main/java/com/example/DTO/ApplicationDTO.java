package com.example.DTO;

import java.util.Date;

import com.example.Enum.Status;

public class ApplicationDTO {
	
	public Long id;
	public Long studentId;
	public Long jobId;
	
	public Status status;
	 
	public Date appliedDate;

	public ApplicationDTO(Long id, Long studentId, Long jobId, Status status, Date appliedDate) {
		
		this.id = id;
		this.studentId = studentId;
		this.jobId = jobId;
		this.status = status;
		this.appliedDate = appliedDate;
	}

}
