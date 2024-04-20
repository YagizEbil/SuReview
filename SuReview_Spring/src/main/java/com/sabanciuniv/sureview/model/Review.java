package com.sabanciuniv.sureview.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Review {
    @Id
    private String id;
    private String courseOfferingId;
    private String userId;
    private int rating;
    private String text;
}