package com.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.exception.InvalidCredentialsException;
import com.example.exception.UserNotFoundException;
import com.example.repository.AdminUserRepository;
import com.example.repository.PasswordResetTokenRepository;
import com.example.repository.RecruiterRepository;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;
import com.example.security.JWTUtil;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

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

    // ---------------- REGISTER ----------------
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("=== Register Method Called ===");

        // 1. Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        // 2. Create and save base User
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);
        log.info("✅ User saved with ID: {}", savedUser.getId());

        // 3. Save role-specific entity
        if (request.getRole() == Role.STUDENT) {
            Student student = new Student();
            student.setUser(savedUser);
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            studentRepository.save(student);
            log.info("🎓 Student entity saved for User ID: {}", savedUser.getId());

        } else if (request.getRole() == Role.RECRUITER) {
            Recruiter recruiter = new Recruiter();
            recruiter.setUser(savedUser);
            recruiter.setName(request.getName());
            recruiter.setEmail(request.getEmail());
            recruiterRepository.save(recruiter);
            log.info("🏢 Recruiter entity saved for User ID: {}", savedUser.getId());

        } else if (request.getRole() == Role.ADMIN) {
            AdminUser admin = new AdminUser();
            admin.setUser(savedUser);
            admin.setName(request.getName());
            admin.setEmail(request.getEmail());
            adminUserRepository.save(admin);
            log.info("🛡️ Admin entity saved for User ID: {}", savedUser.getId());
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        // 4. Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());
        log.info("🔑 JWT generated for User ID: {}", savedUser.getId());

        return new AuthResponse(token, "User Registered Successfully");
    }

    // ---------------- LOGIN ----------------
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        log.info("✅ Login successful for User ID: {}", user.getId());
        return new AuthResponse(token, "Login successful");
    }

    // ---------------- FORGOT PASSWORD ----------------
    public String forgotPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Email not registered");
        }

        String token = UUID.randomUUID().toString();
        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(expiry);
        passwordResetTokenRepository.save(resetToken);

        log.info("📩 Reset link generated for email: {}", email);
        return "Reset link sent to email";
    }

    // ---------------- RESET PASSWORD ----------------
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
        log.info("✅ Password reset successful for User ID: {}", user.getId());
        return "Password has been reset successfully";
    }
}


