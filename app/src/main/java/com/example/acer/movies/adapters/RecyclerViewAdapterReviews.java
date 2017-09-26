package com.example.acer.movies.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.movies.R;
import com.example.acer.movies.models.Review;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 14/03/17.
 */

public class RecyclerViewAdapterReviews extends RecyclerView.Adapter<RecyclerViewAdapterReviews.ViewHolder> {
    Context mContext;
    private ArrayList<Review> mReviews;
    private boolean isExpanded = false;

    public RecyclerViewAdapterReviews(ArrayList<Review> reviews, Context context) {
        mReviews = reviews;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String secondInitialOfAuthor = null;
        for (int i = 0; i < mReviews.get(position).getAuthor().length(); i++) {
            if (mReviews.get(position).getAuthor().charAt(i) == ' ') {
                secondInitialOfAuthor = mReviews.get(position).getAuthor().substring(i + 1, i + 2);
                holder.circleTextView.setText(mReviews.get(position).getAuthor().substring(0, 1) + secondInitialOfAuthor);
                break;
            } else
                holder.circleTextView.setText(mReviews.get(position).getAuthor().substring(0, 1));
        }

        holder.reviewHeading.setText("A review by " + mReviews.get(position).getAuthor());
        holder.reviewSubheading.setText("By " + mReviews.get(position).getAuthor());
        holder.reviewTextView.setText(mReviews.get(position).getContent());

        holder.reviewTextView.post(new Runnable() {
            @Override
            public void run() {
                if (holder.reviewTextView.getLineCount() == 9) {
                    holder.readTheRestTextView.setVisibility(View.VISIBLE);
                    holder.readTheRestTextView.setText("Read More");
                    holder.readTheRestTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isExpanded) {
                                holder.reviewTextView.setMaxLines(9);
                                holder.reviewTextView.setEllipsize(TextUtils.TruncateAt.END);
                                isExpanded = false;
                                holder.readTheRestTextView.setText("read more");


                            } else {
                                holder.reviewTextView.setMaxLines(Integer.MAX_VALUE);
                                holder.reviewTextView.setEllipsize(null);
                                isExpanded = true;
                                holder.readTheRestTextView.setText("hide");

                            }

                        }
                    });
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView reviewsCardView;
        TextView reviewHeading;
        TextView reviewSubheading;
        TextView reviewTextView;
        TextView circleTextView;
        TextView readTheRestTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            reviewsCardView = (CardView) itemView.findViewById(R.id.reviewCardView);
            reviewTextView = (TextView) itemView.findViewById(R.id.reviewsTextView);
            reviewHeading = (TextView) itemView.findViewById(R.id.reviewHeading);
            circleTextView = (TextView) itemView.findViewById(R.id.circleTextView);
            reviewSubheading = (TextView) itemView.findViewById(R.id.reviewSubheading);
            readTheRestTextView = (TextView) itemView.findViewById(R.id.readtheRestTextView);
        }
    }

}


