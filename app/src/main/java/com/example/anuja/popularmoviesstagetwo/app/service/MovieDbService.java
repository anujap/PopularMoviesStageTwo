package com.example.anuja.popularmoviesstagetwo.app.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.anuja.popularmoviesstagetwo.data.dal.MoviesDAO;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.database.MoviesDatabase;

import java.util.List;

/**
 * This class is used to handle data operations.
 * Reference:- https://developer.android.com/topic/libraries/architecture/livedata
 */
public class MovieDbService {

    private MutableLiveData<List<MoviesEntity>> mAllMovies;

    private static MovieDbService movieDbServiceInstance;
    private MoviesDatabase moviesDatabase;
    private MoviesDAO mMovieDao;

    // gets a handle to the database and initializes the member variables
    public static MovieDbService getInstance(MoviesDatabase moviesDatabase) {
        if(movieDbServiceInstance == null) {
            synchronized (MovieDbService.class) {
                if(movieDbServiceInstance == null) {
                    movieDbServiceInstance = new MovieDbService(moviesDatabase);
                }
            }
        }
        return movieDbServiceInstance;
    }

    public MovieDbService(MoviesDatabase moviesDatabase) {
        this.moviesDatabase = moviesDatabase;
        mMovieDao = moviesDatabase.moviesDAO();
    }

    /**
     * Wrapper for the insert method.
     * This operation needs to be performed on the non UI thread.
     * @param moviesEntity - entity that is to be inserted
     */
    public void insert(MoviesEntity moviesEntity) {
        new InsertAsyncTask(mMovieDao).execute(moviesEntity);
    }

    /**
     * Wrapper for the delete method
     * This operation needs to be performed on the non UI thread
     * @param moviesEntity - entity that is to be deleted
     */
    public void delete(MoviesEntity moviesEntity) {
        new DeleteAsyncTask(mMovieDao).execute(moviesEntity);
    }

    /**
     * Function called to get the isFavorite column of the movie by id
     */
    public LiveData<Boolean> isMovieFavById(int id) {
        return mMovieDao.isMovieFavById(id);
    }

    /**
     * Function called to return all the movies that are marked favorite
     * (from the database)
     */
    public LiveData<List<MoviesEntity>> getFavoriteMovies() {
        return mMovieDao.getFavoriteMovies();
    }

    /**
     * async task to perform insert operation
     */
    private static class InsertAsyncTask extends AsyncTask<MoviesEntity, Void, Void> {

        private MoviesDAO mAsyncMoviesDao;

        InsertAsyncTask(MoviesDAO mAsyncMoviesDao) {
            this.mAsyncMoviesDao = mAsyncMoviesDao;
        }

        @Override
        protected Void doInBackground(MoviesEntity... moviesEntities) {
            mAsyncMoviesDao.insert(moviesEntities[0]);

            Log.i("Test", "movie inserted: " + moviesEntities[0].getOriginalTitle());
            return null;
        }
    }

    /**
     * async task to perform the delete operation
     */
    private static class DeleteAsyncTask extends AsyncTask<MoviesEntity, Void, Void> {

        private MoviesDAO mAsyncMoviesDao;

        DeleteAsyncTask(MoviesDAO mAsyncMoviesDao) {
            this.mAsyncMoviesDao = mAsyncMoviesDao;
        }

        @Override
        protected Void doInBackground(MoviesEntity... moviesEntities) {
            mAsyncMoviesDao.delete(moviesEntities[0]);

            Log.i("Test", "movie deleted: " + moviesEntities[0].getOriginalTitle());
            return null;
        }
    }
}
