package com.example.anuja.popularmoviesstagetwo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anuja.popularmoviesstagetwo.data.dal.MoviesDAO;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;

/**
 * Contains the database holder and serves as the main access point
 * for the underlying connection to the app's persisted, relational data
 *
 * Reference:- https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
 */
@Database(entities = {MoviesEntity.class}, version = 1, exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MoviesDAO moviesDAO();
    private static MoviesDatabase mInstance;

    public static MoviesDatabase getDatabase(Context context) {
        if(mInstance == null) {
            synchronized (MoviesDatabase.class) {
                if(mInstance == null) {
                    // creates the database
                    mInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, "movie_database").build();
                }
            }
        }

        return mInstance;
    }
}
