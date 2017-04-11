package com.example.acer.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 06/01/17.
 */

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    ArrayList<Movie> mMovies;
    Context mContext;
    private OnRecyclerViewitemClicklistener listener;
    private int verticalPosition;

    public Recycler_View_Adapter(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // View v = View.inflate(parent.getContext(),R.layout.horizontal_cardview_movies,parent);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_cardview_movies, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final View_Holder holder, final int position) {
        if (mMovies != null) {
            holder.movieName.setText(mMovies.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mMovies.get(position).posterPath).into(holder.movieThumbnailImage);
            if (mMovies.get(position).getDate().length() >= 5) {
                String date = mMovies.get(position).date.substring(0, 4);
                holder.movieReleaseDate.setText(date);
            }
            String rating = Double.toString(mMovies.get(position).rating);
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onRecyclerViewItemClicked(verticalPosition, position, holder.movieThumbnailImage);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public void setOnItemClickListener(OnRecyclerViewitemClicklistener listener, int verticalPosition) {
        this.listener = listener;
        this.verticalPosition = verticalPosition;
    }
}
