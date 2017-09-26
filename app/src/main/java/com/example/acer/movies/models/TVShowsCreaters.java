package com.example.acer.movies.models;

import java.io.Serializable;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class TVShowsCreaters implements Serializable{

    private int id;
    private String name;
    private String profile_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
