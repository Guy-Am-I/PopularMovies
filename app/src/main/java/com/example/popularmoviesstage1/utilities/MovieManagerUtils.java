package com.example.popularmoviesstage1.utilities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.popularmoviesstage1.MainActivity;
import com.example.popularmoviesstage1.MovieDetailActivity;
import com.example.popularmoviesstage1.MoviePostersAdapter;
import com.example.popularmoviesstage1.data.Movie;

import java.net.URL;

/**
 * Class for managing operations regarding movies
 */
public class MovieManagerUtils implements MoviePostersAdapter.MovieAdapterOnClickHandler{

    private final String TAG = "MovieManager";

    private MainActivity callerActivity;
    private int options;
    private Movie[] movieData;
    private MoviePostersAdapter mMovieAdapter;


    public MovieManagerUtils(Context context) {
        callerActivity = (MainActivity) context;
        mMovieAdapter = new MoviePostersAdapter(this);

    }

    public MoviePostersAdapter getMovieAdapter() {
        return this.mMovieAdapter;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    public Movie[] executeFetchAsyncTask() {
        new FetchMoviesTask().execute(options);
        return movieData;
    }


    public class FetchMoviesTask extends AsyncTask<Integer, Boolean, Movie[]>  {
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

    @Override
    public void onClick(Movie movieClicked) {
        //create intent
        Class destinationActivity = MovieDetailActivity.class;
        Intent goToMovieDetail = new Intent(callerActivity, destinationActivity);

        //movie details as string - CHANGE IN FUTURE
        //0 - title, 1 - release date, 2-user rating, 3- poster path, 5 - overview
        String[] movieDetails = new String[5];
        movieDetails[0] = movieClicked.getTitle();
        movieDetails[1] = movieClicked.getReleaseDate();
        movieDetails[2] = "" + movieClicked.getUser_rating();
        movieDetails[3] = movieClicked.getPoster_path();
        movieDetails[4] = movieClicked.getOverview();

        goToMovieDetail.putExtra(Intent.EXTRA_TEXT, movieDetails);

        callerActivity.startIntent(goToMovieDetail);

    }
}
