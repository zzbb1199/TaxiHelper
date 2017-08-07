package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.GainAccessToken;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/7.
 */

public interface GainAccessTokenContract {
    interface Model {
        Observable<GainAccessToken> gainAccessToken(String clientId,String clientSecret,String type,String code,String redirectUrl);
        Observable<GainAccessToken>  refreshAccessToken(String clientId,String clientSecret,String type,String refreshToken);
    }

    interface View extends BaseView {
        void gainAccessToken(GainAccessToken gainAccessToken);
    }

    interface Presenter {
        void gainAccessToken(String clientId,String clientSecret,String type,String code,String redirectUrl);
        void refreshAccessToken(String clientId,String clientSecret,String type,String refreshToken);
    }
}
