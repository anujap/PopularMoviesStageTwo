package com.example.anuja.popularmoviesstagetwo.app.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.anuja.popularmoviesstagetwo.R;
import com.example.anuja.popularmoviesstagetwo.app.adapters.MovieGridAdapter;
import com.example.anuja.popularmoviesstagetwo.common.SortMovie;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.viewmodel.MainViewModel;

import java.util.List;

/**
 * This class displays the movies in a grid format based on
 * menu selection - Popular/Top Rated/Favorite.
 *
 * References:- https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
 */
public class MainActivity extends BaseActivity implements MovieGridAdapter.GridItemClickListener {

    // constants
    protected static final String MOVIE_DETAIL_ITEM = "movie_detail_item";
    private static final String SORT_OPTION = "sort";

    // toolbar
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MovieGridAdapter movieGridAdapter;
    private CoordinatorLayout coordinatorLayout;

    // viewmodel
    private MainViewModel mainViewModel;

    private String sortMovie = SortMovie.POPULAR.name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey(SORT_OPTION)) {
            sortMovie = String.valueOf(savedInstanceState.get(SORT_OPTION));
        }

        // get the viewmodel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setUpToolBar();
        setUpRecyclerView();

        retrieveFavoriteMovies();
    }

    /**
     * function called to set up the toolbar
     */
    private void setUpToolBar() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.action_itm_pop_mv);
        setSupportActionBar(toolbar);
    }

    /**
     * function called to set up the recycler view and display
     * the list of movies
     */
    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns()));
        movieGridAdapter = new MovieGridAdapter(null, this);
        recyclerView.setAdapter(movieGridAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(TextUtils.equals(sortMovie, SortMovie.POPULAR.name()))
            updateMenuSelection(menu.getItem(0));
        else if(TextUtils.equals(sortMovie, SortMovie.TOP_RATED.name()))
            updateMenuSelection(menu.getItem(1));
        else
            updateMenuSelection(menu.getItem(2));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_itm_popular_mv:
                sortMovie = SortMovie.POPULAR.name();
                displayMoviesOnMenuSelection(item, mainViewModel.getPopularMoviesList().getValue());
                return true;
            case R.id.action_itm_top_rated_mv:
                sortMovie = SortMovie.TOP_RATED.name();
                displayMoviesOnMenuSelection(item, mainViewModel.getTopRatedMoviesList().getValue());
                return true;
            case R.id.action_itm_fav_mv:
                sortMovie = SortMovie.FAVORITE.name();
                displayMoviesOnMenuSelection(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * function called to display the list of movies based on the menu
     * selection - Popular/Top Rated
     * @param item - Menu item
     * @param movies - list of movies (Popular/Top Rated/Favorite)
     */
    private void displayMoviesOnMenuSelection(MenuItem item, List<MoviesEntity> movies) {
        movieGridAdapter.swapLists(movies);
        updateMenuSelection(item);
    }

    /**
     * function called to display the list of movies based on the menu
     * selection - Favorite
     * @param item - Menu item
     */
    private void displayMoviesOnMenuSelection(MenuItem item) {
        retrieveFavoriteMovies();
        updateMenuSelection(item);
    }

    /**
     * function called to update the menu based on the selection
     */
    private void updateMenuSelection(MenuItem item) {
        item.setChecked(item.isChecked() ? false : true);
        toolbar.setTitle(item.getTitle().toString());
    }

    /**
     * function called to get the movies based on the sort order
     * Popular/top rated
     */
    private void retrieveQueriedMovies() {
        mainViewModel.displayMovies();

        mainViewModel.getTopRatedMoviesList().observe(this, moviesEntities -> {
            if(TextUtils.equals(sortMovie, SortMovie.TOP_RATED.name())) {
                movieGridAdapter.swapLists(moviesEntities);
            }
        });

        mainViewModel.getPopularMoviesList().observe(this, moviesEntities -> {
            if(TextUtils.equals(sortMovie, SortMovie.POPULAR.name())) {
                movieGridAdapter.swapLists(moviesEntities);
            }
        });
    }

    /**
     * function called to retrieve favorite movies from the database
     */
    private void retrieveFavoriteMovies() {
        mainViewModel.getFavoriteMoviesList().observe(this, movieEntities -> {
            movieGridAdapter.swapLists(movieEntities);

        });
    }

    /**
     * function called when the grid item is clicked.
     */
    @Override
    public void onGridItemClick(MoviesEntity movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_DETAIL_ITEM, movie);
        startActivity(intent);
    }

    /**
     * function called when the connection is available
     */
    @Override
    protected void onConnected() {
        retrieveQueriedMovies();
    }

    /**
     * function called when the connection is not available
     */
    @Override
    protected void onDisconnected() {
        showSnackBar(coordinatorLayout, R.string.no_connection_message);
    }

    /**
     * function called to auto fit the number of columns in a grid
     */
    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SORT_OPTION, sortMovie);
    }
}