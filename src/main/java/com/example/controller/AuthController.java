package com.example.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AuthResponse;
import com.example.DTO.ForgotPasswordRequest;
import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;
import com.example.DTO.ResetPasswordRequest;
import com.example.entity.PasswordResetToken;
import com.example.entity.User;
import com.example.repository.PasswordResetTokenRepository;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import com.example.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@Tag(name = "Authentication", description = "Login & Registration APIs")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register User", description = "Create a new user account")
    public ResponseEntity<AuthResponse>register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
	}
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login User", description = "Authenticate user and return JWT token")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Testing purpose")
    public ResponseEntity<?> testEndpoint() {
        return ResponseEntity.ok(
            java.util.Map.of("message", "Auth service is working")
        );
    }

    @PostMapping(value = "/forgot-password", produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(
        summary = "Forgot Password",
        description = "Send a password reset link to the registered email"
    )
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    	return ResponseEntity.ok(authService.forgotPassword(request.getEmail() ));
//    	System.out.println("forgot controller");
//        String email = request.getEmail().trim();
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        String token = UUID.randomUUID().toString();
//
//        PasswordResetToken resetToken = new PasswordResetToken();
//        resetToken.setEmail(email);
//        resetToken.setToken(token);
//        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 30)); // 30 min
//        tokenRepository.save(resetToken);
//
//        String resetLink = "http://localhost:8889/reset-password?token=" + token;
//        emailService.sendEmail(email, "Reset Your Password", "Click to reset: " + resetLink);

       // return ResponseEntity.ok("Reset link sent to email");
    }

    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Reset Password",
            description = "Reset the user password using the token sent to email"
        )
   public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
    	return ResponseEntity.ok(authService.resetPassword(request.getToken(),request.getNewPassword() ));
//        String rawToken = request.getToken().trim();
//
//        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(rawToken);
//        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiryDate().before(new Date())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalid or expired");
//        }
//
//        PasswordResetToken tokenEntity = tokenOpt.get();
//
//        Optional<User> userOpt = userRepository.findByEmail(tokenEntity.getEmail());
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        User user = userOpt.get();
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        userRepository.save(user);
//
//        tokenRepository.delete(tokenEntity);
//
//        return ResponseEntity.ok("Password has been reset");
    }
}
