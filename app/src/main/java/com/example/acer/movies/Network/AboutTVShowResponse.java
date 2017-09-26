package com.example.acer.movies.network;

import com.example.acer.movies.models.Genre;
import com.example.acer.movies.models.TVShowsCreaters;
import com.example.acer.movies.models.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class AboutTVShowResponse {
    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("genres")
    ArrayList<Genre> genres;

    @SerializedName("created_by")
    ArrayList<TVShowsCreaters> tvShowsCreaters;

    @SerializedName("type")
    private String showType;

    private String status;

    @SerializedName("number_of_episodes")
    private int episodes;

    @SerializedName("number_of_seasons")
    private int seasons;

    @SerializedName("videos")
    private Video video;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<TVShowsCreaters> getTvShowsCreaters() {
        return tvShowsCreaters;
    }

    public void setTvShowsCreaters(ArrayList<TVShowsCreaters> tvShowsCreaters) {
        this.tvShowsCreaters = tvShowsCreaters;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
