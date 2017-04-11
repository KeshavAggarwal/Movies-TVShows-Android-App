package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by KeshavAggarwal on 14/03/17.
 */

public class ViewHolderReviews extends RecyclerView.ViewHolder {
    CardView reviewsCardView;
    TextView reviewHeading;
    TextView reviewSubheading;
    TextView reviewTextView;
    TextView circleTextView;
    TextView readTheRestTextView;

    public ViewHolderReviews(View itemView) {
        super(itemView);

        reviewsCardView = (CardView) itemView.findViewById(R.id.reviewCardView);
        reviewTextView = (TextView) itemView.findViewById(R.id.reviewsTextView);
        reviewHeading = (TextView) itemView.findViewById(R.id.reviewHeading);
        circleTextView = (TextView) itemView.findViewById(R.id.circleTextView);
        reviewSubheading = (TextView) itemView.findViewById(R.id.reviewSubheading);
        readTheRestTextView = (TextView) itemView.findViewById(R.id.readtheRestTextView);
    }
}
