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
    int options;
    Movie[] movieData;

    public MovieManagerUtils(Context context) {
        callerActivity = (MainActivity) context;
    }
    public void setOptions(int options) {
        this.options = options;
    }

    public Movie[] executeFetchAsyncTask() {
        new FetchMoviesTask().execute(options);
        return movieData;
    }


    public class FetchMoviesTask extends AsyncTask<Integer, Boolean, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callerActivity.loadingMovies();
        }

        @Override
        protected Movie[] doInBackground(Integer... params) {

            if (params.length == 0) return  null;
            //callerActivity = (MainActivity) params[1];

            URL moviesRequestUrl = NetworkUtils.buildMoviesURL(params[0]);
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
