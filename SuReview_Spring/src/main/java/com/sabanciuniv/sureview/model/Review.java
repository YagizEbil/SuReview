package com.sabanciuniv.sureview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;
    private String userId; // Reference to User's email as the identifier

    @DBRef
    private CourseOffering courseOffering; // Reference to a specific CourseOffering

    private String content; // The actual review text
    private int rating;     // Rating given by the user
}
