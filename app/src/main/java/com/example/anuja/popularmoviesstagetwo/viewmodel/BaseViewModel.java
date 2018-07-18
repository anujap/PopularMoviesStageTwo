package com.example.anuja.popularmoviesstagetwo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.anuja.popularmoviesstagetwo.app.MovieApplication;
import com.example.anuja.popularmoviesstagetwo.app.service.MovieDbService;

public abstract class BaseViewModel extends AndroidViewModel {

    // reference to the movie service
    protected MovieDbService movieDbService;

    public BaseViewModel(Application application) {
        super(application);

        movieDbService = ((MovieApplication)application).getMovieService();
    }
}
