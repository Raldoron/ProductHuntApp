package com.example.raldoron.producthuntapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.raldoron.producthuntapp.Models.Categories;
import com.example.raldoron.producthuntapp.Models.Posts;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private LinearLayoutManager layoutManager;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Toolbar toolbar;
    private Spinner spinner;
    private CategoriesAdapter categoriesAdapter;
    private Categories categories;
    private String selected_category;
    private Posts products;
    private ProductHuntClient productHuntClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.spinner);

        productsRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        layoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(layoutManager);

        products = new Posts();
        categories = new Categories();
        selected_category = "tech";


        productHuntClient = new ProductHuntClient(new ProductHuntClient.ResponseListener() {
            @Override
            public void onSuccess(Response<Posts> response) {
                products.setPosts(response.body());
                productAdapter.update(products);
                productsRecyclerView.setAdapter(productAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, new ProductHuntClient.CategoriesListener() {
            @Override
            public void onSuccess(Response<Categories> response) {
                categories.setCategories(response.body());
                categoriesAdapter.update(categories);
                spinner.setAdapter(categoriesAdapter);
            }
        });
        productHuntClient.getCategories(MainActivity.this);
        productHuntClient.getPosts(selected_category, MainActivity.this);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                swipeRefreshLayout.setRefreshing(true);
                selected_category = categories.getCategories().get(position).getSlug();
                productHuntClient.getPosts(selected_category, MainActivity.this);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        categoriesAdapter = new CategoriesAdapter(MainActivity.this, categories.getCategories());
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
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        productHuntClient.getPosts(selected_category, MainActivity.this);
    }
}
