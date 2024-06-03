package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.security.UserLoginRequest;
import com.sabanciuniv.sureview.service.CustomUserDetailsService;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserLoginRequest loginRequest) {
        return userDetailsService.registerUser(loginRequest.getEmail(), loginRequest.getDisplayName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or username format"));
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            // Perform the authentication using the custom filter's logic
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), "")
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid email or username");
        }
    }
}
