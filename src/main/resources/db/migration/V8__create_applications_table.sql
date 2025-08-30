CREATE TABLE applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    resume_url VARCHAR(255),
    status VARCHAR(50),
    applied_date TIMESTAMP
);
