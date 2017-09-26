package com.example.acer.movies.adapters;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.acer.movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 19/01/17.
 */

public class BannerViewPagerAdapter extends PagerAdapter {

    Context mContext;
    ArrayList<String> mAllBannerImageFullLinks;
    ProgressBar progressBar;
    //LayoutInflaterCompat layoutInflator;

    public BannerViewPagerAdapter(Context context, ArrayList<String> allBannerImageFullLinks) {
        mContext = context;
        mAllBannerImageFullLinks = allBannerImageFullLinks;

    }

    @Override
    public int getCount() {
        return mAllBannerImageFullLinks.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_image_view_layout, container, false);
        ImageView bannerImage = (ImageView) v.findViewById(R.id.bannerImage);
        Picasso.with(mContext).load(mAllBannerImageFullLinks.get(position)).into(bannerImage);
        container.addView(v);
        return v;

    }
}

