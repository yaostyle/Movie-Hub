package com.example.chrishsu.moviehub;

import android.annotation.SuppressLint;
import android.net.Network;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chrishsu.moviehub.data.Movie;
import com.example.chrishsu.moviehub.utilities.JsonUtils;
import com.example.chrishsu.moviehub.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler,
                    LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private String mSortingBy = "pop";
    private static final int MOVIE_LOADER_ID = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<ArrayList<Movie>>(this) {
            ArrayList<Movie> mMovieData = null;

            @Override
            protected void onStartLoading() {

                if (mMovieData != null) {
                    deliverResult(mMovieData);
                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public ArrayList<Movie> loadInBackground() {
                //get any preference data
                URL movieRequestUrl = NetworkUtils.buildUrl(mSortingBy);

                try {
                    String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                    ArrayList<Movie> jsonMovieData = JsonUtils.parseJson(MainActivity.this, jsonMovieResponse);
                    return jsonMovieData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(@Nullable ArrayList<Movie> data) {
                mMovieData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (null == data) {
            //showErrorMessage();
        } else {
            showMovieDataView();
            mMovieAdapter.setMovieData(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {
        //do nothing for now but it's required
    }

    @Override
    public void onClick(String currentMovie) {
        //do nothing for now
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        GridLayoutManager layoutManager = new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT);
        layoutManager.setSpanCount(2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);
        LoaderManager.LoaderCallbacks<ArrayList<Movie>> callback = MainActivity.this;
        Bundle bundleForLoader = null;
        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, bundleForLoader, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        invalidateData();

        switch (item.getItemId()) {
            case R.id.action_main_sort_by_popular:
                mSortingBy = "pop";
                getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID
                        , null, this);
                return true;
            case R.id.action_main_sort_by_highest_rated:
                mSortingBy = "highRated";
                getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID
                        , null, this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void invalidateData() {
        mMovieAdapter.setMovieData(null);
    }

    private void showMovieDataView() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
