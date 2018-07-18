package com.example.anuja.popularmoviesstagetwo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The model class
 */
public class ReviewPage {

    @SerializedName("id")
    private Integer id;

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private ArrayList<ReviewResults> reviewResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("total_results")
    private Integer totalResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<ReviewResults> getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(ArrayList<ReviewResults> reviewResults) {
        this.reviewResults = reviewResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
