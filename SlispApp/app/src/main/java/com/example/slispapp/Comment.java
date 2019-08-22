package com.example.slispapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment {

    @SerializedName( "CommentTime" )
    @Expose
    private String CommentTime;

    @SerializedName( "Content" )
    @Expose
    private String Content;

    @SerializedName( "Username" )
    @Expose
    private String Username;





    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String CommentTime) {
        CommentTime = CommentTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
