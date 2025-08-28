CREATE TABLE student_preferred_locations (
    student_id BIGINT NOT NULL,
    preferred_job_location VARCHAR(50) NOT NULL,
    CONSTRAINT fk_student_preferred_locations_student
        FOREIGN KEY (student_id) REFERENCES students(id)
        ON DELETE CASCADE
);

ALTER TABLE student_preferred_locations
ADD CONSTRAINT pk_student_preferred_locations
PRIMARY KEY (student_id, preferred_job_location);
