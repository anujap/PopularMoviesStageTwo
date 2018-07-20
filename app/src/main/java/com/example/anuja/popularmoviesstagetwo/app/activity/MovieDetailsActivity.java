package com.example.anuja.popularmoviesstagetwo.app.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.anuja.popularmoviesstagetwo.R;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.databinding.ActivityMovieDetailsBinding;
import com.example.anuja.popularmoviesstagetwo.viewmodel.MovieDetailViewModel;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.squareup.picasso.Picasso;

/**
 * This class shows the details of the movie
 */
public class MovieDetailsActivity extends BaseActivity {

    private static final String FAV_MOV_ITEM = "fav_mov_item";

    private ActivityMovieDetailsBinding mBinding;
    private MoviesEntity movie = null;

    // viewmodel
    private MovieDetailViewModel viewModel;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        // get the viewmodel
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        setUpActionBar();
        retrieveIntent(savedInstanceState);
        displayMovieDetails();
        performFABClick();
    }

    /**
     * Function called to handle the action bar
     */
    private void setUpActionBar() {
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Function called to get the intent - this intent
     * has the details about the movie that was clicked
     * in the previous activity
     */
    private void retrieveIntent(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.MOVIE_DETAIL_ITEM)) {
            movie = intent.getParcelableExtra(MainActivity.MOVIE_DETAIL_ITEM);
            toggleFavButton(savedInstanceState);
        }
    }

    /**
     * Function called to toggle the favorite button
     */
    private void toggleFavButton(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(FAV_MOV_ITEM)) {
            isFavorite = savedInstanceState.getBoolean(FAV_MOV_ITEM);
            toggleFABImageResource();
        }
        else {
            setFavButton(movie.getId());
        }
    }

    /**
     * function called to get fav column info
     * @param id - movie id
     */
    private void setFavButton(int id) {
        viewModel.isMovieFavById(id).observe(this, isFav -> {

            if(isFav != null)
                this.isFavorite = isFav;
            else
                this.isFavorite = false;

            toggleFABImageResource();
        });
    }

    /**
     * Function called to display movie specific information
     */
    private void displayMovieDetails() {
        if(movie != null) {
            setTitle(movie.getTitle());
            mBinding.tvMovieTitle.setText(movie.getOriginalTitle());
            mBinding.tvMovieRelease.setText(movie.getReleaseDate());
            mBinding.tvMovieRatings.setText(String.valueOf(movie.getVoteAverage()));
            mBinding.tvMoviePlot.setText(movie.getOverview());

            String imageBackdropPath = MovieUtils.BASE_IMG_URL + MovieUtils.LARGE_IMG_SIZE + movie.getBackdropPath();
            Picasso.with(this)
                    .load(imageBackdropPath)
                    .fit()
                    .into(mBinding.ivMovieBackdrop);

            String imagePosterPath = MovieUtils.BASE_IMG_URL + MovieUtils.THUMB_IMG_SIZE + movie.getPosterPath();
            Picasso.with(this)
                    .load(imagePosterPath)
                    .fit()
                    .into(mBinding.ivMovieDtPoster);

        }

    }

    private void performFABClick() {
        mBinding.fab.setOnClickListener(v -> {
            handleFavoriteMovieClick();
        });
    }

    /**
     * Function called to handle the favorite button click.
     * This button will insert/delete the movie from the database
     */
    private void handleFavoriteMovieClick() {
        if(!isFavorite)
            insertMovie();
        else
            deleteMovie();
    }

    /**
     * Function called to insert the movie into the database
     */
    private void insertMovie() {
        isFavorite = true;
        movie.setFavorite(isFavorite);
        viewModel.insertMovie(movie);
        showSnackBar(mBinding.detailsCoordinatorLayout, R.string.str_mv_favorite);
    }

    /**
     * Function called to delete movie from the database
     */
    private void deleteMovie() {
        isFavorite = false;
        //movie.setFavorite(isFavorite);
        viewModel.deleteMovie(movie);
        showSnackBar(mBinding.detailsCoordinatorLayout, R.string.str_mv_unfavorite);
    }

    /**
     * Function called to toggle favorite button image
     */
    private void toggleFABImageResource() {
        if(isFavorite)
            mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_fab_selected));
        else
            mBinding.fab.setImageDrawable(getDrawable(R.drawable.ic_fab_unselected));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(FAV_MOV_ITEM, isFavorite);
    }

    /**
     * Function called when the connection is available
     */
    @Override
    protected void onConnected() {
        //retrieveTrailersAndReviews();
    }

    /**
     * Function called when the connection is unavailable
     */
    @Override
    protected void onDisconnected() {
        showSnackBar(mBinding.detailsCoordinatorLayout, R.string.no_connection_message);
    }
 }
