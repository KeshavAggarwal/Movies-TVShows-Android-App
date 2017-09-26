package com.example.acer.movies.network;

import com.example.acer.movies.models.TVShow;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 08/02/17.
 */

public class TVShowResponse {

    private int total_results;

    private int total_pages;

    @SerializedName("results")
    private ArrayList<TVShow> tvShows;

    int page;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<TVShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(ArrayList<TVShow> tvShows) {
        this.tvShows = tvShows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
