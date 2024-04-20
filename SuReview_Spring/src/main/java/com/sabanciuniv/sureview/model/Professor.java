package com.sabanciuniv.sureview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Professor {
    @Id
    private String id;
    private String name;
    private String department;
}
