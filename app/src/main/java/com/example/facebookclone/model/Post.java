package com.example.facebookclone.model;

public class Post {
    private String status;
    private String image;

    public Post(String status, String image) {
        this.status = status;
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
}
