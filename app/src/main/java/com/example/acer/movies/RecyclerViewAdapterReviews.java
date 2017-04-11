package com.example.acer.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by KeshavAggarwal on 14/03/17.
 */

public class RecyclerViewAdapterReviews extends RecyclerView.Adapter<ViewHolderReviews> {
    Context mContext;
    ArrayList<Review> mReviews;
    boolean isExpanded = false;

    public RecyclerViewAdapterReviews(ArrayList<Review> reviews, Context context) {
        mReviews = reviews;
        mContext = context;
    }

    @Override
    public ViewHolderReviews onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item_view, parent, false);
        ViewHolderReviews holder = new ViewHolderReviews(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderReviews holder, final int position) {
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
}


