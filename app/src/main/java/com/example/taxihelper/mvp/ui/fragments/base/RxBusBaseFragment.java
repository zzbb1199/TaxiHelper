package com.example.taxihelper.mvp.ui.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Subscription;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public abstract class RxBusBaseFragment extends BaseFragment {
    public abstract void initRxBus();
    protected Subscription mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRxBus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
