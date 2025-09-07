package com.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import com.example.entity.AdminUser;
import com.example.entity.PasswordResetToken;
import com.example.entity.Recruiter;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.exception.*;
import com.example.repository.*;
import com.example.security.JWTUtil;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired private UserRepository userRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private RecruiterRepository recruiterRepository;
    @Autowired private AdminUserRepository adminUserRepository;
    @Autowired private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JWTUtil jwtUtil;

    // ---------------- REGISTER ----------------
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("📩 Register request received for email: {}", request.getEmail());

        // 1. Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("❌ Registration failed: Email already exists -> {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // 2. Save base User
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        User savedUser = userRepository.save(user);
        log.info("✅ User [{}] registered successfully with role: {}", savedUser.getEmail(), savedUser.getRole());

        // 3. Save role-specific entity
        switch (request.getRole()) {
            case STUDENT:
                Student student = new Student();
                student.setUser(savedUser);
                student.setName(request.getName());
                student.setEmail(request.getEmail());
                studentRepository.save(student);
                log.info("🎓 Student profile created for {}", savedUser.getEmail());
                break;

            case RECRUITER:
                Recruiter recruiter = new Recruiter();
                recruiter.setUser(savedUser);
                recruiter.setName(request.getName());
                recruiter.setEmail(request.getEmail());
                recruiterRepository.save(recruiter);
                log.info("🏢 Recruiter profile created for {}", savedUser.getEmail());
                break;

            case ADMIN:
                AdminUser admin = new AdminUser();
                admin.setUser(savedUser);
                admin.setName(request.getName());
                admin.setEmail(request.getEmail());
                adminUserRepository.save(admin);
                log.info("🛡️ Admin profile created for {}", savedUser.getEmail());
                break;

            default:
                log.error("❌ Invalid role selected during registration for email: {}", savedUser.getEmail());
                throw new InvalidCredentialsException("Invalid role selected.");
        }

        // 4. Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());
        return new AuthResponse(token, "User registered successfully");
    }

    // ---------------- LOGIN ----------------
    public AuthResponse login(LoginRequest request) {
        log.info("🔑 Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("❌ Login failed: User not found -> {}", request.getEmail());
                    return new UserNotFoundException("User not found with email: " + request.getEmail());
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("❌ Login failed: Invalid credentials for {}", request.getEmail());
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        log.info("✅ User [{}] logged in successfully with role: {}", user.getEmail(), user.getRole());

        return new AuthResponse(token, "Login successful");
    }

    // ---------------- FORGOT PASSWORD ----------------
    public String forgotPassword(String email) {
        log.info("🔄 Forgot password request for email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("❌ Forgot password failed: Email not registered -> {}", email);
                    return new UserNotFoundException("Email not registered: " + email);
                });

        String token = UUID.randomUUID().toString();
        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(expiry);
        passwordResetTokenRepository.save(resetToken);

        log.info("📩 Password reset token generated for {} (expires at {})", email, expiry);

        return "Reset link sent to email";
    }

    // ---------------- RESET PASSWORD ----------------
    public String resetPassword(String token, String newPassword) {
        log.info("🔐 Password reset attempt with token: {}", token);

        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    log.warn("❌ Password reset failed: Invalid token -> {}", token);
                    return new InvalidResetTokenException("Invalid reset token");
                });

        if (resetToken.getExpiryDate().before(new Date())) {
            log.warn("❌ Password reset failed: Token expired for email -> {}", resetToken.getEmail());
            throw new TokenExpiredException("Reset token has expired");
        }

        User user = userRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> {
                    log.error("❌ Password reset failed: User not found for token {}", token);
                    return new UserNotFoundException("User not found for token");
                });

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);

        log.info("✅ Password reset successful for user {}", user.getEmail());

        return "Password has been reset successfully";
    }
}
