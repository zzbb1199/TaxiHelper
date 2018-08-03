package com.example.taxihelper.mvp.presenter;

import android.util.Log;

import com.example.taxihelper.mvp.contract.GainAccessTokenContract;
import com.example.taxihelper.mvp.entity.GainAccessToken;
import com.example.taxihelper.mvp.model.GainAccessTokenModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.utils.image.ToastUtil;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raven on 2017/8/7.
 */

public class GainAccessTokenPresenterImpl extends BasePresenterImpl<GainAccessTokenContract.View> implements GainAccessTokenContract.Presenter {
    
    @Inject
    GainAccessTokenModelImpl model; 
    
    @Inject
    public GainAccessTokenPresenterImpl() {
    }

    @Override
    public void gainAccessToken(String clientId, String clientSecret, String type, String code, String redirectUrl) {
        mView.showProgress();
        model.gainAccessToken(clientId,clientSecret,type,code,redirectUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GainAccessToken>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                        ToastUtil.shortToast(e.getMessage());
                    }

                    @Override
                    public void onNext(GainAccessToken gainAccessToken) {
                        Log.i(TAG,gainAccessToken.toString());
                        mView.hideProgress();
                        mView.showMsg("success");
                        mView.gainAccessToken(gainAccessToken);
                    }
                });
                
    }

    @Override
    public void refreshAccessToken(String clientId, String clientSecret, String type, String refreshToken) {
        mView.showProgress();
        model.refreshAccessToken(clientId,clientSecret,type,refreshToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GainAccessToken>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(GainAccessToken gainAccessToken) {
                        Log.i(TAG,gainAccessToken.toString());
                        mView.hideProgress();
                        mView.gainAccessToken(gainAccessToken);
                    }
                });
    }
}
