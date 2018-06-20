package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;

import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;

public class MovieDetailViewModel extends BaseViewModel {

    public MovieDetailViewModel(Application application) {
        super(application);
    }

    /**
     * A wrapper method that calls the insert method of
     * the service class.
     * @param moviesEntity - the entity that is to be inserted
     */
    public void insertMovie(MoviesEntity moviesEntity) {
        movieService.insert(moviesEntity);
    }

    /**
     * A wrapper method that calls the delete method of the service
     * class
     * @param moviesEntity - the entity that is to be deleted
     */
    public void deleteMovie(MoviesEntity moviesEntity) {
        movieService.delete(moviesEntity);
    }

    /**
     * TODO: get the select query based on the movie id
     * and check if its favorite or not
    public boolean isFavoriteMovie() {
    }
     */
}
