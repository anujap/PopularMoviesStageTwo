package com.example.anuja.popularmoviesstagetwo.app;

import android.app.Application;

import com.example.anuja.popularmoviesstagetwo.app.service.MovieDbService;
import com.example.anuja.popularmoviesstagetwo.database.MoviesDatabase;

/**
 * The movie application class
 */
public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MoviesDatabase getMovieDatabase() {
        return MoviesDatabase.getDatabase(this);
    }

    public MovieDbService getMovieService() {
        return MovieDbService.getInstance(getMovieDatabase());
    }
}
