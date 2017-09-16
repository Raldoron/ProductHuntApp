package com.example.raldoron.producthuntapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raldoron on 10.09.17.
 */

public class Thumbnail {

    @SerializedName("image_url")
    @Expose
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

}
