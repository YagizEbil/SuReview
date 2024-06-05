package com.sabanciuniv.sureviewapp;

public class Review {
    private String displayName;

    private String professor;

    private String course;
    private String content;
    private int rating;

    private String userId;

    private String courseOfferingId;

    public Review() {}
    public Review(String displayName,String content,int rating,String professor,String course,String courseOfferingId) {
        this.displayName = displayName;
        this.rating = rating;
        this.content = content;
        this.course = course;
        this.professor = professor;
        this.courseOfferingId = courseOfferingId;
    }

    public Review(String displayName, String content, int rating, String professor, String course, String courseOfferingId, String userId) {
        this.displayName = displayName;
        this.rating = rating;
        this.content = content;
        this.course = course;
        this.professor = professor;
        this.courseOfferingId = courseOfferingId;
        this.userId = userId;

    }

    public Review(String displayName, String content, int rating, String professor, String course) {
        this.displayName = displayName;
        this.rating = rating;
        this.content = content;
        this.course = course;
        this.professor = professor;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getCourse() {
        return course;
    }
    public String getProfessor() {
        return professor;
    }


    public String getCourseOfferingId() {return courseOfferingId;
    }

    public String getUserId() {return userId;
    }
}
