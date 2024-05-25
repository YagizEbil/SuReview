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

        Optional<User> userByEmail = userRepository.findByEmail(email);



        return false;
    }
}

