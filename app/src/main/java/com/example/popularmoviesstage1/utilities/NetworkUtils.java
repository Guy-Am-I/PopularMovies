package com.example.popularmoviesstage1.utilities;


import android.net.Uri;
import android.util.Log;

import com.example.popularmoviesstage1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * class for handling communications with network
 */
public class NetworkUtils {
    //build url
    //create session
    //get json data from url

    private static final String API_KEY = "********";
    private static final String BASE_MOVIES_URL = "https://api.themoviedb.org/3";
    private static final String POPULAR_ADD = "/movie/popular";
    private static final String TOP_RATED_ADD = "/movie/top_rated";

    final static String API_QUERY = "api_key";
    //final static String


    private static final String format = "json";

    /**
     * Build the request URL based on queries and API_KEY
     * @param sort Optional sort ability by "popular" or "topRated"
     * @return built URL to send request
     */
    public static URL buildMoviesURL(int sort) {
        //choose url based on populairty or top rated
        String parseUrl = BASE_MOVIES_URL;
        if (sort == R.string.most_popular_url) {
            Log.d("Most Popular", "buildMoviesURL: ");
            parseUrl = parseUrl + POPULAR_ADD;
        }
        else if (sort == R.string.top_rated_url) {
            parseUrl = parseUrl + TOP_RATED_ADD;
        }

        //add optional queries such as language, page to show ...
        Uri builtUri = Uri.parse(parseUrl).buildUpon()
                .appendQueryParameter(API_QUERY, API_KEY)
                .build();

        URL requestUrl = null;
        try {
            requestUrl = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return requestUrl;

    }

    /**
     * return the result from the url request (HTTP response)
     * using scanner method
     * @param url the URL to fetch request from
     * @return the contents of the result/response
     * @throws IOException in case of network error / failing to fetch data throw an error
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                Log.d("URL Error", "getResponseFromHttpUrl: proper scanner");
                return scanner.next();
            } else {
                Log.d("URL Error", "getResponseFromHttpUrl: null scanner");
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
