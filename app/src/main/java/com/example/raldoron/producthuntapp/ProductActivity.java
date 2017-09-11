package com.example.raldoron.producthuntapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raldoron.producthuntapp.Models.Product;
import com.squareup.picasso.Picasso;

/**
 * Created by Raldoron on 11.09.17.
 */

public class ProductActivity extends AppCompatActivity {

    private Product product;

    private TextView name;
    private ImageView screenshot;
    private TextView description;
    private TextView upvotes;
    private Button redirect_button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product = getIntent().getParcelableExtra("product");

        name = (TextView) findViewById(R.id.name);
        screenshot = (ImageView) findViewById(R.id.screenshot);
        description = (TextView) findViewById(R.id.description);
        upvotes = (TextView) findViewById(R.id.upvotes);
        redirect_button = (Button) findViewById(R.id.redirect_button);

        if (product != null) {
            name.setText(product.getName());
            description.setText(product.getDescription());
            upvotes.setText("Upvotes: " + String.valueOf(product.getUpvotes()));

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
