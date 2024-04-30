package com.sabanciuniv.sureview.controller;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Endpoint to retrieve a user's profile
    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        User user = Optional.ofNullable(userService.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    // Endpoint to update a user's profile
    @PutMapping("/profile/{email}")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser, @PathVariable String email) {
        User user = Optional.ofNullable(userService.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields here
        user.setDisplayName(updatedUser.getDisplayName());
        // Save the updated user information
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }
}