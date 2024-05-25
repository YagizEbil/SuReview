package com.sabanciuniv.sureview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String email; // Username as fetched from the token
    private String username; // Optionally store additional user info
}
