package com.app.techvalley.movies.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.OnRecyclerViewItemClickListener;
import com.app.techvalley.movies.R;
import com.app.techvalley.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 06/01/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies;
    Context mContext;
    private OnRecyclerViewItemClickListener listener;
    private int verticalPosition;

    public RecyclerViewAdapter(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_cardview_movies, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mMovies != null) {
            holder.movieName.setText(mMovies.get(position).getTitle());
            Picasso.get().load(URLConstants.IMAGE_BASE_URL + mMovies.get(position).getPosterPath()).into(holder.movieThumbnailImage);
            if (mMovies.get(position).getDate().length() >= 5) {
                String date = mMovies.get(position).getDate().substring(0, 4);
                holder.movieReleaseDate.setText(date);
            }
            String rating = Double.toString(mMovies.get(position).getRating());
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(v -> {
                if (listener != null)
                    listener.onRecyclerViewItemClicked(verticalPosition, position, holder.movieThumbnailImage);
            });
        }
    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener, int verticalPosition) {
        this.listener = listener;
        this.verticalPosition = verticalPosition;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView movieThumbnailImage;
        TextView movieName;
        TextView movieReleaseDate;
        TextView rating;

        ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            movieThumbnailImage = itemView.findViewById(R.id.thumbnailImageView);
            movieName = itemView.findViewById(R.id.nameTextView);
            movieReleaseDate = itemView.findViewById(R.id.releaseDateTextView);
            rating = itemView.findViewById(R.id.ratingTextView);

        }
    }

}
