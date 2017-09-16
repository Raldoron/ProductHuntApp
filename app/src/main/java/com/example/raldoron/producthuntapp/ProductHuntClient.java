package com.example.raldoron.producthuntapp;

import android.content.Context;
import android.widget.Toast;

import com.example.raldoron.producthuntapp.Models.Categories;
import com.example.raldoron.producthuntapp.Models.Posts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Raldoron on 12.09.17.
 */

public class ProductHuntClient {

    private ResponseListener responseListener;
    private CategoriesListener categoriesListener;

    public ProductHuntClient (ResponseListener responseListener, CategoriesListener categoriesListener){
        this.responseListener = responseListener;
        this.categoriesListener = categoriesListener;
    }

    public void getPosts(String category, final Context context) {
        ProductHunt.getAPI().getPosts(Config.ACCESS_TOKEN, category).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.isSuccessful()) {
                    responseListener.onSuccess(response);
                } else {
                    Toast.makeText(context, "Get posts: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(context, "Something wrong with posts!", Toast.LENGTH_LONG).show();
                responseListener.onFailure();
            }
        });
    }

    public void getCategories(final Context context) {
        ProductHunt.getAPI().getCategories(Config.ACCESS_TOKEN).enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    categoriesListener.onSuccess(response);
                } else {
                    Toast.makeText(context, "Get categories: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(context, "Something wrong with categories!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface ResponseListener{
        void onSuccess(Response<Posts> response);
        void onFailure();
    }

    public interface CategoriesListener{
        void onSuccess(Response<Categories> response);
    }
}
