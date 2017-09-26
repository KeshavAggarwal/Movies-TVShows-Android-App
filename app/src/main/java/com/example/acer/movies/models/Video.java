package com.example.acer.movies.models;

import com.example.acer.movies.models.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 18/02/17.
 */

public class Video {

    @SerializedName("results")
    private
    ArrayList<Trailer> trailers;

    public ArrayList<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<Trailer> trailers) {
        this.trailers = trailers;
    }
}
