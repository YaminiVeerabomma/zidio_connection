-- V7__create_jobposts.sql
CREATE TABLE job_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_title VARCHAR(255) NOT NULL,
    job_description TEXT,
    job_location VARCHAR(255),
    job_type VARCHAR(50),   -- ENUM stored as STRING
    company_name VARCHAR(255),
    posted_by_email VARCHAR(255),
    posted_date TIMESTAMP,
    required_experience VARCHAR(50), -- ENUM stored as STRING
    salary_min DECIMAL(10,2),
    salary_max DECIMAL(10,2),
    education VARCHAR(255),
    skills VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    number_of_vacancies INT
);
