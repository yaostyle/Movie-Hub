package com.example.chrishsu.moviehub.utilities;

import android.content.Context;
import android.util.Log;

import com.example.chrishsu.moviehub.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static ArrayList<Movie> parseJson(Context context, String json) {
        ArrayList<Movie> parsedMovieData = new ArrayList<>();

        final String KEY_RES_ARRAY = "results";
        final String KEY_TITLE = "title";
        final String KEY_RELEASE_DATE = "release_date";
        final String KEY_IMAGE = "poster_path";
        final String KEY_VOTE_AVG = "vote_average";
        final String KEY_OVERVIEW = "overview";

        try {
            JSONObject jsonBaseObj = new JSONObject(json);

            JSONArray jsonResultArray = jsonBaseObj.getJSONArray(KEY_RES_ARRAY);
            for (int i=0; i<jsonResultArray.length(); i++) {
                JSONObject singleMovieJson = jsonResultArray.getJSONObject(i);
                String titleString = singleMovieJson.getString(KEY_TITLE);
                String releaseDateString = singleMovieJson.getString(KEY_RELEASE_DATE);
                String imageString = singleMovieJson.getString(KEY_IMAGE);
                String voteAvgString = singleMovieJson.getString(KEY_VOTE_AVG);
                String overviewString = singleMovieJson.getString(KEY_OVERVIEW);
                parsedMovieData.add(new Movie(titleString, releaseDateString,
                        imageString, voteAvgString, overviewString));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedMovieData;
    }
}
