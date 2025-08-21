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
import com.example.entity.PasswordResetToken;
import com.example.entity.User;
import com.example.exception.EmailNotRegisteredException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidEmailException;
import com.example.exception.UserNotFoundException;
import com.example.repository.PasswordResetTokenRepository;
import com.example.repository.UserRepository;
import com.example.security.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    // ------------------ REGISTER ------------------
    public AuthResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, "User registered successfully");
    }

    // ------------------ LOGIN ------------------
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, "Login successful");
    }
    public String forgotPassword(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Enter valid email");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new EmailNotRegisteredException("Email is not registered");
        }

        // Generate reset token
        String token = UUID.randomUUID().toString();
        Date expiry = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(expiry);

        passwordResetTokenRepository.save(resetToken);

        // Log reset link (or send email)
        System.out.println("Reset link: http://localhost:8889/api/auth/reset-password?token=" + token);

        return "Reset link sent to email";
    }



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
