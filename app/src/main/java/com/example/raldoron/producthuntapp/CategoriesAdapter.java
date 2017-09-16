package com.example.raldoron.producthuntapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.raldoron.producthuntapp.Models.Categories;
import com.example.raldoron.producthuntapp.Models.Category;

import java.util.List;

/**
 * Created by Raldoron on 12.09.17.
 */

public class CategoriesAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categories;
    private LayoutInflater layoutInflater;

    public CategoriesAdapter(@NonNull Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCategroyRow(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCategroyRow(position, convertView, parent);
    }

    @Override
    public int getCount() {
        if (categories != null) {
            return categories.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (categories != null) {
            return categories.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getPositionOfTechTopic() {
        if (categories != null)
            for (Category category: categories)
                if (category.getName().equals("Tech"))
                    return categories.indexOf(category);
        return 0;
    }

    public View getCategroyRow(int position, View convertView, ViewGroup parent){
        View row = layoutInflater.inflate(R.layout.item_spinner, parent, false);
        TextView categoryName = (TextView) row.findViewById(R.id.category_name);
        categoryName.setText(categories.get(position).getName());
        return row;
    }

    public void update(Categories categories){
        this.categories = categories.getCategories();
    }
}
