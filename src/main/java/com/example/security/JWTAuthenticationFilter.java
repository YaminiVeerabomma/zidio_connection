//package com.example.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.service.AuthService;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @Autowired
//    private AuthService authService;  // AuthService implements UserDetailsService
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//
//        String email = null;
//        String token = null;
//
//        // Extract token from Authorization header
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            try {
//                email = jwtUtil.extractUsername(token);
//            } catch (ExpiredJwtException e) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;  // Token expired, stop filter chain here
//            } catch (Exception e) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;  // Invalid token, stop filter chain
//            }
//        }
//
//        // If email extracted and no authentication present yet
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = authService.loadUserByUsername(email);
//
//            // Validate token and set Authentication in SecurityContext
//            if (jwtUtil.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
//
//
//
//
//
