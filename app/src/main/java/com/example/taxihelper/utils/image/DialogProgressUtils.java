package com.example.taxihelper.utils.image;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class DialogProgressUtils {

    private static ProgressDialog dialog;

    public static void ShowDialogProgress(Context context) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("加载中...");
        }
        dialog.show();
    }

    public static void ShowDialogProgressWithMsg(Context context, String msg) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
        }
        dialog.setMessage(msg);
        dialog.show();
    }


    public static void hideDialogProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static void clear() {
        dialog = null;
    }
}
