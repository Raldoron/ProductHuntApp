package com.example.raldoron.producthuntapp;

import android.content.Context;
import android.widget.Toast;

import com.example.raldoron.producthuntapp.Models.Posts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Raldoron on 12.09.17.
 */

public class ProductHuntClient {

    private ResponseListener responseListener;

    public ProductHuntClient (ResponseListener responseListener){
        this.responseListener = responseListener;
    }

    public void getPosts(String category, final Context context) {
        ProductHunt.getAPI().getData(Config.ACCESS_TOKEN, category).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(context, "Something wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface ResponseListener{
        void onSuccess(Response<Posts> response);
    }
}
