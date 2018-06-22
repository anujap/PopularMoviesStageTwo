package com.example.anuja.popularmoviesstagetwo.utils;

/**
 * Class used to define constants
 */
public class Constants {

    // database specific constants
    public static final String MOVIE_TABLE_NAME = "movie_table";
    public static final String MOVIE_SELECT_QUERY = "SELECT * FROM " + MOVIE_TABLE_NAME;
    public static final String MOVIE_SELECT_QUERY_ID = "SELECT * FROM " + MOVIE_TABLE_NAME + "WHERE id=";
}
