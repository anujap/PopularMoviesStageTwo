package com.example.anuja.popularmoviesstagetwo.webservice;

import com.example.anuja.popularmoviesstagetwo.model.MoviePage;
import com.example.anuja.popularmoviesstagetwo.model.ReviewPage;
import com.example.anuja.popularmoviesstagetwo.model.TrailerPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * An Interface (Retrofit turns your HTTP API into a Java interface.)
 */
public interface MovieWebserviceInterface {

    /**
     * @Get declares an HTTP GET request
     * @Path("endpoint") annotation on the endpoint (popular/top_rated) parameter
     * marks it as a replacement for the {endpoint} placeholder in the @GET path
     * @Query annotation is used to get the api key in order to access the movie
     * data
     * @param endpoint - placeholder for ( popular or top_rated )
     * @param apiKey - api key
     * @return a movie object based on the specified criteria
     */
    @GET(MovieUtils.MOVIE_PATH + "{endpoint}")
    Call<MoviePage> getMovies(@Path(MovieUtils.ENDPOINT_PARAM) String endpoint, @Query(MovieUtils.API_KEY_PARAM) String apiKey);

    @GET(MovieUtils.MOVIE_PATH + "{id}" + "/videos")
    Call<TrailerPage> getMovieTrailers(@Path("id") String movieId, @Query(MovieUtils.API_KEY_PARAM) String apiKey);

    @GET(MovieUtils.MOVIE_PATH + "{id}" + "/reviews")
    Call<ReviewPage> getMovieReviews(@Path("id") String movieId, @Query(MovieUtils.API_KEY_PARAM) String apiKey);
}
