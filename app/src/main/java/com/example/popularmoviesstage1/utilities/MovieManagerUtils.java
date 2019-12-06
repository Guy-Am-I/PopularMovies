package com.example.popularmoviesstage1.utilities;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.popularmoviesstage1.MainActivity;
import com.example.popularmoviesstage1.data.Movie;

import java.net.URL;

/**
 * Class for managing operations regarding movies
 */
public class MovieManagerUtils {

    private final String TAG = "MovieManager";

    MainActivity callerActivity;
    String options = "";
    Movie[] movieData;

    public MovieManagerUtils(Context context) {
        callerActivity = (MainActivity) context;
    }
    public void setOptions(String options) {
        this.options = options;
    }

    public Movie[] executeFetchAsyncTask() {
        new FetchMoviesTask().execute(options);
        return movieData;
    }


    public class FetchMoviesTask extends AsyncTask<String, Boolean, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callerActivity.loadingMovies();
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            if (params.length == 0) return  null;
            //callerActivity = (MainActivity) params[1];

            String options = (String) params[0]; //can be "topRated" or "popular"
            URL moviesRequestUrl = NetworkUtils.buildURL(options);
            Log.d(TAG, "doInBackground: URL: " + moviesRequestUrl);

            try {
                //parse JSON data from URL
                String jsonMoviesResponse = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
                Log.d(TAG, "doInBackground: JSON: " + jsonMoviesResponse);
                Movie[] moviesArray = TheMovieDBJsonUtils.getMoviesFromJSON(callerActivity, jsonMoviesResponse);
                return moviesArray;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] moviesData) {
            callerActivity.finishedLoadingMovies(moviesData);
            movieData = moviesData;

        }
    }

}
