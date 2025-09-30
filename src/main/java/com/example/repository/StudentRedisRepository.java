package com.example.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

// Redis Repository
@Repository
public interface StudentRedisRepository extends CrudRepository<Student, Long> { }

