package com.example.security;

import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JWTUtil {
	
	private final String SECRET_KEY="zidio_secret_key";
	
	public String generateToken(String email,String role) {
		
		return Jwts.builder().
				setSubject(email).
				claim("role",role).
				setIssuedAt(new Date()).
				setExpiration(new Date(System.currentTimeMillis()+36000)).
				signWith(SignatureAlgorithm.HS256,SECRET_KEY).
				compact();
		
	}
	public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
	
	
//	public String extractEmail(String token) {
//		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
//		
//      
//    }
	
	
//	public Claims getClaims(String token) {
//		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//		
//	}
	
	
}

