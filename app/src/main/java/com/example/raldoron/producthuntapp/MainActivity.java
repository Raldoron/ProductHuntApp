package com.example.raldoron.producthuntapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.raldoron.producthuntapp.Models.Categories;
import com.example.raldoron.producthuntapp.Models.Posts;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private final String PRODUCTHUNT_TAG = "PRODUCTHUNT_CLIENT";

    private LinearLayoutManager layoutManager;
    private ProductAdapter productAdapter;

    @BindView(R.id.product_list) RecyclerView productsRecyclerView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.spinner) Spinner spinner;

    private CategoriesAdapter categoriesAdapter;
    private Categories categories;
    private String selected_category;
    private Posts products;
    private ProductHuntClient productHuntClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        layoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(layoutManager);

        products = new Posts();
        categories = new Categories();
        selected_category = "tech";


        productHuntClient = new ProductHuntClient(new ProductHuntClient.ResponseListener() {
            @Override
            public void onSuccess(Response<Posts> response) {
                if (response.isSuccessful()) {
                    products.setPosts(response.body());
                    productAdapter.update(products);
                    productsRecyclerView.setAdapter(productAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(MainActivity.this, "Posts response isn't successful!", Toast.LENGTH_LONG).show();
                    Log.d(PRODUCTHUNT_TAG, response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "Failure on posts!", Toast.LENGTH_LONG).show();
                Log.e(PRODUCTHUNT_TAG, t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        }, new ProductHuntClient.CategoriesListener() {
            @Override
            public void onSuccess(Response<Categories> response) {
                if (response.isSuccessful()){
                    categories.setCategories(response.body());
                    categoriesAdapter.update(categories);
                    spinner.setAdapter(categoriesAdapter);
                    spinner.setSelection(categoriesAdapter.getPositionOfTechTopic());
                } else {
                    Toast.makeText(MainActivity.this, "Categories response isn't successful!", Toast.LENGTH_LONG).show();
                    Log.d(PRODUCTHUNT_TAG, response.message());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "Failure on categories!" + t.toString(), Toast.LENGTH_LONG).show();
                Log.e(PRODUCTHUNT_TAG, t.getMessage());
            }
        });
        productHuntClient.getCategories();
        productHuntClient.getPosts(selected_category);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                swipeRefreshLayout.setRefreshing(true);
                selected_category = categories.getCategories().get(position).getSlug();
                productHuntClient.getPosts(selected_category);
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
        productHuntClient.getPosts(selected_category);
    }
}
