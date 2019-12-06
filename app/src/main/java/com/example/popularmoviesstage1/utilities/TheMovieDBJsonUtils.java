package com.example.popularmoviesstage1.utilities;



import com.example.popularmoviesstage1.data.Movie;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
/***
 *Class for handling all JSON data received from TheMovieDB API and
 * converting it to strings we can use in app
 */
public class TheMovieDBJsonUtils {
    final static String TAG = "Movie JSON PARSER";

    static String title, poster_path, backdrop_path, overview, releaseDate;
    static int id;
    static double user_rating;
    static Movie[] moviesData = null;
    /**
     * Parse data from JSON string received via web response into an array of type movies
     * @param context
     * @param JsonMovieData JSON data from server
     * @return Array of movies for each movie represented in data
     * @throws JSONException if JSON data cannot be parsed properly
     */
    public static Movie[] getMoviesFromJSON(Context context, String JsonMovieData) throws JSONException {
        final String ERROR_MSG = "status_message";
        final String ERROR_CODE = "status_code";

        //Movie DataBase keys for JSON parsing
        final String MDB_RESULTS = "results";


        JSONObject movieJson = new JSONObject(JsonMovieData);

        if (movieJson.has(ERROR_MSG)) {
            //int errorCode = movieJson.getInt(ERROR_CODE);
            String errorMsg = movieJson.getString(ERROR_MSG);
            Log.e(TAG, "getMoviesFromJSON: " + errorMsg);
            return null;
        }

        JSONArray movieResults = movieJson.getJSONArray(MDB_RESULTS);
        moviesData = new Movie[movieResults.length()];

        for (int i = 0; i < movieResults.length(); i++){
            //retrieve each movie JSON object
            JSONObject movie_object = movieResults.getJSONObject(i);
            Movie movie = getMovieFromObject(movie_object);
            moviesData[i] = movie;
        }
        return moviesData;
    }

    public static Movie getMovieFromObject(JSONObject movie) throws JSONException {
        title = movie.getString("original_title");
        poster_path = movie.getString("poster_path");
        backdrop_path = movie.getString("backdrop_path");
        id = movie.getInt("id");
        user_rating = movie.getDouble("vote_average");
        overview = movie.getString("overview");
        releaseDate = movie.getString("release_date");

        return new Movie(title, poster_path, backdrop_path, overview,
                releaseDate, id, user_rating);
    }

}
