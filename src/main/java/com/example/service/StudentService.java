package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.Enum.ExperienceLevel;
import com.example.Enum.Gender;
import com.example.Enum.NoticePeriod;
import com.example.Enum.PreferredJobLocations;
import com.example.entity.Student;
import com.example.exception.UserNotFoundException;
import com.example.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    // ----------------- DTO Converters -----------------
    private StudentDTO convertToDTO(Student student) {
        log.debug("🔧 convertToDTO called for studentId={}", student.getId());
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
                student.getProjects(),
                null
        );
    }

    private Student convertToEntity(StudentDTO dto) {
        log.debug("🔧 convertToEntity called for studentId={}", dto.getId());
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
                null
        );
    }

    // ----------------- UPDATE -----------------
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        log.info("📝 updateStudent called for studentId={}", id);

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("❌ Student not found with id={}", id);
                    return new UserNotFoundException("Student not found with id " + id);
                });

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
        log.info("✅ Student updated successfully with studentId={}", saved.getId());
        return convertToDTO(saved);
    }

    // ----------------- DELETE -----------------
    public void deleteStudent(Long id) {
        log.info("🗑️ deleteStudent called for studentId={}", id);

        if (!studentRepository.existsById(id)) {
            log.warn("⚠️ Student not found with id={}", id);
            throw new UserNotFoundException("Student not found with id " + id);
        }

        studentRepository.deleteById(id);
        log.info("✅ Student deleted successfully with studentId={}", id);
    }
    @Cacheable(value = "students", key = "#id")
    public StudentDTO getStudentById(Long id) {
        log.info("🔍 getStudentById called for studentId={}", id);

        StudentDTO dto = studentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> {
                    log.warn("⚠️ Student not found with id={}", id);
                    return new UserNotFoundException("Student not found with id " + id);
                });

        log.info("✅ Student fetched successfully with studentId={}", id);
        return dto;
    }

//    // ----------------- GET BY ID -----------------
//    public StudentDTO getStudentById(Long id) {
//        log.info("🔍 getStudentById called for studentId={}", id);
//
//        StudentDTO dto = studentRepository.findById(id)
//                .map(this::convertToDTO)
//                .orElseThrow(() -> {
//                    log.warn("⚠️ Student not found with id={}", id);
//                    return new UserNotFoundException("Student not found with id " + id);
//                });
//
//        log.info("✅ Student fetched successfully with studentId={}", id);
//        return dto;
//    }

    // ----------------- GET ALL -----------------
    public List<StudentDTO> getAllStudents() {
        log.info("📚 getAllStudents called");

        List<StudentDTO> students = studentRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found");
            throw new UserNotFoundException("No students found");
        }

        log.info("✅ Total students fetched: {}", students.size());
        return students;
    }

    // ----------------- FILTERS -----------------
    public List<StudentDTO> getStudentsByGender(Gender gender) {
        log.info("🎯 getStudentsByGender called for gender={}", gender);
        List<StudentDTO> students = studentRepository.findByGender(gender)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with gender={}", gender);
            throw new UserNotFoundException("No students found with gender: " + gender);
        }

        log.info("✅ {} student(s) found with gender={}", students.size(), gender);
        return students;
    }

    public List<StudentDTO> getStudentsByExperienceLevel(ExperienceLevel experienceLevel) {
        log.info("🎯 getStudentsByExperienceLevel called for experienceLevel={}", experienceLevel);
        List<StudentDTO> students = studentRepository.findByExperienceLevel(experienceLevel)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with experienceLevel={}", experienceLevel);
            throw new UserNotFoundException("No students found with experience level: " + experienceLevel);
        }

        log.info("✅ {} student(s) found with experienceLevel={}", students.size(), experienceLevel);
        return students;
    }

    public List<StudentDTO> getStudentsByGraduationYear(Integer graduationYear) {
        log.info("🎯 getStudentsByGraduationYear called for graduationYear={}", graduationYear);
        List<StudentDTO> students = studentRepository.findByGraduationYear(graduationYear)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with graduationYear={}", graduationYear);
            throw new UserNotFoundException("No students found with graduation year: " + graduationYear);
        }

        log.info("✅ {} student(s) found with graduationYear={}", students.size(), graduationYear);
        return students;
    }

    public List<StudentDTO> getStudentsBySkill(String skill) {
        log.info("🎯 getStudentsBySkill called for skill={}", skill);
        List<StudentDTO> students = studentRepository.findBySkillsContainingIgnoreCase(skill)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with skill={}", skill);
            throw new UserNotFoundException("No students found with skill: " + skill);
        }

        log.info("✅ {} student(s) found with skill={}", students.size(), skill);
        return students;
    }

    public List<StudentDTO> getStudentsByNoticePeriod(NoticePeriod noticePeriod) {
        log.info("🎯 getStudentsByNoticePeriod called for noticePeriod={}", noticePeriod);
        List<StudentDTO> students = studentRepository.findByNoticePeriod(noticePeriod)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with noticePeriod={}", noticePeriod);
            throw new UserNotFoundException("No students found with notice period: " + noticePeriod);
        }

        log.info("✅ {} student(s) found with noticePeriod={}", students.size(), noticePeriod);
        return students;
    }

    public List<StudentDTO> getStudentsByPreferredLocation(PreferredJobLocations location) {
        log.info("🎯 getStudentsByPreferredLocation called for location={}", location);
        List<StudentDTO> students = studentRepository.findByPreferredJobLocations(location)
                .stream().map(this::convertToDTO).collect(Collectors.toList());

        if (students.isEmpty()) {
            log.warn("⚠️ No students found with preferred location={}", location);
            throw new UserNotFoundException("No students found with preferred location: " + location);
        }

        log.info("✅ {} student(s) found with preferred location={}", students.size(), location);
        return students;
    }
}
