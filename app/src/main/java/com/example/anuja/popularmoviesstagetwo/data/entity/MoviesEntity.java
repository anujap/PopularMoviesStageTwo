package com.example.anuja.popularmoviesstagetwo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.anuja.popularmoviesstagetwo.common.Constants;
import com.example.anuja.popularmoviesstagetwo.utils.MovieDBTypeConverters;

import java.util.List;

/**
 * This class represents a table within the movie database.
 *
 * Each @Entity class represents an entity in a table. Specify the name of the table if
 * you want it to be different from the name of the class.
 * @PrimaryKey Every entity needs a primary key.
 * @NonNull Denotes that a parameter, field, or method return value can never be null.
 * @ColumnInfo(name = "") Specify the name of the column in the table if you want it
 * to be different from the name of the member variable.
 * Every field that's stored in the database needs to be either public or have a "getter" method
 */
@Entity(tableName = Constants.MOVIE_TABLE_NAME)
public class MoviesEntity {

    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="vote_count")
    private int voteCount;

    @NonNull
    @ColumnInfo(name="video")
    private boolean isVideo;

    @NonNull
    @ColumnInfo(name="vote_average")
    private double voteAverage;

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @NonNull
    @ColumnInfo(name="popularity")
    private double popularity;

    @NonNull
    @ColumnInfo(name="poster_path")
    private String posterPath;

    @NonNull
    @ColumnInfo(name="original_language")
    private String originalLanguage;

    @NonNull
    @ColumnInfo(name="original_title")
    private String originalTitle;

    /*
    @NonNull
    @ColumnInfo(name="genre_ids")
    private List<Integer> genreId;
    */

    @NonNull
    @ColumnInfo(name="backdrop_path")
    private String backdropPath;

    @NonNull
    @ColumnInfo(name="adult")
    private boolean isAdult;

    @NonNull
    @ColumnInfo(name="overview")
    private String overview;

    @NonNull
    @ColumnInfo(name="release_date")
    private String releaseDate;

    @NonNull
    @ColumnInfo(name="favorite")
    private boolean isFavorite;

    public int getId() {
        return id;
    }

    public void setId(int mid) {
        this.id = mid;
    }

    @NonNull
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(@NonNull int voteCount) {
        this.voteCount = voteCount;
    }

    @NonNull
    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(@NonNull boolean video) {
        isVideo = video;
    }

    @NonNull
    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(@NonNull double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(@NonNull double popularity) {
        this.popularity = popularity;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@NonNull String posterPath) {
        this.posterPath = posterPath;
    }

    @NonNull
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(@NonNull String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @NonNull
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(@NonNull String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /*
    @NonNull
    public List<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(@NonNull List<Integer> genreId) {
        this.genreId = genreId;
    }
    */

    @NonNull
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(@NonNull String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @NonNull
    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(@NonNull boolean adult) {
        isAdult = adult;
    }

    @NonNull
    public String getOverview() {
        return overview;
    }

    public void setOverview(@NonNull String overview) {
        this.overview = overview;
    }

    @NonNull
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NonNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NonNull
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(@NonNull boolean favorite) {
        isFavorite = favorite;
    }
}
