package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.anuja.popularmoviesstagetwo.BuildConfig;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.model.ReviewPage;
import com.example.anuja.popularmoviesstagetwo.model.ReviewResults;
import com.example.anuja.popularmoviesstagetwo.model.TrailerPage;
import com.example.anuja.popularmoviesstagetwo.model.TrailerResults;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieRetrofitClient;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieWebserviceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailViewModel extends BaseViewModel {

    private static final String TAG = "MainViewDetailsModel";

    private MovieWebserviceInterface movieWebservice = MovieRetrofitClient.getInstance().getMovieWebservice();

    private MutableLiveData<List<TrailerResults>> movieTrailerList;
    private MutableLiveData<List<ReviewResults>> movieReviewList;

    public MutableLiveData<List<TrailerResults>> getMovieTrailerList() {
        if(movieTrailerList == null)
            movieTrailerList = new MutableLiveData<>();
        return movieTrailerList;
    }

    public MutableLiveData<List<ReviewResults>> getMovieReviewList() {
        if(movieReviewList == null)
            movieReviewList = new MutableLiveData<>();
        return movieReviewList;
    }

    public MovieDetailViewModel(Application application) {
        super(application);
    }

    /**
     * function called to download the trailers and reviews specific
     * to the movie
     */
    public void displayMovieTrailersAndReviews(String id) {
        movieWebservice.getMovieTrailers(id, BuildConfig.ApiKey).enqueue(new Callback<TrailerPage>() {
            @Override
            public void onResponse(Call<TrailerPage> call, Response<TrailerPage> response) {
                if(response.isSuccessful()) {
                    List<TrailerResults> trailerResults = response.body().getTrailerResults();
                    if(trailerResults != null && trailerResults.size() > 0) {
                        movieTrailerList.postValue(trailerResults);
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerPage> call, Throwable t) {
                Log.e(TAG, "Error retrieving the trailers");
            }
        });

        movieWebservice.getMovieReviews(id, BuildConfig.ApiKey).enqueue(new Callback<ReviewPage>() {
            @Override
            public void onResponse(Call<ReviewPage> call, Response<ReviewPage> response) {
                if(response.isSuccessful()) {
                    List<ReviewResults> reviewResults = response.body().getReviewResults();
                    if(reviewResults != null && reviewResults.size() > 0) {
                        movieReviewList.postValue(reviewResults);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewPage> call, Throwable t) {
                Log.e(TAG, "Error retrieving the reviews");
            }
        });
    }



    /**
     * A wrapper method that calls the insert method of
     * the service class.
     * @param moviesEntity - the entity that is to be inserted
     */
    public void insertMovie(MoviesEntity moviesEntity) {
        movieDbService.insert(moviesEntity);
    }

    /**
     * A wrapper method that calls the delete method of the service
     * class
     * @param moviesEntity - the entity that is to be deleted
     */
    public void deleteMovie(MoviesEntity moviesEntity) {
        movieDbService.delete(moviesEntity);
    }

    /**
     * This function checks if the movie exists in the database.
     * @param id - the movie id
     * @return - the entity if exists in the datasbase
     */
    public LiveData<Boolean> isMovieFavById(int id) {
        return movieDbService.isMovieFavById(id);
    }
}
