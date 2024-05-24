package com.sabanciuniv.sureviewapp;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String DisplayName;

    private String Password;

    public User() {}

    public User(String email,String DisplayName,String Password){
        this.email = email;
        this.DisplayName = DisplayName;
        this.Password = Password;
    }
}
