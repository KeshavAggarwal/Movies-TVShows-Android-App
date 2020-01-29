package com.app.techvalley.movies.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.techvalley.movies.R;
import com.app.techvalley.movies.models.Review;
import com.app.techvalley.movies.adapters.RecyclerViewAdapterReviews;

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
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);

        recyclerView = v.findViewById(R.id.reviewsRecyclerView);
        noReviewTextView = v.findViewById(R.id.noReviewsTextView);
        reviewsMain = new ArrayList<>();
        return v;

    }

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    public void setUIArguements(final Bundle args) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                reviewsMain = (ArrayList<Review>) args.getSerializable("REVIEWS");

                if (reviewsMain.size() == 0) {
                    noReviewTextView.setVisibility(View.VISIBLE);
                    noReviewTextView.setText("No Reviews are currently available for this movie.");

                } else {
                    recyclerViewAdapterReviews = new RecyclerViewAdapterReviews(reviewsMain, context);
                    recyclerView.setAdapter(recyclerViewAdapterReviews);

                    LinearLayoutManager verticalManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(verticalManager);
                }


            });
        }
    }
}