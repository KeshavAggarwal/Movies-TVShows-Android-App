package com.example.acer.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.acer.movies.Network.AboutMovieResponse;

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
                 fragment =  map.get(position);
                if(fragment == null){
                    fragment = InfoAboutMovieFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                }else{
                    return fragment;
                }

            case 1:
                 fragment =  map.get(position);
                if(fragment == null){
                    fragment =  CastMoviefragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                }else{
                    return fragment;
                }



            case 2:
                fragment =  map.get(position);
                if(fragment == null){
                    fragment =  ReviewsFragment.newInstance();
                    map.put(position, fragment);
                    return fragment;
                }else{
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

        Fragment fragment =  map.get(position);
        if(fragment == null){
            return getItem(position);
        }else{
            return fragment;
        }
    }
}
