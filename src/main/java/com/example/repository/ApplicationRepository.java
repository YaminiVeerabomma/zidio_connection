package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	List<Application>findByStudentId(Long studentId);
	List<Application>findByJobId(Long jobId);

	
	

}
