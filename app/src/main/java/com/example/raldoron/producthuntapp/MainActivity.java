package com.example.raldoron.producthuntapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.raldoron.producthuntapp.Models.Posts;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String RETROFIT_TAG = "Retrofit";

    private LinearLayoutManager layoutManager;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private Posts products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new Posts();

        productsRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        layoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(layoutManager);


        ProductHunt.getAPI().getData(Config.ACCESS_TOKEN, "tech").enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.d(RETROFIT_TAG, response.toString());
                products.setPosts(response.body());
                productAdapter = new ProductAdapter(products, new ProductAdapter.ListListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                        intent.putExtra("product", products.getPosts().get(position));
                        startActivity(intent);
                    }
                });
                productsRecyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Something wrong!", Toast.LENGTH_LONG).show();
                Log.e(RETROFIT_TAG, t.getMessage());
            }
        });
    }
}
