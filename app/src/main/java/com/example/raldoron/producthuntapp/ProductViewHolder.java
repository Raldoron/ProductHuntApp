package com.example.raldoron.producthuntapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Raldoron on 07.09.17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    public TextView productDescription;
    public ImageView productThumbnail;
    public TextView productUpvotes;

    public ProductViewHolder(View view){
        super(view);
        productName = (TextView) view.findViewById(R.id.product_name);
        productDescription = (TextView) view.findViewById(R.id.product_description);
        productThumbnail = (ImageView) view.findViewById(R.id.product_thumbnail);
        productUpvotes = (TextView) view.findViewById(R.id.product_upvotes);
    }
}
