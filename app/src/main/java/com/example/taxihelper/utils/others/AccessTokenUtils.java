package com.example.taxihelper.utils.others;

import com.example.taxihelper.App;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.utils.system.SpUtil;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class AccessTokenUtils {
    
    public static String getAccessToken() {
        return SpUtil.getString(App.getContext(), Constant.ACCESS_TOKEN, null);
    }

    public static String getRegreshToken() {
        return SpUtil.getString(App.getContext(), Constant.REFRESH_ACCESS_TOKEN, null);
    }

    public static void putAccessToken(String accessToken) {
        SpUtil.putString(App.getContext(), Constant.ACCESS_TOKEN, accessToken);
    }

    public static void putRefreshToken(String refreshToken) {
        SpUtil.putString(App.getContext(), Constant.REFRESH_ACCESS_TOKEN, refreshToken);

    }
}
