package com.example.popularmoviesstage1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage1.data.Movie;

//adapter to handle the recylcler view
public class MoviePostersAdapter extends RecyclerView.Adapter<MoviePostersAdapter.MoviePostersViewHolder> {

    private final String TAG = "MoviePostersAdapter";
    private Movie[] movies;

    public MoviePostersAdapter() {
    }

    public void setMoviesData(Movie[] moviesData) {
        this.movies = moviesData;
        notifyDataSetChanged();
    }

    //view holder to handle each child element of the recycleView
    public class MoviePostersViewHolder extends RecyclerView.ViewHolder {

        private TextView mMovieTitleTV;

        public MoviePostersViewHolder(View view) {
            super(view);

            mMovieTitleTV = (TextView) view.findViewById(R.id.movie_card_title);
        }
    }

    @NonNull
    @Override
    public MoviePostersAdapter.MoviePostersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        Context context = parent.getContext();
        int layoutID = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutID, parent, false);
        return new MoviePostersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePostersViewHolder holder, int position) {
        //populate view with Movie object
        holder.mMovieTitleTV.setText(movies[position].getTitle());
    }

    @Override
    public int getItemCount() {
        if (movies == null) return 0;
        return movies.length;
    }
}
