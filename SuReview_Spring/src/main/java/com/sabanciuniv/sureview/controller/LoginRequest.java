package com.sabanciuniv.sureview.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    // Setter for username
    // Getter for username
    private String username;
    // Setter for email
    // Getter for email
    private String email;

    // Constructor
    public LoginRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Default constructor
    public LoginRequest() {}

}
