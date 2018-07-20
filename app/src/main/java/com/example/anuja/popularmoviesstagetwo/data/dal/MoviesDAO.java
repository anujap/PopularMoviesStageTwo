package com.example.anuja.popularmoviesstagetwo.data.dal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anuja.popularmoviesstagetwo.utils.Constants;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * This class contains the methods used for accessing the movie database.
 * By default, all queries must be executed on a separate thread.
 *
 * Note:- the default SQL behavior is ABORT, so that you cannot insert
 * two items with the same primary key into the database.
 */

@Dao
public interface MoviesDAO {

    @Insert(onConflict = REPLACE)
    void insert(MoviesEntity moviesEntity);

    @Delete
    void delete(MoviesEntity moviesEntity);

    @Query("SELECT is_favorite FROM movie_table WHERE id=:id")
    LiveData<Boolean> isMovieFavById(int id);

    @Query(Constants.MOVIE_SELECT_QUERY)
    LiveData<List<MoviesEntity>> getFavoriteMovies();
}
