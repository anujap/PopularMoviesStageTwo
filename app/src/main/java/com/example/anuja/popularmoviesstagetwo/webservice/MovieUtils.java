package com.example.anuja.popularmoviesstagetwo.webservice;

/**
 * This class represents Movie Constants
 * References:- https://www.themoviedb.org/talk/5aeaaf56c3a3682ddf0010de
 *              https://developers.themoviedb.org/3/getting-started/images
 *              https://developers.google.com/youtube/v3/docs/videos/list
 */
public class MovieUtils {

    public static final String MOVIE_URL = "http://api.themoviedb.org/";
    public static final String MOVIE_PATH = "3/movie/";
    public static final String API_KEY_PARAM = "api_key";
    public static final String ENDPOINT_PARAM = "endpoint";
    public static final String API_KEY = "eea971c8e6615a1794951f8ff0ae6f2b";
    public static final String ENDPOINT_POPULARITY = "popular";
    public static final String ENDPOINT_TOP_RATED = "top_rated";

    public static final String BASE_IMG_URL = "http://image.tmdb.org/t/p/";
    public static final String THUMB_IMG_SIZE = "w185";
    public static final String LARGE_IMG_SIZE = "w500";

    public static final String TRAILER_SITE_YOUTUBE = "YouTube";
    public static final String YOUTUBE_URL_INTENT = "https://www.youtube.com/watch?v=";
}
