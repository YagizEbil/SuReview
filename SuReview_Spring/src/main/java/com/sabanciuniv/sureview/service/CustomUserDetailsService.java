package com.sabanciuniv.sureview.service;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password("") // No password required
                        .authorities("USER") // Single authority for all users
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public Optional<User> registerUser(String email, String displayName) {
        if (email.endsWith("@sabanciuniv.edu") && email.startsWith(displayName)) {
            User user = new User();
            user.setEmail(email);
            user.setDisplayName(displayName);
            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty();
        }
    }
}
