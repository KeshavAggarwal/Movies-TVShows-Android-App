package com.app.techvalley.movies.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.techvalley.movies.fragments.CastMovieFragment;
import com.app.techvalley.movies.fragments.InfoAboutMovieFragment;
import com.app.techvalley.movies.fragments.ReviewsFragment;

import java.util.HashMap;


/**
 * Created by KeshavAggarwal on 25/01/17.
 */

public class FragmentPager extends FragmentPagerAdapter {
    HashMap<Integer, Fragment> map = new HashMap<>();


    public FragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = InfoAboutMovieFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }

            case 1:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = CastMovieFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }


            case 2:
                fragment = map.get(position);
                if (fragment == null) {
                    fragment = ReviewsFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                } else {
                    return fragment;
                }

        }
        return null;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CAST";
            case 2:
                return "REVIEWS";
        }
        return null;
    }

    public Fragment function(int position) {

        Fragment fragment = map.get(position);
        if (fragment == null) {
            return getItem(position);
        } else {
            return fragment;
        }
    }
}
