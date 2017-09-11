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
    private ListListener listener;

    public ProductAdapter(Posts posts, ListListener listener) {
        this.products = posts.getPosts();
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productUpvotes.setText("Upvotes: " + String.valueOf(product.getUpvotes()));
        if (product.getThumbnail() != null) {
            Picasso.with(holder.productThumbnail.getContext())
                    .load(product.getThumbnail().getImage_url())
                    .into(holder.productThumbnail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (products == null)
            return 0;
        else
            return products.size();
    }

    public interface ListListener {
        void onClick(int position);
    }
}
