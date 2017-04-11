package com.example.acer.movies;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 14/03/17.
 */

public class ReviewsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapterReviews recyclerViewAdapterReviews;
    Context context;
    ArrayList<Review> reviewsMain;
    TextView noReviewTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.reviewsRecyclerView);
        noReviewTextView = (TextView) v.findViewById(R.id.noReviewsTextView);
        reviewsMain = new ArrayList<>();
        return v;

//            View v = inflater.inflate(R.layout.fragment_no_reviews, container, false);
//            return v;

    }

    public static ReviewsFragment newInstance() {
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        return reviewsFragment;
    }

    public void setUIArguements(final Bundle args) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    reviewsMain = (ArrayList<Review>) args.getSerializable("REVIEWS");
                    Log.i("FUNTHEYTAG", String.valueOf(reviewsMain.size()));

                    if (reviewsMain.size() == 0) {
                        noReviewTextView.setVisibility(View.VISIBLE);
                        noReviewTextView.setText("No Reviews are currently available for this movie.");

                    } else {
                        Log.i("INELSE", String.valueOf(reviewsMain.size()));
                        recyclerViewAdapterReviews = new RecyclerViewAdapterReviews(reviewsMain, context);
                        recyclerView.setAdapter(recyclerViewAdapterReviews);

                        LinearLayoutManager verticalManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(verticalManager);
                    }


                }


            });
        } else {
            Log.i("TAG", "Activity null");

        }
    }
}