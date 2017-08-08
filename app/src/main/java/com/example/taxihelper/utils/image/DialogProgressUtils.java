package com.example.taxihelper.utils.image;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class DialogProgressUtils {

    private static AlertDialog dialog;

    public static void ShowDialogProgress(Context context) {
        dialog = new AlertDialog.Builder(context)
                .setTitle("提示:")
                .setMessage("正在加载请稍后...")
                .create();
    }

    public static void hideDialogProgress() {
        dialog.dismiss();
    }
}
