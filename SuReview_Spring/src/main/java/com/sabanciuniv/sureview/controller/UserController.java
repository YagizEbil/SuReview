package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private UserRepository userRepository;

    // Endpoint to retrieve a user's profile
    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    // Endpoint to update a user's profile
    @PutMapping("/profile/{email}")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser, @PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields here
        user.setDisplayName(updatedUser.getDisplayName());
        // Save the updated user information
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}