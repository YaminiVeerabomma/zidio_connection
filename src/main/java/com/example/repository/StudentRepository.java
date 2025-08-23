package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;
import com.example.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    // Gender-based query
    List<Student> findByGender(Gender gender);

    // Experience Level-based query
    List<Student> findByExperienceLevel(ExperienceLevel experienceLevel);

    // Graduation Year
    List<Student> findByGraduationYear(Integer graduationYear);

    // Skills (search inside list)
    List<Student> findBySkillsContainingIgnoreCase(String skill);

    // Notice Period
    List<Student> findByNoticePeriod(NoticePeriod noticePeriod);

    // Preferred Location
    List<Student> findByPreferredLocation(PreferredLocation preferredLocation);
}
