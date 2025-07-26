package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Enum.NoticePeriod;
import com.example.entity.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long>{
	Optional<Student> findByEmail(String email);
	Optional<Student> findById(Long id);
	List<Student> findByGender(String gender);
	List<Student> findByExperienceLevel(String experienceLevel);
	List<Student> findByGraduationYear(Integer graduationYear);
	List<Student> findBySkillsContaining(String skill);
	List<Student> findByNoticePeriod(NoticePeriod noticePeriod);




	

}
