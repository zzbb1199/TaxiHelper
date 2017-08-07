package com.example.taxihelper.utils.others;

import android.widget.Toast;

import com.example.taxihelper.App;

/**
 * Created by ${chenyn} on 2017/2/16.
 */

public class ToastUtil {
    public static void shortToast(String desc) {
        Toast.makeText(App.getContext(), desc, Toast.LENGTH_SHORT).show();
    }
}
