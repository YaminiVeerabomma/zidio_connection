package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredLocation;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository; // needed for @MapsId

    // ---------- DTO Conversion ----------
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
                student.getGraduationYear() != null ? student.getGraduationYear().getYear() + 1900 : null,
                student.getPreferredLocation(),
                student.getExpectedSalary(),
                student.getNoticePeriod()
        );
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.name);
        student.setEmail(dto.email);
        student.setPhone(dto.phone);
        student.setQualification(dto.qualification);
        student.setResumeURL(dto.resumeURL);
        student.setSkills(dto.skills);
        student.setGithubURL(dto.githubURL);
        student.setLinkedinURL(dto.linkdenURL);
        student.setExperienceLevel(dto.experienceLevel);
        student.setGender(dto.gender);
        if (dto.graduationYear != null) {
            student.setGraduationYear(new Date(dto.graduationYear - 1900, 0, 1));
        }
        student.setPreferredLocation(dto.preferredLocation);
        student.setExpectedSalary(dto.expectedSalary);
        student.setNoticePeriod(dto.noticePeriod);
        return student;
    }

    // ---------- CRUD ----------
    public StudentDTO saveStudent(StudentDTO dto, Long userId) {
        Student student = convertToEntity(dto);

        // Link student with User (important for @MapsId)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        student.setUser(user);
        return convertToDTO(studentRepository.save(student));
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // ---------- Filters ----------
    public List<StudentDTO> getByGender(Gender gender) {
        return studentRepository.findByGender(gender)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getByExperienceLevel(ExperienceLevel level) {
        return studentRepository.findByExperienceLevel(level)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getByGraduationYear(Integer year) {
        return studentRepository.findByGraduationYear(year)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getBySkill(String skill) {
        return studentRepository.findBySkillsContainingIgnoreCase(skill)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getByNoticePeriod(NoticePeriod notice) {
        return studentRepository.findByNoticePeriod(notice)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getByPreferredLocation(PreferredLocation location) {
        return studentRepository.findByPreferredLocation(location)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
