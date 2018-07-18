package com.example.anuja.popularmoviesstagetwo.model;

import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model associated with the Movie fragment
 */
public class MoviePage {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<MoviesEntity> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MoviesEntity> getResults() {
        return results;
    }

    public void setResults(ArrayList<MoviesEntity> results) {
        this.results = results;
    }
}
