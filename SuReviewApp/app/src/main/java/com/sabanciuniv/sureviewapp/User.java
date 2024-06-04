package com.sabanciuniv.sureviewapp;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String DisplayName;



    public User() {}

    public User(String email,String DisplayName){
        this.email = email;
        this.DisplayName = DisplayName;
    }
}
