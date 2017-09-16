package com.example.raldoron.producthuntapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raldoron on 10.09.17.
 */

public class Posts {

    @SerializedName("posts")
    @Expose
    private List<Product> posts;

    public List<Product> getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts.posts;
    }
}
