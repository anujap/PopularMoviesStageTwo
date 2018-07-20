package com.example.anuja.popularmoviesstagetwo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.anuja.popularmoviesstagetwo.utils.Constants;
import com.google.gson.annotations.SerializedName;

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
public class MoviesEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name="id")
    @SerializedName("id")
    private int id;

    @NonNull
    @ColumnInfo(name="vote_count")
    @SerializedName("vote_count")
    private int voteCount;

    @NonNull
    @ColumnInfo(name="video")
    @SerializedName("video")
    private boolean isVideo;

    @NonNull
    @ColumnInfo(name="vote_average")
    @SerializedName("vote_average")
    private double voteAverage;

    @NonNull
    @ColumnInfo(name="title")
    @SerializedName("title")
    private String title;

    @NonNull
    @ColumnInfo(name="popularity")
    @SerializedName("popularity")
    private double popularity;

    @NonNull
    @ColumnInfo(name="poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @NonNull
    @ColumnInfo(name="original_language")
    @SerializedName("original_language")
    private String originalLanguage;

    @NonNull
    @ColumnInfo(name="original_title")
    @SerializedName("original_title")
    private String originalTitle;

    /*
    @NonNull
    @ColumnInfo(name="genre_ids")
    private List<Integer> genreId;
    */

    @NonNull
    @ColumnInfo(name="backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @NonNull
    @ColumnInfo(name="adult")
    @SerializedName("adult")
    private boolean isAdult;

    @NonNull
    @ColumnInfo(name="overview")
    @SerializedName("overview")
    private String overview;

    @NonNull
    @ColumnInfo(name="release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @NonNull
    @ColumnInfo(name="is_favorite")
    private boolean isFavorite;

    public MoviesEntity(){}

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
        dest.writeString(this.backdropPath);
        dest.writeValue(this.isAdult);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
    }

    private MoviesEntity(Parcel in) {
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
        this.isAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        this.releaseDate = in.readString();
    }

    /**
     * generates instances of the Parcelable class from a Parcel
     */
    public static final Parcelable.Creator<MoviesEntity> CREATOR = new Parcelable.Creator<MoviesEntity>() {

        /**
         * create a new instance of the Parcelable class, instantiating it
         * from the given Parcel whose data had previously been written by
         */
        @Override
        public MoviesEntity createFromParcel(Parcel source) {
            return new MoviesEntity(source);
        }

        // create a new array of the Parcelable class.
        @Override
        public MoviesEntity[] newArray(int size) {
            return new MoviesEntity[size];
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
