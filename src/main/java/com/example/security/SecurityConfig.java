package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class SecurityConfig {
	@Autowired
	private JWTAuthenticationFilter jwtAuthFilter;

    // Password encoder bean for encoding passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Custom firewall bean to allow encoded characters in URL (helps with tokens in URLs)
    @Bean
    public HttpFirewall allowUrlEncodedCharactersFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedLineFeed(true);
        firewall.setAllowUrlEncodedCarriageReturn(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowUrlEncodedPeriod(true);
        return firewall;
    }

    // Apply the custom firewall
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpFirewall firewall) {
        return web -> web.httpFirewall(firewall);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().and()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow CORS preflight    
            .antMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**",   "/swagger-resources/**", 
                    "/webjars/**","/actuator/**"
                   ).permitAll() 
         

                .anyRequest().authenticated() // All other APIs are protected
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    // Security filter chain for HTTP security config
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()  // Disable CSRF for stateless REST APIs
//            .cors().and()
//            .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow preflight requests
//                .antMatchers(
//                    "/api/auth/**",
//                    "/api/students/**",
//                    "/api/recruiter/**",
//                    "/api/jobPosts/**",
//                    "/api/email/**",
//                    "/api/upload/**",
//                    "/api/admins/**",
//                    "/api/notify/**",
//                    "/api/analytics/**",
//                    "/api/payments/**",
//                    "/api/subscription/**",
//                    "/api/user_subscriptions_status/**",
//                    "/api/invoice/**"
//                ).permitAll()  // Public endpoints
//                .anyRequest().authenticated()  // All other endpoints need authentication
//            .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Stateless session (no HTTP session)
//
//        return http.build();
//    }

    // AuthenticationManager bean required for authentication processes
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
