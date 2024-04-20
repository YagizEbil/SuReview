package com.sabanciuniv.sureview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Course {
    @Id
    private String id;
    private String title;
    private String description;
}
