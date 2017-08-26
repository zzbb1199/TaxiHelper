package com.example.taxihelper.mvp.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.OrderDetailContract;
import com.example.taxihelper.mvp.entity.NoDriverAccept;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.presenter.OrderDetailPresenterImpl;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.RxBus;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class OrderDetailService extends Service implements OrderDetailContract.View {
    private String TAG;
    private String orderId;
    private final int TOTAL_TIME = 1000 * 60 * 5;//最多5分钟
    private final int INTERVAL = 1000 * 5;//3秒钟请求一次
    private TimeCount timeCount;

    OrderDetailPresenterImpl presenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        presenter = new OrderDetailPresenterImpl();
        timeCount = new TimeCount(TOTAL_TIME, INTERVAL);
        presenter.injectView(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TAG = getClass().getSimpleName();
        orderId = intent.getStringExtra(Constant.ORDER_ID);
        if (orderId != null){
            presenter.getOrderDetialInfo(orderId);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String msg) {
        //如果代码走到了这里，说明已经出错
        ToastUtil.shortToast(msg);
    }

    boolean isFirst = true;

    @Override
    public void showOrderDetial(OrderDetailInfo orderDetailInfo) {
        //判定是否已经派单
        Log.i(TAG, orderDetailInfo.toString());
        if (orderDetailInfo.getDriver() != null) {
            //判断条件需要更改*********
            //如果司机不为空，说明已经派单
            //信息传回
            RxBus.getDefault().post(orderDetailInfo);
            stopSelf();//结束掉
            return;
        }
        if (isFirst) {
            //否则，开始计时
            timeCount.start();
            isFirst = false;
        }

    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            presenter.getOrderDetialInfo(orderId);
        }

        @Override
        public void onFinish() {
            //5分钟已过，没人接单，说明一直没人接
            RxBus.getDefault().post(new NoDriverAccept());
        }
    }
}
