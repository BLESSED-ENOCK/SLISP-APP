package com.example.slispapp;

public class Post2 {
    //private String SignImage;
    private String Usename = "";
    private String PostTime = "";
    private String Content = "";


    public Post2 (  String Username, String PostTime, String Content){
       // this.SignImage = signimage;
        this.Usename = Username;
        this.PostTime = PostTime;
        this.Content = Content;
    }

    public String getUsename() {
        return Usename;
    }

    public String getPostTime() {
        return PostTime;
    }

   /* public String getSignImage() {
        return SignImage;
    } */

    public String getContent() {
        return Content;
    }
}
