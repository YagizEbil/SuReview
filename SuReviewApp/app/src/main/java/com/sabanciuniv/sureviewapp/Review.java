package com.sabanciuniv.sureviewapp;

public class Review {
    private User user;

    private String content;
    private int rating;

    public Review() {}
    public Review(User user,String content,int rating) {
        this.user = user;
        this.rating = rating;
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }
}
