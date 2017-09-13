package com.example.raldoron.producthuntapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raldoron on 13.09.17.
 */

public class Categories {

    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories.categories;
    }
}
