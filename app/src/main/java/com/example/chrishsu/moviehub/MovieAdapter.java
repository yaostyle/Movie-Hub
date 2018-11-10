package com.example.chrishsu.moviehub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private String[] mMovieData;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(String currentMovie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mMovieTextView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieTextView = (TextView) itemView.findViewById(R.id.movie_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String currentMovie = mMovieData[adapterPosition];
            mClickHandler.onClick(currentMovie);
        }
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String currentMovie = mMovieData[position];
        holder.mMovieTextView.setText(currentMovie);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public void setmMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

}
