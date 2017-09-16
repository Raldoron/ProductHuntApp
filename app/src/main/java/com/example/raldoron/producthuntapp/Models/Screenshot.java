package com.example.raldoron.producthuntapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raldoron on 10.09.17.
 */

public class Screenshot implements Parcelable {

    @SerializedName("300px")
    @Expose
    private String screenshot_url;

    protected Screenshot(Parcel in) {
        screenshot_url = in.readString();
    }

    public static final Creator<Screenshot> CREATOR = new Creator<Screenshot>() {
        @Override
        public Screenshot createFromParcel(Parcel in) {
            return new Screenshot(in);
        }

        @Override
        public Screenshot[] newArray(int size) {
            return new Screenshot[size];
        }
    };

    public String getScreenshotUrl() {
        return screenshot_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(screenshot_url);
    }
}
