package com.example.taxihelper.mvp.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.GainAccessTokenContract;
import com.example.taxihelper.mvp.entity.GainAccessToken;
import com.example.taxihelper.mvp.presenter.GainAccessTokenPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.others.AccessTokenUtils;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.utils.system.SpUtil;
import com.example.taxihelper.utils.system.ToActivityUtil;

import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by Raven on 2017/8/7.
 */

public class LoginActivity extends RxBusSubscriberBaseActivity implements GainAccessTokenContract.View {


    @Inject
    GainAccessTokenPresenterImpl mPresenter;
    @InjectView(R.id.phone_number_input)
    EditText phoneNumberInput;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        mPresenter.injectView(this);
    }

    @Override
    public void initViews() {
        //首先读取是否过期
        long expiredTime = Long.parseLong(SpUtil.getString(this, Constant.EXPIRED_TIME, "-1"));
        if (expiredTime == -1) {
            //还未进行首次认证,或者授权已经过期
            return;
        }
        if (expiredTime <= System.currentTimeMillis()) {
            mPresenter.refreshAccessToken(Constant.SHENZHOU_CLIENT_ID, Constant.SHENZHOU_CLIENT_PASSWORD,
                    Constant.REFRESH_TYPE, SpUtil.getString(this, Constant.REFRESH_ACCESS_TOKEN, "-1"));
        }
        if (expiredTime > System.currentTimeMillis()) {
            //还在授权期限内
            ToActivityUtil.toNextActivityAndFinish(this, ShenZhouTaxiActivity.class);
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initRxBus() {
        mSubscription = RxBus.getDefault().toObservable(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mPresenter.gainAccessToken(Constant.SHENZHOU_CLIENT_ID,
                                Constant.SHENZHOU_CLIENT_PASSWORD, "authorization_code", s, Constant.SHENZHOUE_REDIRECT_URL);
                    }
                });
    }


    @OnClick({R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                String phoneNumber = phoneNumberInput.getText().toString();
                //采用正则表达式验证
                String regex = "1[358]\\d{9}";
                if(!Pattern.matches(regex,phoneNumber)){
                   ToastUtil.shortToast("输入格式不规范，请重新输入");
                   return;
                }
                Intent intent = new Intent(this,ShenZhouAuthActivity.class);
                intent.putExtra(Constant.PHONE_NUM,phoneNumber);
                startActivity(intent);
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
        Log.i(TAG, gainAccessToken.toString());
        //将信息进行固化
        AccessTokenUtils.putAccessToken(gainAccessToken.getAccess_token());
        AccessTokenUtils.putRefreshToken(gainAccessToken.getRefresh_token());
        //写入过期时间
        long nowMills = System.currentTimeMillis();
        long expiredMills = nowMills + gainAccessToken.getExpires_in() * 1000;
        SpUtil.putString(this, Constant.EXPIRED_TIME, String.valueOf(expiredMills));
        ToActivityUtil.toNextActivityAndFinish(this, ShenZhouTaxiActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
