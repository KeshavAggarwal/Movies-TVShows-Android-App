package com.example.acer.movies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.acer.movies.fragments.MoviesFragment;
import com.example.acer.movies.fragments.TVShowsFragment;

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
        switch(position){
            case 0:
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;

            case 1:
                TVShowsFragment tvShowsFragment = new TVShowsFragment();
                return tvShowsFragment;
        }
        return null;
    }

}
