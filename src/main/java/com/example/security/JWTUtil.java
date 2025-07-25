package com.example.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JWTUtil {

    private final String SECRET_KEY = "zidio_secret_key";

    // Generate JWT with email and role
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Extract email/username from JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract role if needed
    public String extractUserRole(String token) {
        return (String) extractClaims(token).get("role");
    }

    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Validate token (email match + not expired)
    public boolean validateToken(String token, UserDetails userDetails) {
    	System.out.println("jwt");
    	final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Check if expired
    public boolean isTokenExpired(String token) {
    	System.out.println("jwt1");
        return extractExpiration(token).before(new Date());
    }

    // Common method to extract claims
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}


