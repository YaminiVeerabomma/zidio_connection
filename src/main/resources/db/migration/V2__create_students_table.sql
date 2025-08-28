-- V2__create_students_table.sql

CREATE TABLE students (
    id BIGINT PRIMARY KEY,  -- Same as users.id (1-to-1 with User)

    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    qualification VARCHAR(100),

    gender VARCHAR(20),
    graduation_year INT,

    skills VARCHAR(1000),

    experience_level VARCHAR(50),

    resume_url VARCHAR(255),
    github_url VARCHAR(255),
    linkedin_url VARCHAR(255),

    preferred_job_locations VARCHAR(255),

    expected_salary DECIMAL(10,2),

    notice_period VARCHAR(50),

    projects VARCHAR(1000),

    -- Relationship with users
    CONSTRAINT fk_student_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
