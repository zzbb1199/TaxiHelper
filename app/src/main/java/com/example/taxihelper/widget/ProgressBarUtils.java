package com.example.taxihelper.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by Raven on 2017/8/22.
 */

public class ProgressBarUtils {
    private static ProgressBar progressBar;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showProgressBar(Context context, ViewGroup viewGroup){
        if (progressBar == null){
            progressBar = new ProgressBar(context);
            progressBar.setForegroundGravity(Gravity.CENTER);
        }
        progressBar.setVisibility(View.VISIBLE);
        viewGroup.addView(progressBar);
    }
    public static void hideProgressBar(Context context){
        progressBar.setVisibility(View.INVISIBLE);
    }
}
