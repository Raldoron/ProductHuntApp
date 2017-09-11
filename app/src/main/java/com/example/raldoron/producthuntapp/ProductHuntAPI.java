package com.example.raldoron.producthuntapp;

import com.example.raldoron.producthuntapp.Models.Posts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Raldoron on 07.09.17.
 */

public interface ProductHuntAPI {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Host: api.producthunt.com"
    })
    @GET("/v1/posts/all?")
    Call<Posts> getData(@Header("Authorization") String accessToken, @Query("search[category]") String categoryName);
}
