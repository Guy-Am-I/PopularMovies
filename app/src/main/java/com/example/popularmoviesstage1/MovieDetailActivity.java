package com.example.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mPosterImage;
    private TextView mTitle, mReleaseDate, mUserRating, mOverview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mPosterImage = findViewById(R.id.poster_description_image);
        mTitle = findViewById(R.id.title_description);
        mUserRating = findViewById(R.id.user_rating_description);
        mReleaseDate = findViewById(R.id.release_date_description);
        mOverview = findViewById(R.id.overview_description);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
          String[] data = intentThatStartedThisActivity.getStringArrayExtra(Intent.EXTRA_TEXT);
            //movie details as string - CHANGE IN FUTURE
            //0 - title, 1 - release date, 2-user rating, 3- poster path, 4 - overview
          mTitle.setText(data[0]);
          mReleaseDate.setText(data[1]);
          mUserRating.setText(data[2]);
          Picasso.get().load(data[3]).into(mPosterImage);
          mOverview.setText(data[4]);
        }

    }
}
