package com.example.anuja.popularmoviesstagetwo.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to convert the list of objects into a String
 */
public class MovieDBTypeConverters {

    @TypeConverter
    public static String fromArrayList(List<Integer> list) {
        List<String> newList = new ArrayList<String>(list.size());
        for (Integer myInt : list) {
            newList.add(String.valueOf(myInt));
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
