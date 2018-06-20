package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.anuja.popularmoviesstagetwo.app.MovieApplication;
import com.example.anuja.popularmoviesstagetwo.app.service.MovieService;

public abstract class BaseViewModel extends AndroidViewModel {

    // reference to the movie service
    protected MovieService movieService;

    public BaseViewModel(Application application) {
        super(application);

        movieService = ((MovieApplication)application).getMovieService();
    }
}
