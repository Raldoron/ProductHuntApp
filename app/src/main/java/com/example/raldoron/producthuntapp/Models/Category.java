package com.example.raldoron.producthuntapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Raldoron on 13.09.17.
 */

public class Category {

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("name")
    @Expose
    private String name;

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }
}
