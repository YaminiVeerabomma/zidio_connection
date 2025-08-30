package com.example.DTO;

public class ApplicationRequestDTO {

    private Long studentId;
    private Long jobId;
    private String resumeURL;

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
