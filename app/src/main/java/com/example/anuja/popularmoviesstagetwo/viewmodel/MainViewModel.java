package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.model.MoviePage;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieRetrofitClient;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieWebserviceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The ViewModel class
 *
 * Note:- There are no database calls from the ViewModel. This makes
 * the code more testable.
 *
 * References:- https://proandroiddev.com/refactor-with-the-new-viewmodel-class-b334fd88bf82
 * https://github.com/googlesamples/android-architecture-components/blob/master/BasicSample/app/src/main/java/com/example/android/persistence/viewmodel/ProductListViewModel.java
 * https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
 */
public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";

    private MovieWebserviceInterface movieWebservice = MovieRetrofitClient.getInstance().getMovieWebservice();

    private MutableLiveData<List<MoviesEntity>> popularMoviesList;
    private MutableLiveData<List<MoviesEntity>> topRatedMoviesList;

    public MainViewModel(Application application) {
        super(application);
    }

    public MutableLiveData<List<MoviesEntity>> getPopularMoviesList() {
        if(popularMoviesList == null)
            popularMoviesList = new MutableLiveData<>();
        return popularMoviesList;
    }

    public MutableLiveData<List<MoviesEntity>> getTopRatedMoviesList() {
        if(topRatedMoviesList == null)
            topRatedMoviesList = new MutableLiveData<>();
        return topRatedMoviesList;
    }

    /**
     * function used to download the movies specific to the endpoints
     */
    public void displayMovies() {
        if(popularMoviesList == null) {
            movieWebservice.getMovies(MovieUtils.ENDPOINT_POPULARITY, MovieUtils.API_KEY).enqueue(new Callback<MoviePage>() {
                @Override
                public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                    if(response.isSuccessful()) {

                        List<MoviesEntity> movieList = response.body().getResults();
                        if(movieList != null && movieList.size() > 0) {
                            popularMoviesList.postValue(movieList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoviePage> call, Throwable t) {
                    Log.e(TAG, "Error retrieving the movies");
                }
            });
        }

        if(topRatedMoviesList == null) {
            movieWebservice.getMovies(MovieUtils.ENDPOINT_TOP_RATED, MovieUtils.API_KEY).enqueue(new Callback<MoviePage>() {
                @Override
                public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                    if(response.isSuccessful()) {
                        List<MoviesEntity> movieList = response.body().getResults();
                        if(movieList != null && movieList.size() > 0) {
                            topRatedMoviesList.postValue(movieList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoviePage> call, Throwable t) {
                    Log.e(TAG, "Error retrieving the movies");
                }
            });
        }
    }

    /**
     * function called to display movies marked as favorite from the
     * database
     */
    public LiveData<List<MoviesEntity>> getFavoriteMoviesList() {
        return movieDbService.getFavoriteMovies();
    }
}
