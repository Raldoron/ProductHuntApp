package com.example.raldoron.producthuntapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raldoron.producthuntapp.Models.Posts;
import com.example.raldoron.producthuntapp.Models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Raldoron on 06.09.17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products){
        this.products = products;
    }
    public ProductAdapter(Posts posts) {
        this.products = posts.getPosts();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productUpvotes.setText("Upvotes: " + String.valueOf(product.getUpvotes()));
        if (product.getThumbnail() != null) {
            Picasso.with(holder.productThumbnail.getContext())
                    .load(product.getThumbnail().getImage_url())
                    .into(holder.productThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        if (products == null)
            return 0;
        else
            return products.size();
    }
}
