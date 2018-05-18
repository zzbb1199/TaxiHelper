package com.example.taxihelper.mvp.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.CancelOrder;
import com.example.taxihelper.mvp.entity.CancelOrderReason;
import com.example.taxihelper.mvp.entity.NoDriverAccept;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.presenter.AcceptOrderPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.service.OrderDetailService;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.DensityUtil;
import com.example.taxihelper.utils.system.RxBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class WaitingDriveAcceptActivity extends RxBusSubscriberBaseActivity implements AcceptOrderContract.View {

    @InjectView(R.id.current_status)
    TextView currentStatus;
//    @InjectView(R.id.accept)
//   Button accept;
    @InjectView(R.id.wait_time)
    TextView waitTime;
    int currentSeconds = 0;
    int currentMinute = 0;
    private String orderId;
    int totalWaitTime = 6;
    @Inject
    AcceptOrderPresenterImpl presenter;
    int count = 0;//总共9个流程
    public final String STATUS[] = new String[]{"正在为您分配", "系统已为您成功派单，正在跳转...", "车辆已到达，请上车", "服务开始", "服务结束", "提交费用中", "已完成支付", "本次乘车已结束"};

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        presenter.injectView(this);
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
        //计时开始
        TimeCount timeCount = new TimeCount(totalWaitTime*60*1000,1000);
        timeCount.start();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_driver_accept;
    }


    @Override
    public void initRxBus() {
        RxBus.getDefault().toObservable(OrderDetailInfo.class)
                .subscribe(new Action1<OrderDetailInfo>() {
                    @Override
                    public void call(OrderDetailInfo orderDetailInfo) {
                        //得到信息后，传给下一个Activity
                        //成功有人接单
                        Log.i(TAG, orderDetailInfo.toString());
                        Intent intent = new Intent(WaitingDriveAcceptActivity.this, WaitingDriverArriveActivity.class);
                        intent.putExtra(Constant.ORDER_DETAIL_INFO, orderDetailInfo);
                        startActivity(intent);
                        finish();
                    }
                });
        RxBus.getDefault().toObservable(NoDriverAccept.class)
                .subscribe(new Action1<NoDriverAccept>() {
                    @Override
                    public void call(NoDriverAccept noDriverAccept) {
                        //提示用户，然后自动取消订单
                        Log.i(TAG, "无人接单");
                    }
                });
    }


    @Override
    public void showOrderStatus(OrderStatus orderStatus) {
        Log.i(TAG, orderStatus.toString());
//        accept.setClickable(count <= 8 ? true : false);
        count++;
        currentStatus.setText(STATUS[count]);

    }

    @Override
    public void onError() {
//        accept.setClickable(count <= 8 ? true : false);
    }

    @Override
    public void cancelOrder(CancelOrder cancelOrder) {
        Log.i(TAG, cancelOrder.toString());
    }

    AlertDialog dialog = null;

    @Override
    public void cancelReson(List<CancelOrderReason> cancelOrderReason) {
        Log.i(TAG, cancelOrderReason.toString());
        LayoutInflater inflater = LayoutInflater.from(this);
        //得到reason后，
        LinearLayout container = (LinearLayout) inflater.inflate(R.layout.dialog_cancel_reason, null);
        for (CancelOrderReason reason : cancelOrderReason) {
            final TextView tv = (TextView) inflater.inflate(R.layout.cancel_reason_item, null);
            int dp8 = DensityUtil.dip2px(this, 8);
            tv.setPadding(dp8, dp8, dp8, dp8);
            tv.setText(reason.getValue());
            tv.setTag(reason.getId());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.cancelOrder(orderId, true, (Integer) tv.getTag());
                    dialog.dismiss();
                }
            });
            container.addView(tv);
        }
        dialog = new AlertDialog.Builder(this)
                .setView(container)
                .create();
        dialog.show();

    }
    private void addTime() {
        currentSeconds++;
        if(currentSeconds < 10){
            waitTime.setText(0+""+currentMinute+":"+"0"+currentSeconds);
        }else if(currentSeconds < 60){
            waitTime.setText(0+""+currentMinute+":"+currentSeconds);
        }else if(currentSeconds == 60){
            currentMinute++;
            currentSeconds = 0;
            waitTime.setText(0+""+currentMinute+":"+currentSeconds);    
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.cancel_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_order:
                presenter.cancelOrderReason();
                break;
        }
    }
    
    
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            addTime();
        }

        @Override
        public void onFinish() {
            ToastUtil.shortToast("等待时间已超"+totalWaitTime + "分钟" + "，建议取消订单");
        }
    }

   

}
