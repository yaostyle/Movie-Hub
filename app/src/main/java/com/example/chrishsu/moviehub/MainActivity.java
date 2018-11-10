package com.example.chrishsu.moviehub;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler,
                    LoaderManager.LoaderCallbacks<String[]> {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private static final int MOVIE_LOADER_ID = 0;
    private static final String TAG = "MainActivity";

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<String[]>(this) {
            String[] mMovieData = null;

            @Override
            protected void onStartLoading() {
                if (mMovieData != null) {
                    deliverResult(mMovieData);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String[] loadInBackground() {
                return new String[0];
            }

            @Override
            public void deliverResult(@Nullable String[] data) {
                mMovieData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {
        //mLoadingInidicator.setVisibility(View.INVISIBLE);
        if (null == data) {
            //showErrorMessage();
        } else {
            //showMovieDataView();
            mMovieAdapter.setMovieData(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {
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

        Log.d(TAG, "onCreate: " + BuildConfig.ApiKey);
    }
}
