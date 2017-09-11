package com.example.raldoron.producthuntapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raldoron on 10.09.17.
 */

public class ProductHunt {
    private static Retrofit retrofit;


    public static ProductHuntAPI getAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.PRODUCTHUNT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ProductHuntAPI.class);
    }
}
