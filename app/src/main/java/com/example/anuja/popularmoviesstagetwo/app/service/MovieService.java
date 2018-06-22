package com.example.anuja.popularmoviesstagetwo.app.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.anuja.popularmoviesstagetwo.data.dal.MoviesDAO;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.database.MoviesDatabase;

import java.util.List;

/**
 * This class is used to handle data operations.
 * Reference:- https://developer.android.com/topic/libraries/architecture/livedata
 */
public class MovieService {

    private MutableLiveData<List<MoviesEntity>> mAllMovies;

    private static MovieService movieServiceInstance;
    private MoviesDatabase moviesDatabase;
    private MoviesDAO mMovieDao;

    // gets a handle to the database and initializes the member variables
    public static MovieService getInstance(MoviesDatabase moviesDatabase) {
        if(movieServiceInstance == null) {
            synchronized (MovieService.class) {
                if(movieServiceInstance == null) {
                    movieServiceInstance = new MovieService(moviesDatabase);
                }
            }
        }
        return movieServiceInstance;
    }

    public MovieService(MoviesDatabase moviesDatabase) {
        this.moviesDatabase = moviesDatabase;
        mMovieDao = moviesDatabase.moviesDAO();
    }

    /**
     * Wrapper for the insert method.
     * This operation needs to be performed on the non UI thread.
     * @param moviesEntity
     */
    public void insert(MoviesEntity moviesEntity) {
        new InsertAsyncTask(mMovieDao).execute(moviesEntity);
    }

    public void delete(MoviesEntity moviesEntity) {
        new DeleteAsyncTask(mMovieDao).execute(moviesEntity);
    }

    public LiveData<Boolean> isMovieFavById(int id) {
        return mMovieDao.isMovieFavById(id);
    }

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
