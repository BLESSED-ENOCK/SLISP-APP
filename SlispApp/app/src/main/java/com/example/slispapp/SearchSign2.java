package com.example.slispapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchSign2 {
    private String SignImage;
    private String EnglishName = "";
    private String LugandaName = "";
    private String Description = "";


    public SearchSign2 (String signimage,  String englishname, String lugandaname, String description){
        this.SignImage = signimage;
        this.EnglishName = englishname;
        this.LugandaName = lugandaname;
        this.Description = description;
    }

    public String getLugandaName() {
        return LugandaName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public String getSignImage() {
        return SignImage;
    }

    public String getDescription() {
        return Description;
    }
}
