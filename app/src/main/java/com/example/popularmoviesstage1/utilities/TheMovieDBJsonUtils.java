package com.example.popularmoviesstage1.utilities;



import com.example.popularmoviesstage1.data.Movie;
import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
/***
 *Class for handling all JSON data received from TheMovieDB API and
 * converting it to strings we can use in app
 */
public class TheMovieDBJsonUtils {


    /**
     * Parse data from JSON string received via web response into an array of type movies
     * @param context
     * @param JsonData JSON data from server
     * @return Array of movies for each movie represented in data
     * @throws JSONException if JSON data cannot be parsed properly
     */
    public static Movie[] getMoviesFromJSON(Context context, String JsonData) throws JSONException {
        return null;
    }

}
