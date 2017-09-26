package com.example.acer.movies.models;

import java.io.Serializable;

/**
 * Created by KeshavAggarwal on 16/02/17.
 */

public class Trailer implements Serializable {
    private String name;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
