package com.example.acer.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KeshavAggarwal on 20/01/17.
 */

public class BackdropImage {

    @SerializedName("file_path")
    String bannerImageLink;

    public String getBannerImageLink() {
        return bannerImageLink;
    }

    public void setBannerImageLink(String bannerImageLink) {
        this.bannerImageLink = bannerImageLink;
    }
}
