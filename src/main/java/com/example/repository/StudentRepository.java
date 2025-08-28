package com.example.repository;

import com.example.Enum.Gender;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredJobLocations;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Filter by Gender
    List<Student> findByGender(Gender gender);

    // Filter by Experience Level
    List<Student> findByExperienceLevel(ExperienceLevel experienceLevel);

    // Filter by Graduation Year
    List<Student> findByGraduationYear(Integer graduationYear);

    // Filter by skills (CSV string)
    List<Student> findBySkillsContainingIgnoreCase(String skill);

    // Filter by Notice Period
    List<Student> findByNoticePeriod(NoticePeriod noticePeriod);

    List<Student> findByPreferredJobLocations(PreferredJobLocations location);

}
