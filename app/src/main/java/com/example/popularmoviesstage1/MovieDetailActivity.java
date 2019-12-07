package com.example.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.popularmoviesstage1.R;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mPosterImage;
    private TextView mTitle, mReleaseDate, mUserRating, mOverview;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.movie_detail);

        mPosterImage = findViewById(R.id.poster_description_image);
        mTitle = findViewById(R.id.title_description);
        mUserRating = findViewById(R.id.user_rating_description);
        mReleaseDate = findViewById(R.id.release_date_description);
        mOverview = findViewById(R.id.overview_description);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
          String[] data = intentThatStartedThisActivity.getStringArrayExtra(Intent.EXTRA_TEXT);

          mTitle.setText(data[0]);
        }

    }
}
