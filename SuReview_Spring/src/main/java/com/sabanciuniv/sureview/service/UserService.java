package com.sabanciuniv.sureview.service;

import com.sabanciuniv.sureview.model.User;
import com.sabanciuniv.sureview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String email) {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        Optional<User> userByEmail = userRepository.findByEmail(email);

        if (userByUsername.isPresent() && userByEmail.isPresent()) {
            return userByUsername.get().getId().equals(userByEmail.get().getId());
        }

        return false;
    }
}

