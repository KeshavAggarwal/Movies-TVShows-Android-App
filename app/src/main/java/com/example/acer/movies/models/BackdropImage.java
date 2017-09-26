package com.example.acer.movies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KeshavAggarwal on 20/01/17.
 */

public class BackdropImage {

    @SerializedName("file_path")
    private String bannerImageLink;

    public String getBannerImageLink() {
        return bannerImageLink;
    }

    public void setBannerImageLink(String bannerImageLink) {
        this.bannerImageLink = bannerImageLink;
    }
}
