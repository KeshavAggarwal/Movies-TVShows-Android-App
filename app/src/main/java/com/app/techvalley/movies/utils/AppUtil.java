package com.app.techvalley.movies.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Keshav Aggarwal on 01/02/20.
 */
public class AppUtil {

    public static boolean isEmptyOrNullString(String input) {
        if ((input != null) && (!input.trim().isEmpty())) {
            return false;
        } else {
            return true;
        }
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static float spToPx(Context context, float sp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float px = (sp * displayMetrics.scaledDensity);
        return px;
    }
}
