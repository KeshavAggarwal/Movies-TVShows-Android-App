package com.app.techvalley.movies.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.techvalley.movies.fragments.MoviesFragment;
import com.app.techvalley.movies.fragments.TVShowsFragment;

/**
 * Created by KeshavAggarwal on 06/02/17.
 */

public class MainFragmentPager extends FragmentPagerAdapter {
    public MainFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "TV Shows";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();

            case 1:
                return new TVShowsFragment();
        }
        return null;
    }

}
