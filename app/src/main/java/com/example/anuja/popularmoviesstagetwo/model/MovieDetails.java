package com.example.anuja.popularmoviesstagetwo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * The model class
 */
public class MovieDetails implements Parcelable {

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("id")
    private Integer id;

    @SerializedName("video")
    private Boolean isVideo;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreId;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private Boolean isAdult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public MovieDetails(Integer voteCount, Integer id, Boolean isVideo, Double voteAverage, String title,
                        Double popularity, String posterPath, String originalLanguage, String originalTitle,
                        List<Integer> genreId, String backdropPath, Boolean isAdult, String overview, String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.isVideo = isVideo;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreId = genreId;
        this.backdropPath = backdropPath;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(List<Integer> genreId) {
        this.genreId = genreId;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

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

    /**
     * flatten this object in to a Parcel.
     * @param dest - The Parcel in which the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.voteCount);
        dest.writeValue(this.id);
        dest.writeValue(this.isVideo);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeList(this.genreId);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.isAdult);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
    }

    private MovieDetails(Parcel in) {
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.genreId = new ArrayList<Integer>();
        in.readList(this.genreId, Integer.class.getClassLoader());
        this.backdropPath = in.readString();
        this.isAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        this.releaseDate = in.readString();
    }

    /**
     * generates instances of the Parcelable class from a Parcel
     */
    public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {

        /**
         * create a new instance of the Parcelable class, instantiating it
         * from the given Parcel whose data had previously been written by
         */
        @Override
        public MovieDetails createFromParcel(Parcel source) {
            return new MovieDetails(source);
        }

        // create a new array of the Parcelable class.
        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };

    /**
     * describe the kinds of special objects contained in this
     * Parcelable instance's marshaled representation.
     */
    @Override
    public int describeContents() {
        return 0;
    }
}
