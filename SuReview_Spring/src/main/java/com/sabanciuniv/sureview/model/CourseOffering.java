package com.sabanciuniv.sureview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CourseOffering {
    @Id
    private String id;
    private String courseId;
    private String professorId;
    private String semester;
}
