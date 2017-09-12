package com.example.raldoron.producthuntapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Raldoron on 12.09.17.
 */

public class CategoriesAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> categories;

    public CategoriesAdapter(@NonNull Context context,  @NonNull ArrayList<String> objects) {
        super(context, R.layout.item_spinner, objects);

        this.context = context;
        this.categories = objects;
    }
}
