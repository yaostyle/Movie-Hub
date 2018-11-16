package com.example.chrishsu.moviehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetailActivity extends AppCompatActivity {

    private final String EXTRA_TITLE = "EXTRA_TITLE";
    private final String EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE";
    private final String EXTRA_IMAGE = "EXTRA_IMAGE";
    private final String EXTRA_REVIEW_AVG = "EXTRA_REVIEW_AVG";
    private final String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";

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
            mTitle = intent.getStringExtra(EXTRA_TITLE);
            mReleaseDate = intent.getStringExtra(EXTRA_RELEASE_DATE);
            mImage = intent.getStringExtra(EXTRA_IMAGE);
            mReviewAvg = intent.getStringExtra(EXTRA_REVIEW_AVG);
            mOverview = intent.getStringExtra(EXTRA_OVERVIEW);
            setTitle(mTitle);
        }


    }
}
