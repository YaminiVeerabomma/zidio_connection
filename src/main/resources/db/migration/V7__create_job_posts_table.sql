-- V7__jobpost_table.sql

CREATE TABLE IF NOT EXISTS jobPosts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    jobTitle VARCHAR(255) NOT NULL,
    jobDescription TEXT,
    jobLocation VARCHAR(255),
    jobType VARCHAR(50),  -- Enum stored as string
    companyName VARCHAR(255),
    postedByEmail VARCHAR(255),
    postedDate DATETIME,
    experienceLevel VARCHAR(50),  -- Enum stored as string
    salaryMin DOUBLE,
    salaryMax DOUBLE,
    education VARCHAR(255),
    skills TEXT,
    isActive BOOLEAN DEFAULT TRUE,
    numberOfVacancies INT
);
