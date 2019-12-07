package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.popularmoviesstage1.data.Movie;
import com.example.popularmoviesstage1.utilities.MovieManagerUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MoviePostersAdapter mAdapter;
    private MovieManagerUtils manager;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_rv);
        manager = new MovieManagerUtils(this);

        //initiazlise Recycler view
        //set layout manager, set fixed size, set adapter
        mAdapter = manager.getMovieAdapter();
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true); //movie posters will always have same size

        getMovieData(R.string.most_popular_url);

    }


    private void getMovieData(Integer option_id) {
        manager.setOptions(option_id);
        manager.executeFetchAsyncTask();


    }
    public void loadingMovies() {
        //set loading indicator view to visible
        // TODO show loading view hide recycler view
    }
    public void finishedLoadingMovies(Movie[] moviesArray) {
        if(moviesArray != null) {
            //show recycler hide error
            mAdapter.setMoviesData(moviesArray);
        } //otherwise show error message
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        switch (itemID) {
            case R.id.sort_top_rated:
                mAdapter.setMoviesData(null);
                getMovieData(R.string.top_rated_url);
                return true;
            case R.id.sort_most_popular:
                mAdapter.setMoviesData(null);
                getMovieData(R.string.most_popular_url);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    public void startIntent(Intent goToIntent) {
        startActivity(goToIntent);
    }
}
