package com.sabanciuniv.sureview.security;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String displayName;
}
