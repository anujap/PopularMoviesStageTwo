package com.example.anuja.popularmoviesstagetwo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The model class
 */
public class TrailerPage {

    @SerializedName("id")
    private Integer id;

    @SerializedName("results")
    private ArrayList<TrailerResults> trailerResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<TrailerResults> getTrailerResults() {
        return trailerResults;
    }

    public void setTrailerResults(ArrayList<TrailerResults> trailerResults) {
        this.trailerResults = trailerResults;
    }
}
