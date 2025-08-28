CREATE TABLE student_skills (
    student_id BIGINT NOT NULL,
    skill VARCHAR(255) NOT NULL,
    CONSTRAINT fk_student_skills_student
        FOREIGN KEY (student_id) REFERENCES students(id)
        ON DELETE CASCADE
);
