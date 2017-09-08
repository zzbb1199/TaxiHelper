package com.example.taxihelper.mvp.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.OrderDetailContract;
import com.example.taxihelper.mvp.entity.CurrentOrderStatus;
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
        if (orderId != null) {
            presenter.getOrderDetialInfo(orderId);
            Log.i(TAG, "启动监听Service");
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
    boolean b1 = true;
    boolean b2 = true;
    boolean b3 = true;
    boolean b4 = true;
    boolean b5 = true;
    boolean b6 = true;
    boolean b7 = true;
    boolean b8 = true;

    @Override
    public void showOrderDetial(OrderDetailInfo orderDetailInfo) {
        //判定是否已经派单
        Log.i(TAG, "监听收到");
        String status = orderDetailInfo.getOrder().getStatus();
        if (status.equals(Constant.ORDER_DISPATCHED) && b1) {
            b1 = false;
            //如果已经有司机接单，那么就跳转页面
            RxBus.getDefault().post(orderDetailInfo);
        } else if (status.equals(Constant.ORDER_ARRIVING) && b2) {
            //车辆赶来中
            b2 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_ARRIVING));
        } else if (status.equals(Constant.ORDER_ARRIVED) && b3) {
            //车辆已经成功到达
            b3 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_ARRIVED));
        } else if (status.equals(Constant.ORDER_SERVICE_STARTED) && b4) {
            //开始
            b4 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_SERVICE_STARTED));
        } else if (status.equals(Constant.ORDER_SERVICE_FINISHED) && b5) {
            //结束
            b5 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_SERVICE_FINISHED));
        } else if (status.equals(Constant.ORDER_FEE_SUBMITTED) && b6) {
            //提交费用
            b6 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_FEE_SUBMITTED));
        } else if (status.equals(Constant.ORDER_PAID) && b7) {
            //支付
            b7 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORDER_PAID));
        } else if (status.equals(Constant.ORRDER_COMPLEDTED) && b8) {
            //完成整个流程
            b8 = false;
            RxBus.getDefault().post(new CurrentOrderStatus(Constant.ORRDER_COMPLEDTED));
            //一旦完成所有流程
            stopSelf();
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
