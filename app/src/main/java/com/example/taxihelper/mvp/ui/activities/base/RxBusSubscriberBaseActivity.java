package com.example.taxihelper.mvp.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.utils.image.DialogProgressUtils;
import com.example.taxihelper.utils.image.ToastUtil;

import rx.Subscription;

/**
 * Created by 猿人 on 2017/5/24.
 */

public abstract class RxBusSubscriberBaseActivity extends BaseActivity implements BaseView{

    /**
     * 进行RxBus的订阅
     */
    public abstract void initRxBus();
    protected Subscription mSubscription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRxBus();
        
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
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
}
