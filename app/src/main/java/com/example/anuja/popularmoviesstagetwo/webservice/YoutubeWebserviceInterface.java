package com.example.anuja.popularmoviesstagetwo.webservice;

import com.example.anuja.popularmoviesstagetwo.model.youtubemodel.YoutubePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * An Interface (Retrofit turns your HTTP API into a Java interface.)
 */
public interface YoutubeWebserviceInterface {

    @GET(YoutubeUtils.YOUTUBE_PATH)
    Call<YoutubePage> getYoutubeMovieTrailers(@Query(YoutubeUtils.YOUTUBE_PARAM_ID) String id);
}
