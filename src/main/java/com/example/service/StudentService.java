package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.entity.User;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Student from User
    public Student createStudent(Student student, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOpt.get();
        student.setUser(user);   // link student to user
        return studentRepository.save(student);
    }

    // Get Student by ID
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    // Update Student
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudent(id);

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setQualification(studentDetails.getQualification());
        student.setResumeURL(studentDetails.getResumeURL());
        student.setSkills(studentDetails.getSkills());
        student.setGithubURL(studentDetails.getGithubURL());
        student.setLinkedinURL(studentDetails.getLinkedinURL());
        student.setGender(studentDetails.getGender());
        student.setGraduationYear(studentDetails.getGraduationYear());
        student.setExperienceLevel(studentDetails.getExperienceLevel());
        student.setPreferredLocation(studentDetails.getPreferredLocation());
        student.setExpectedSalary(studentDetails.getExpectedSalary());
        student.setNoticePeriod(studentDetails.getNoticePeriod());

        return studentRepository.save(student);
    }

    // Delete Student
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}
