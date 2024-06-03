package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.security.UserLoginRequest;
import com.sabanciuniv.sureview.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserLoginRequest loginRequest) {
        logger.info("Registering user with email: {}", loginRequest.getEmail());
        return userDetailsService.registerUser(loginRequest.getEmail(), loginRequest.getDisplayName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    logger.warn("Invalid email or username format for email: {}", loginRequest.getEmail());
                    return new IllegalArgumentException("Invalid email or username format");
                });
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            logger.info("Logging in user with email: {}", loginRequest.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), "")
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Login successful for user with email: {}", loginRequest.getEmail());
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            logger.warn("Login failed for user with email: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(401).body("Invalid email or username");
        }
    }
}
