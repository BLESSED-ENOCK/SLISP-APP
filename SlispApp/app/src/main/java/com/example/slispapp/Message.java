package com.example.slispapp;

public class Message {

     String Content,username;

    public Message(String content,String username) {
        Content = content;
        this.username = username;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
