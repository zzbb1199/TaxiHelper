package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.GainAccessTokenContract;
import com.example.taxihelper.mvp.entity.GainAccessToken;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Raven on 2017/8/7.
 */

public class GainAccessTokenModelImpl extends BaseModelImpl implements GainAccessTokenContract.Model {
    @Inject
    public GainAccessTokenModelImpl() {
    }

    @Override
    public Observable<GainAccessToken> gainAccessToken(String clientId, String clientSecret, String type, String code, String redirectUrl) {
        return getAuthApi().gainAccessToken(clientId,clientSecret,type,code,redirectUrl);
    }

    @Override
    public Observable<GainAccessToken> refreshAccessToken(String clientId, String clientSecret, String type, String refreshToken) {
        return getAuthApi().refreshAccessToken(clientId,clientSecret,type,refreshToken);
    }


}
