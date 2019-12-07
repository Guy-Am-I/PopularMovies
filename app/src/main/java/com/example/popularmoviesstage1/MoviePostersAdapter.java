package com.example.popularmoviesstage1;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View.OnClickListener;


import com.example.popularmoviesstage1.data.Movie;
import com.squareup.picasso.Picasso;


//adapter to handle the recylcler view
public class MoviePostersAdapter extends RecyclerView.Adapter<MoviePostersAdapter.MoviePostersViewHolder> {

    private final String TAG = "MoviePostersAdapter";
    private Movie[] movies;

    private final MovieAdapterOnClickHandler mOnClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movieClicked);
    }

    /**
     * adapter for handling movies in recycler view
     * @param clickHandler on click handler for this adapter, when movie item is clicked
     */
    public MoviePostersAdapter(MovieAdapterOnClickHandler clickHandler) {
        mOnClickHandler = clickHandler;
    }

    public void setMoviesData(Movie[] moviesData) {
        this.movies = moviesData;
        notifyDataSetChanged();
    }

    //view holder to handle each child element of the recycleView
    public class MoviePostersViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private TextView mMovieTitleTV;
        private ImageView mPosterImageView;

        public MoviePostersViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            mMovieTitleTV = view.findViewById(R.id.movie_card_title);
            mPosterImageView = view.findViewById(R.id.poster_image);
        }

        @Override
        public void onClick(View view) {
            int moviePosition = getAdapterPosition();
            Movie movie = movies[moviePosition];
            mOnClickHandler.onClick(movie);
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
        Picasso.get().load(movies[position].getBackdrop_path()).into(holder.mPosterImageView);
    }

    @Override
    public int getItemCount() {
        if (movies == null) return 0;
        return movies.length;
    }
}
