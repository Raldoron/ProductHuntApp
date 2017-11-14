package com.example.raldoron.producthuntapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raldoron.producthuntapp.Models.Product;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Raldoron on 11.09.17.
 */

public class ProductActivity extends AppCompatActivity {

    private Product product;

    @BindView(R.id.product_toolbar) Toolbar toolbar;

    @BindView(R.id.name) TextView name;
    @BindView(R.id.screenshot) ImageView screenshot;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.upvotes) TextView upvotes;
    @BindView(R.id.redirect_button) Button redirect_button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        product = getIntent().getParcelableExtra("product");

        if (product != null) {
            name.setText(product.getName());
            description.setText(product.getDescription());
            upvotes.setText(
                    getResources().getString(
                            R.string.upvotes_string,
                            product.getUpvotes()
                    )
            );

            if (product.getScreenshot().getScreenshotUrl() != null) {
                Picasso.with(getBaseContext())
                        .load(product.getScreenshot().getScreenshotUrl())
                        .into(screenshot);
            }

            if (product.getRedirect_url() != null) {
                redirect_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getRedirect_url()));
                        startActivity(intent);
                    }
                });
            }
        }

    }
}
