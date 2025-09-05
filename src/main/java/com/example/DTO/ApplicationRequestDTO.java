package com.example.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApplicationRequestDTO {

	 @NotNull(message = "Student ID is required")
	 public  Long studentId;

	    @NotNull(message = "Job ID is required")
	    public  Long jobId;

	    @NotBlank(message = "Resume URL cannot be blank")
	    @Pattern(regexp = "^(http|https)://.*$", message = "Resume URL must be a valid link")
	    public  String resumeURL;

    public ApplicationRequestDTO() {}

    public ApplicationRequestDTO(Long studentId, Long jobId, String resumeURL) {
        this.studentId = studentId;
        this.jobId = jobId;
        this.resumeURL = resumeURL;
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
}
