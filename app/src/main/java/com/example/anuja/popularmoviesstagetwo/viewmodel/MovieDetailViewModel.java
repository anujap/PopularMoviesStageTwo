package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;

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
     * This function checks if the movie exists in the database.
     * @param id - the movie id
     * @return - the entity if exists in the datasbase
     */
    public LiveData<Boolean> isMovieFavById(int id) {
        return movieService.isMovieFavById(id);
    }

    /**
     * TODO: get the select query based on the movie id
     * and check if its favorite or not
    public boolean isFavoriteMovie() {
    }
     */
}
