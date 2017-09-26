package com.example.acer.movies.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KeshavAggarwal on 08/02/17.
 */

public class TVShow implements Serializable{
    @SerializedName("name")
    private
    String title;

    @SerializedName("first_air_date")
    private
    String date;

    @SerializedName("vote_average")
    private
    double rating;

    @SerializedName("poster_path")
    private
    String posterPath;

    @SerializedName("id")
    private
    int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
