package com.example.raldoron.producthuntapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raldoron on 06.09.17.
 */

public class Product {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String description;
    @SerializedName("votes_count")
    @Expose
    private int upvotes;
    @SerializedName("redirect_url")
    @Expose
    private String redirect_url;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("screenshot_url")
    @Expose
    private Screenshot screenshot;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Screenshot getScreenshot() {
        return screenshot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public void setThumbnail_url(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setScreenshot_url(Screenshot screenshot) {
        this.screenshot = screenshot;
    }


}
