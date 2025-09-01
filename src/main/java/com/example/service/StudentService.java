package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredJobLocations;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.exception.StudentNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ----------------- DTO Converters -----------------
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getQualification(),
                student.getResumeURL(),
                student.getSkills(),
                student.getGithubURL(),
                student.getLinkedinURL(),
                student.getExperienceLevel(),
                student.getGender(),
                student.getGraduationYear(),
                student.getPreferredJobLocations(),
                student.getExpectedSalary(),
                student.getNoticePeriod(),
                student.getProjects()
        );
    }

    private Student convertToEntity(StudentDTO dto) {
        return new Student(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getQualification(),
                dto.getResumeURL(),
                dto.getSkills(),
                dto.getGithubURL(),
                dto.getLinkedinURL(),
                dto.getExperienceLevel(),
                dto.getGender(),
                dto.getGraduationYear(),
                dto.getPreferredJobLocations(),
                dto.getExpectedSalary(),
                dto.getNoticePeriod(),
                dto.getProjects(),
                null // user mapping handled separately
        );
    }

 // ----------------- CRUD -----------------
//    public StudentDTO createStudent(StudentDTO studentDTO) {
//        Student student = convertToEntity(studentDTO);
//        Student saved = studentRepository.save(student);
//        return convertToDTO(saved);
//    }

    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student existing = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id)); // ✅ throws exception if not found

        // ✅ Update only if found
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setQualification(dto.getQualification());
        existing.setResumeURL(dto.getResumeURL());
        existing.setSkills(dto.getSkills());
        existing.setGithubURL(dto.getGithubURL());
        existing.setLinkedinURL(dto.getLinkedinURL());
        existing.setExperienceLevel(dto.getExperienceLevel());
        existing.setGender(dto.getGender());
        existing.setGraduationYear(dto.getGraduationYear());
        existing.setPreferredJobLocations(dto.getPreferredJobLocations());
        existing.setExpectedSalary(dto.getExpectedSalary());
        existing.setNoticePeriod(dto.getNoticePeriod());
        existing.setProjects(dto.getProjects());

        Student saved = studentRepository.save(existing);
        return convertToDTO(saved);
    }

    public void deleteStudent(Long id) {
    	 if (!studentRepository.existsById(id))
             throw new StudentNotFoundException(id);
        studentRepository.deleteById(id);
    }

    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ----------------- Filters -----------------
    public List<StudentDTO> getStudentsByGender(Gender gender) {
        return studentRepository.findByGender(gender)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByExperienceLevel(ExperienceLevel experienceLevel) {
        return studentRepository.findByExperienceLevel(experienceLevel)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByGraduationYear(Integer graduationYear) {
        return studentRepository.findByGraduationYear(graduationYear)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsBySkill(String skill) {
        return studentRepository.findBySkillsContainingIgnoreCase(skill)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByNoticePeriod(NoticePeriod noticePeriod) {
        return studentRepository.findByNoticePeriod(noticePeriod)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByPreferredLocation(PreferredJobLocations location) {
        return studentRepository.findByPreferredJobLocations(location)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
