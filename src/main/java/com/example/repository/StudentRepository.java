package com.example.repository;

import com.example.Enum.Gender;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredJobLocations;
import com.example.entity.Student;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Get all students with skills & preferred job locations in one query
    @Override
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findAll();

    // Filter by Gender
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findByGender(Gender gender);

    // Filter by Experience Level
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findByExperienceLevel(ExperienceLevel experienceLevel);

    // Filter by Graduation Year
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findByGraduationYear(Integer graduationYear);

    // Filter by Skills (CSV string search)
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findBySkillsContainingIgnoreCase(String skill);

    // Filter by Notice Period
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findByNoticePeriod(NoticePeriod noticePeriod);

    // Filter by Preferred Job Locations
    @EntityGraph(attributePaths = {"skills", "preferredJobLocations"})
    List<Student> findByPreferredJobLocations(PreferredJobLocations location);
}
