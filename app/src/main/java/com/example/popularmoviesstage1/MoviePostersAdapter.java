package com.example.popularmoviesstage1;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//adapter to handle the recylcler view
public class MoviePostersAdapter extends RecyclerView.Adapter<MoviePostersAdapter.MoviePostersViewHolder> {

    private Movie[] movies;

    @Override
    public void onBindViewHolder(@NonNull MoviePostersViewHolder holder, int position) {

    }

    @NonNull
    @Override
    public MoviePostersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //view holder to handle each child elemebnt of the recycleView
    public class MoviePostersViewHolder extends RecyclerView.ViewHolder {

    }
}
