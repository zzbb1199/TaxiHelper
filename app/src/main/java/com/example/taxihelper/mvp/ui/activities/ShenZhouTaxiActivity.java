package com.example.taxihelper.mvp.ui.activities;

import android.util.Log;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.contract.GetCityInfoContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.presenter.GetCityInfoPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.example.taxihelper.utils.image.DialogProgressUtils;
import com.example.taxihelper.utils.others.AccessTokenUtils;
import com.example.taxihelper.utils.others.ToastUtil;

import javax.inject.Inject;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class ShenZhouTaxiActivity extends BaseActivity implements GetCityInfoContract.View {

    /**
     * 测试用经纬度
     */
    //经度
    private final double slot = 106.33;
    //唯独
    private final double slat = 29.35;
    
    @Inject
    GetCityInfoPresenterImpl mPresenter;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        mPresenter.injectView(this);
    }

    @Override
    public void initViews() {
        //首先获取当前城市位置
        mPresenter.getCityInfo(AccessTokenUtils.getAccessToken(),slat,slot);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shenzhou_taxi;
    }
    
    @Override
    public void showProgress() {
        DialogProgressUtils.ShowDialogProgress(this);
    }

    @Override
    public void hideProgress() {
        DialogProgressUtils.hideDialogProgress();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.shortToast(msg);
    }

    @Override
    public void getCityInfo(CityInfo cityInfo) {
        //得到当前城市的数据后进行排布
        Log.i(TAG,cityInfo.toString());
    }
}
