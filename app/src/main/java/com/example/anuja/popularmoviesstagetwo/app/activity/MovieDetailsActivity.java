package com.example.anuja.popularmoviesstagetwo.app.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.anuja.popularmoviesstagetwo.R;
import com.example.anuja.popularmoviesstagetwo.databinding.ActivityMovieDetailsBinding;
import com.example.anuja.popularmoviesstagetwo.model.MovieDetails;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * This class shows the details of the movie
 *
 * Note:- This class currently does not extend from the BaseActivity.
 * But for the Phase2, it will.
 *
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding mBinding;

    private MovieDetails movie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        setUpActionBar();
        retrieveIntent();
        displayMovieDetails();
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
    private void retrieveIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.MOVIE_DETAIL_ITEM)) {
            movie = intent.getParcelableExtra(MainActivity.MOVIE_DETAIL_ITEM);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
