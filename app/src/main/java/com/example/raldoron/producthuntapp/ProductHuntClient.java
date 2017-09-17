package com.example.raldoron.producthuntapp;

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

    public void getPosts(String category) {
        ProductHunt.getAPI().getPosts(Config.ACCESS_TOKEN, category).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                responseListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                responseListener.onFailure(t);
            }
        });
    }

    public void getCategories() {
        ProductHunt.getAPI().getCategories(Config.ACCESS_TOKEN).enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                categoriesListener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                categoriesListener.onFailure(t);
            }
        });
    }

    public interface ResponseListener{
        void onSuccess(Response<Posts> response);
        void onFailure(Throwable throwable);
    }

    public interface CategoriesListener{
        void onSuccess(Response<Categories> response);
        void onFailure(Throwable throwable);
    }
}
