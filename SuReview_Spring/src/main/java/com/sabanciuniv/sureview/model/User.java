package com.sabanciuniv.sureview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
public class User {
    @Id
    private String id;
    private String email; // Username as fetched from the token
    private String displayName; // Optionally store additional user info
}
