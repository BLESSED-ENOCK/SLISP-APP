package com.example.slispapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://192.168.137.1/SlispApp/";

    @GET("SelectPost.php")
    Call<List<Comment>> getStatus();

}

