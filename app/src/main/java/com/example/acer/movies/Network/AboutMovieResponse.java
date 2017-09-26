package com.example.acer.movies.network;

import com.example.acer.movies.models.Genre;
import com.example.acer.movies.models.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 24/01/17.
 */

public class AboutMovieResponse {

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("runtime")
    private int runTimeOfMovie;

    @SerializedName("budget")
    private long budget;

    @SerializedName("revenue")
    private long revenue;

    @SerializedName("genres")
    ArrayList<Genre> genres;

    @SerializedName("videos")
    private Video video;


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunTimeOfMovie() {
        return runTimeOfMovie;
    }

    public void setRunTimeOfMovie(int runTimeOfMovie) {
        this.runTimeOfMovie = runTimeOfMovie;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
