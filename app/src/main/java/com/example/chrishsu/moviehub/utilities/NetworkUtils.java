package com.example.chrishsu.moviehub.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.chrishsu.moviehub.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie";

    final static String QUERY_PARAM = "";
    final static String SORTBY_PARAM = "sort_by";
    final static String PAGE_PARAM = "page";
    final static String LANGUAGE_PARAM = "language";
    final static String APIKEY_PARAM = "api_key";

    private static final String sortBy = "popularity.desc";
    private static final String pageNo = "1";
    private static final String lang = "en-US";
    private static final String apiKey = BuildConfig.ApiKey;

    public static URL buildUrl(){
        Uri buildUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendQueryParameter(SORTBY_PARAM, sortBy)
                .appendQueryParameter(PAGE_PARAM, pageNo)
                .appendQueryParameter(LANGUAGE_PARAM, lang)
                .appendQueryParameter(APIKEY_PARAM, apiKey)
                .build();

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Build URL: " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }

        } finally {
            urlConnection.disconnect();
        }
    }
}
