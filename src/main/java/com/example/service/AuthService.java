package com.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DTO.AuthResponse;
import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;
import com.example.Enum.Role;
import com.example.entity.AdminUser;
import com.example.entity.PasswordResetToken;
import com.example.entity.Recruiter;
import com.example.entity.Student;
import com.example.entity.User;
<<<<<<< HEAD
import com.example.exception.EmailNotRegisteredException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidEmailException;
=======
import com.example.exception.InvalidCredentialsException;
>>>>>>> feature/swagger
import com.example.exception.UserNotFoundException;
import com.example.repository.AdminUserRepository;
import com.example.repository.PasswordResetTokenRepository;
import com.example.repository.RecruiterRepository;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;
import com.example.security.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

<<<<<<< HEAD
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    // ------------------ REGISTER ------------------
    public AuthResponse register(RegisterRequest request) {
=======
    // ---------------- REGISTER ----------------
    public AuthResponse register(RegisterRequest request) {
        // 1. Check if email already exists
>>>>>>> feature/swagger
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

<<<<<<< HEAD
=======
        // 2. Create base User
>>>>>>> feature/swagger
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

<<<<<<< HEAD
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, "User registered successfully");
    }

    // ------------------ LOGIN ------------------
=======
        // 3. Create role-specific entity with shared PK
        if (request.getRole() == Role.STUDENT) {
            Student student = new Student();
            student.setUser(savedUser); // important for @MapsId
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            studentRepository.save(student);

        } else if (request.getRole() == Role.RECRUITER) {
            Recruiter recruiter = new Recruiter();
            recruiter.setUser(savedUser); // important for @MapsId
            recruiter.setName(request.getName());
            recruiter.setEmail(request.getEmail());
            recruiterRepository.save(recruiter);

        } else if (request.getRole() == Role.ADMIN) {
            AdminUser admin = new AdminUser();
            admin.setUser(savedUser); // important for @MapsId
            admin.setName(request.getName());
            admin.setEmail(request.getEmail());
            adminUserRepository.save(admin);
        }

        // 4. Generate JWT
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());
        return new AuthResponse(token, "User Registered Successfully");
    }

    // ---------------- LOGIN ----------------
>>>>>>> feature/swagger
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, "Login successful");
    }
<<<<<<< HEAD
    public String forgotPassword(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Enter valid email");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new EmailNotRegisteredException("Email is not registered");
        }

        // Generate reset token
=======

    // ---------------- FORGOT PASSWORD ----------------
    public String forgotPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Email not registered");
        }

>>>>>>> feature/swagger
        String token = UUID.randomUUID().toString();
        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(expiry);
        passwordResetTokenRepository.save(resetToken);

<<<<<<< HEAD
        // Log reset link (or send email)
=======
        // For demo purposes, log reset link
>>>>>>> feature/swagger
        System.out.println("Reset link: http://localhost:8889/api/auth/reset-password?token=" + token);

        return "Reset link sent to email";
    }

<<<<<<< HEAD


//    // ------------------ FORGOT PASSWORD ------------------
//    public String forgotPassword(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
//
//        String token = UUID.randomUUID().toString();
//        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
//                .atZone(ZoneId.systemDefault()).toInstant());
//
//        PasswordResetToken resetToken = new PasswordResetToken();
//        resetToken.setToken(token);
//        resetToken.setEmail(email);
//        resetToken.setExpiryDate(expiry);
//
//        passwordResetTokenRepository.save(resetToken);
//
//        // For now, just log the link
//        System.out.println("Reset link: http://localhost:8889/api/auth/reset-password?token=" + token);
//
//        return "Reset link sent to email";
//    }

    // ------------------ RESET PASSWORD ------------------
=======
    // ---------------- RESET PASSWORD ----------------
>>>>>>> feature/swagger
    public String resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reset token"));

        if (resetToken.getExpiryDate().before(new Date())) {
            throw new IllegalArgumentException("Reset token has expired");
        }

        User user = userRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken);
        return "Password has been reset successfully";
    }
}
<<<<<<< HEAD
=======

//package com.example.service;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Optional;
//import java.util.UUID;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.DTO.AuthResponse;
//import com.example.DTO.LoginRequest;
//import com.example.DTO.RegisterRequest;
//import com.example.Enum.Role;
//import com.example.entity.AdminUser;
//import com.example.entity.PasswordResetToken;
//import com.example.entity.Recruiter;
//import com.example.entity.Student;
//import com.example.entity.User;
//import com.example.exception.InvalidCredentialsException;
//import com.example.exception.UserNotFoundException;
//import com.example.repository.AdminUserRepository;
//import com.example.repository.PasswordResetTokenRepository;
//import com.example.repository.RecruiterRepository;
//import com.example.repository.StudentRepository;
//import com.example.repository.UserRepository;
//import com.example.security.JWTUtil;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//   @Autowired
//    private RecruiterRepository recruiterRepository;
//
//    @Autowired
//    private AdminUserRepository adminUserRepository;
//
//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;
//
//   
//    public AuthResponse register(RegisterRequest request) {
//        System.out.println("Login service hit");
//
//        // 1. Check if email already exists
//        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
//        if (existingUser.isPresent()) {
//            throw new IllegalArgumentException("Email is already registered.");
//        }
//
//        // 2. Create and save new user
//        User user = new User();
//        
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(request.getRole());
//
//        User savedUser= userRepository.save(user);
//        	Long   id=savedUser.getId();
//        // auto save in student table
//        Student student = new Student();
//        student.setId(id);
//        student.setName(request.getName());
//        student.setEmail(request.getEmail());
//        studentRepository.save(student);
//
//
//
//        // Generate JWT token
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//        return new AuthResponse(token, "User Registered Successfully");
//    }
//
// 
// // ------------------ LOGIN ------------------
//    public AuthResponse login(LoginRequest request)  {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new InvalidCredentialsException("Invalid email or password");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//        return new AuthResponse(token, "Login successful");
//    }
//
//    // ----- FORGOT PASSWORD -----
//    public String forgotPassword(String email) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isEmpty()) {
//            throw new IllegalArgumentException("Email not registered");
//        }
//
//        String token = UUID.randomUUID().toString();
//        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
//                .atZone(ZoneId.systemDefault()).toInstant());
//
//        PasswordResetToken resetToken = new PasswordResetToken();
//        resetToken.setToken(token);
//        resetToken.setEmail(email);
//        resetToken.setExpiryDate(expiry);
//        passwordResetTokenRepository.save(resetToken);
//
//        // For demo purposes, log link
//        System.out.println("Reset link: http://localhost:8889/api/auth/reset-password?token=" + token);
//        return "Reset link sent to email";
//    }
//
//    // ----- RESET PASSWORD -----
//    public String resetPassword(String token, String newPassword) {
//        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid reset token"));
//
//        if (resetToken.getExpiryDate().before(new Date())) {
//            throw new IllegalArgumentException("Reset token has expired");
//        }
//
//        User user = userRepository.findByEmail(resetToken.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        passwordResetTokenRepository.delete(resetToken);
//        return "Password has been reset";
//    }
//}
>>>>>>> feature/swagger
