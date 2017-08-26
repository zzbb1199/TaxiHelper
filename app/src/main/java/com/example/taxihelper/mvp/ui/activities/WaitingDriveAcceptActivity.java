package com.example.taxihelper.mvp.ui.activities;

import android.content.Intent;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.NoDriverAccept;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.service.OrderDetailService;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.RxBus;

import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class WaitingDriveAcceptActivity extends RxBusSubscriberBaseActivity {

    private String orderId;

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        orderId = getIntent().getStringExtra(Constant.ORDER_ID);
        if (orderId == null) {
            ToastUtil.shortToast("出现未知错误,请重试");
            finish();
            return;
        }
        //启动监听Service
        Intent intent = new Intent(this, OrderDetailService.class);
        intent.putExtra(Constant.ORDER_ID, orderId);
        startService(intent);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_driver_accept;
    }


    @Override
    public void initRxBus() {
        RxBus.getDefault().toObservable(OrderDetailService.class)
                .subscribe(new Action1<OrderDetailService>() {
                    @Override
                    public void call(OrderDetailService orderDetailService) {
                        //得到信息后，传给下一个Activity
                    }
                });
        RxBus.getDefault().toObservable(NoDriverAccept.class)
                .subscribe(new Action1<NoDriverAccept>() {
                    @Override
                    public void call(NoDriverAccept noDriverAccept) {
                        //提示用户，然后自动取消订单
                    }
                });
    }
}
