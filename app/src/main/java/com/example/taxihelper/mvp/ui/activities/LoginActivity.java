package com.example.taxihelper.mvp.ui.activities;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.GainAccessTokenContract;
import com.example.taxihelper.mvp.entity.GainAccessToken;
import com.example.taxihelper.mvp.presenter.GainAccessTokenPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.utils.others.AccessTokenUtils;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.utils.system.SpUtil;
import com.example.taxihelper.utils.system.ToActivityUtil;

import javax.inject.Inject;

import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/7.
 */

public class LoginActivity extends RxBusSubscriberBaseActivity implements GainAccessTokenContract.View {
    
    
    @Inject
    GainAccessTokenPresenterImpl mPresenter;
    
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        mPresenter.injectView(this);
    }

    @Override
    public void initViews() {
        //首先读取是否过期
        long expiredTime = Long.parseLong(SpUtil.getString(this, Constant.EXPIRED_TIME,"-1"));
        if (expiredTime == -1) {
            //还未进行首次认证,或者授权已经过期
            ToActivityUtil.toNextActivity(this,ShenZhouAuthActivity.class);
            return;
        }
        if (expiredTime <= System.currentTimeMillis()){
            mPresenter.refreshAccessToken(Constant.SHENZHOU_CLIENT_ID,Constant.SHENZHOU_CLIENT_PASSWORD,
                    Constant.REFRESH_TYPE,SpUtil.getString(this,Constant.REFRESH_ACCESS_TOKEN,"-1"));
        }
        if (expiredTime > System.currentTimeMillis()){
            //还在授权期限内
            ToActivityUtil.toNextActivityAndFinish(this,ShenZhouTaxiActivity.class);
            return;
        }
        
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initRxBus() {
        RxBus.getDefault().toObservable(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mPresenter.gainAccessToken(Constant.SHENZHOU_CLIENT_ID,
                                Constant.SHENZHOU_CLIENT_PASSWORD,"authorization_code",s,Constant.SHENZHOUE_REDIRECT_URL);
                    }
                });
    }
    

    @OnClick({R.id.shenzhou_auth, R.id.didi_auth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shenzhou_auth:
                ToActivityUtil.toNextActivity(this, ShenZhouAuthActivity.class);
                break;
            case R.id.didi_auth:
                break;
        }
    }

    AlertDialog dialog;
    @Override
    public void showProgress() {
        dialog = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("提示:")
                .setMessage("正在加载请稍后...")
                .setCancelable(false).create();
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.shortToast(msg);
    }

    @Override
    public void gainAccessToken(GainAccessToken gainAccessToken) {
        Log.i(TAG,gainAccessToken.toString());
        //将信息进行固化
        AccessTokenUtils.putAccessToken(gainAccessToken.getAccess_token());
        AccessTokenUtils.putRefreshToken(gainAccessToken.getRefresh_token());
        //写入过期时间
        long nowMills = System.currentTimeMillis();
        long expiredMills = nowMills+gainAccessToken.getExpires_in()*1000;
        SpUtil.putString(this,Constant.EXPIRED_TIME, String.valueOf(expiredMills));
        ToActivityUtil.toNextActivityAndFinish(this,ShenZhouTaxiActivity.class);
    }
}
