package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.DTO.EmailRequest;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    // Send email using DTO
    public String sendEmail(EmailRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getTo());
            message.setSubject(request.getSubject());
            message.setText(request.getBody());
            mailSender.send(message);

            log.info("✅ Email sent successfully -> To: {}, Subject: {}", request.getTo(), request.getSubject());
            return "Email sent to " + request.getTo();
        } catch (Exception e) {
            log.error("❌ Failed to send email -> To: {}, Error: {}", request.getTo(), e.getMessage());
            return "Failed to send email: " + e.getMessage();
        }
    }

    // Overloaded method for direct use
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            msg.setFrom("your_email@gmail.com"); // Optional
            mailSender.send(msg);

            log.info("✅ Email sent successfully -> To: {}, Subject: {}", to, subject);
        } catch (Exception e) {
            log.error("❌ Failed to send email -> To: {}, Error: {}", to, e.getMessage());
            throw e; // rethrow if you want controller to handle it
        }
    }
}
