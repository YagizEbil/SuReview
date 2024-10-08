package com.sabanciuniv.sureview.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtil jwtUtil;

    public CustomAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);
            String email = loginRequest.getEmail();
            String displayName = loginRequest.getDisplayName();
            logger.info("Attempting authentication for email: {}, displayName: {}", email, displayName);
            if (email.endsWith("@sabanciuniv.edu") && email.startsWith(displayName)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, "", Collections.emptyList());
                logger.info("Authentication success for email: {}", email);
                return getAuthenticationManager().authenticate(authentication);
            } else {
                logger.warn("Invalid email or username format for email: {}", email);
                throw new RuntimeException("Invalid email or username");
            }
        } catch (IOException e) {
            logger.error("Error reading login request", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("Authentication successful for principal: {}", authResult.getPrincipal());
        String jwt = jwtUtil.generateToken(authResult.getName());

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", jwt);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        logger.warn("Authentication failed", failed);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
