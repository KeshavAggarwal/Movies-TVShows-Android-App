package com.app.techvalley.movies.network;

import com.app.techvalley.movies.models.BackdropImage;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 20/01/17.
 */

public class ImageResponse {
    @SerializedName("backdrops")
    private ArrayList<BackdropImage> bannerImageLinks;

    public ArrayList<BackdropImage> getBannerImageLinks() {
        return bannerImageLinks;
    }

    public void setBannerImageLinks(ArrayList<BackdropImage> bannerImageLinks) {
        this.bannerImageLinks = bannerImageLinks;
    }
}
