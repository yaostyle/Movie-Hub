package com.example.chrishsu.moviehub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrishsu.moviehub.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private static final String TAG = "MovieAdapter";
    
    private ArrayList<Movie> mMovieData = new ArrayList<Movie>();
    private MovieAdapterOnClickHandler mClickHandler;
    private Context mContext;

    public interface MovieAdapterOnClickHandler {
        void onClick(String currentMovie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mMovieTextView;
        public final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieTextView = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.movie_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();

            String currentMovie = mMovieData.get(adapterPosition).getTitle();
            mClickHandler.onClick(currentMovie);
            Log.d(TAG, "onClick: mClickHandler: " + currentMovie.toString());

        }
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        mContext = viewGroup.getContext();
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        Movie currentMovie = mMovieData.get(position);
        holder.mMovieTextView.setText(currentMovie.getTitle());
        String baseImageUrl = "https://image.tmdb.org/t/p/w500/";

        if (currentMovie.getImage() == "null") {
            holder.mMovieImageView.setImageResource(R.drawable.movie_place_holder);
        } else {
            String imageUrl = baseImageUrl + currentMovie.getImage();
            Picasso.with(mContext).load(imageUrl).into(holder.mMovieImageView);
        }



    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.size();
    }

    public void setMovieData(ArrayList<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

}
