package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Application;
import com.example.entity.Student;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	List<Application>findByStudentId(Long studentId);
	List<Application>findByJobId(Long jobId);
	 Application findApplicationById(Long id);
	Optional<Student> findByStudentIdAndJobId(Long studentId, Long jobId);
	
	

}
