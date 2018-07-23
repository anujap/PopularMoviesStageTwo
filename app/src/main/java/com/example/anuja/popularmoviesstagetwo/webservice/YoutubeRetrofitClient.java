package com.example.anuja.popularmoviesstagetwo.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeRetrofitClient {

    private static YoutubeWebserviceInterface movieWebservice;

    private static YoutubeRetrofitClient mClient;

    public synchronized static YoutubeRetrofitClient getInstance() {
        if(mClient == null) {
            mClient = new YoutubeRetrofitClient();
        }

        return mClient;
    }

    private YoutubeRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YoutubeUtils.YOUTUBE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieWebservice = retrofit.create(YoutubeWebserviceInterface.class);

    }

    public YoutubeWebserviceInterface getMovieWebservice() {
        return movieWebservice;
    }
}
