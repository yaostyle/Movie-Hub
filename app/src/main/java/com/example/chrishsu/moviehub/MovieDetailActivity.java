package com.example.chrishsu.moviehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chrishsu.moviehub.data.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private String mTitle;
    private String mReleaseDate;
    private String mImage;
    private String mReviewAvg;
    private String mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        
        if (intent != null) {
            mTitle = intent.getStringExtra(Movie.EXTRA_TITLE);
            mReleaseDate = intent.getStringExtra(Movie.EXTRA_RELEASE_DATE);
            mImage = intent.getStringExtra(Movie.EXTRA_IMAGE);
            mReviewAvg = intent.getStringExtra(Movie.EXTRA_REVIEW_AVG);
            mOverview = intent.getStringExtra(Movie.EXTRA_OVERVIEW);
            setTitle(mTitle);
        }


    }
}
