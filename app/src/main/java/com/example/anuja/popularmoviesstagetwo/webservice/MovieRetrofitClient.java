package com.example.anuja.popularmoviesstagetwo.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitClient {

    private static MovieWebserviceInterface movieWebservice;

    private static MovieRetrofitClient mClient;

    public synchronized static MovieRetrofitClient getInstance() {
        if(mClient == null) {
            mClient = new MovieRetrofitClient();
        }

        return mClient;
    }

    private MovieRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieUtils.MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieWebservice = retrofit.create(MovieWebserviceInterface.class);

    }

    public MovieWebserviceInterface getMovieWebservice() {
        return movieWebservice;
    }
}
