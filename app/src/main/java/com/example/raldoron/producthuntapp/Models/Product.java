package com.example.raldoron.producthuntapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raldoron on 06.09.17.
 */

public class Product implements Parcelable{

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

    protected Product(Parcel in) {
        name = in.readString();
        description = in.readString();
        upvotes = in.readInt();
        redirect_url = in.readString();
        screenshot = in.readParcelable(Screenshot.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(upvotes);
        dest.writeString(redirect_url);
        dest.writeParcelable(screenshot, flags);
    }
}
