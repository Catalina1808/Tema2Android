package com.example.homework2.models;

public class Post extends Entertainment {

    public String id;
    public String userId;
    public String title;
    public String body;

    public Post( String id, String userId, String title, String body) {
        super(EntertainmentType.POST);
        this.id=id;
        this.title=title;
        this.body=body;
    }

    public Post( String id, String userId, String title) {
        super(EntertainmentType.POST);
        this.id=id;
        this.title=title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
