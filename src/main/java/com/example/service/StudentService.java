package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.StudentDTO;
import com.example.Enum.NoticePeriod;
import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {
@Autowired
private StudentRepository studentRepository;

	public StudentDTO createOrUpdateStudent(StudentDTO dto) {
		
		Student student = new Student(
				dto.id,dto.name,dto.email,dto.phone,dto.qualification,dto.resumeURL,dto.skills,dto.githubURL,dto.linkdenURL,dto.experienceLevel,dto.gender,dto.graduationYear,dto.preferredJobLocations,dto.expectedSalary,dto.noticePeriod);
			
		Student saved = (Student) studentRepository.save(student);
		return mapToDTO(saved);
	}

	public StudentDTO getStudentByEmail(String email) {
		Student student = studentRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("student not found"));
		 return mapToDTO(student);
		
	}
	public StudentDTO getStudentById(Long id) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("student not found"));
		 return mapToDTO(student);
	}
	public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
	public List<StudentDTO> getAllStudents() {
	    List<Student> students = studentRepository.findAll();
	    return students.stream()
	                   .map(this::mapToDTO)
	                   .toList(); 
	}
	public List<StudentDTO> getStudentsByGender(String gender) {
	    List<Student> students = studentRepository.findByGender(gender);
	    return students.stream()
	                   .map(this::mapToDTO)
	                   .collect(Collectors.toList());
	}
	public List<StudentDTO> getStudentsByExperienceLevel(String experienceLevel) {
	    List<Student> students = studentRepository.findByExperienceLevel(experienceLevel);
	    return students.stream()
	                   .map(this::mapToDTO)
	                   .collect(Collectors.toList());
	}
	public List<StudentDTO> getStudentsByGraduationYear(Integer year) {
	    List<Student> students = studentRepository.findByGraduationYear(year);
	    return students.stream().map(this::mapToDTO).toList();
	}
	public List<StudentDTO> getStudentsByNoticePeriod(NoticePeriod noticePeriod) {
	    List<Student> students = studentRepository.findByNoticePeriod(noticePeriod);
	    return students.stream()
	                   .map(this::mapToDTO)
	                   .collect(Collectors.toList());
	}


	public List<StudentDTO> getStudentsBySkill(String skill) {
	    List<Student> students = studentRepository.findBySkillsContaining(skill);
	    return students.stream().map(this::mapToDTO).toList();
	}


	

	private StudentDTO mapToDTO(Student student) {
		return  new StudentDTO(
				student.getId(),
				student.getName(),
				student.getEmail(),
				student.getPhone(),
				student.getQualification(),
				student.getResumeURL(),
				student.getSkills(),
				student.getGithubURL(),
				student.getLinkdenURL(),
				student.getExperienceLevel(),
				student.getGender(),
				student.getGraduationYear(),
				student.getPreferredJobLocations(),
			    student.getExpectedSalary(),
				student.getNoticePeriod()

				);
				
		
	}
}