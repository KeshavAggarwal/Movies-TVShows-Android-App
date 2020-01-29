package com.app.techvalley.movies.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.techvalley.movies.fragments.CastTVShowFragment;
import com.app.techvalley.movies.fragments.InfoAboutTVShowFragment;

import java.util.HashMap;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class TVShowFragmentPager extends FragmentPagerAdapter {
    HashMap<Integer, Fragment> map = new HashMap<>();

    public TVShowFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                map.put(position, InfoAboutTVShowFragment.newInstance().newInstance());
                return map.get(position);
            case 1:
                map.put(position, CastTVShowFragment.newInstance());
                return map.get(position);
        }
        return null;

    }


    @Override
    public int getCount() {
        return 2;
    }

    public Fragment function(int position) {
        return map.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "ACTORS";
        }

        return null;
    }
}
