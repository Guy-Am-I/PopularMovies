package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.popularmoviesstage1.data.Movie;
import com.example.popularmoviesstage1.utilities.MovieManagerUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MoviePostersAdapter mMovieAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_rv);

        //initiazlise Recycler view
        //set layout manager, set fixed size, set adapter
        mMovieAdapter = new MoviePostersAdapter();

        mRecyclerView.setAdapter(mMovieAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true); //movie posters will always have same size

        getMovieData();

    }


    private void getMovieData() {
        //show recycler view and hide error view
        String options = "popular";
        // String options =  <Get options from user preference in view button/text view>
        //MovieManagerUtils.FetchMoviesTask fetchMoviesTask = new MovieManagerUtils.FetchMoviesTask();
        MovieManagerUtils manager = new MovieManagerUtils(this);
        manager.setOptions(options);
        manager.executeFetchAsyncTask();


    }
    public void loadingMovies() {
        //set loading indicator view to visible
    }
    public void finishedLoadingMovies(Movie[] moviesArray) {
        if(moviesArray != null) {
            //show recycler hide error
            mMovieAdapter.setMoviesData(moviesArray);
        } //otherwise show error message
    }
}
