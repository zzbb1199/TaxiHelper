package com.example.taxihelper.mvp.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
    @InjectView(R.id.accept)
    Button accept;
    private String orderId;
    @Inject
    AcceptOrderPresenterImpl presenter;
    int count = 0;//总共9个流程
    public final String STATUS[] = new String[]{"正在为您分配", "车辆正在赶来", "车辆已到达，请上车", "服务开始", "服务结束", "提交费用中", "已完成支付", "本次乘车已结束"};

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
        accept.setClickable(count <= 8 ? true : false);
        count++;
        currentStatus.setText(STATUS[count]);

    }

    @Override
    public void onError() {
        accept.setClickable(count <= 8 ? true : false);
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
            View view = inflater.inflate(R.layout.cancel_reason_item, null);
            final TextView tv = view.findViewById(R.id.reason_item);
            tv.setText(reason.getValue());
            tv.setTag(reason.getId());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.cancelOrder(orderId, true, (Integer) tv.getTag());
                    dialog.dismiss();
                }
            });
            container.addView(view);
        }
        dialog = new AlertDialog.Builder(this)
                .setView(container)
                .create();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.accept, R.id.cancel_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.accept:
                accept.setClickable(false);
                presenter.changOrderStatus(orderId, Constant.SHANGWU_DRIVER, Constant.ORDER_STATUS[count]);
                break;
            case R.id.cancel_order:
                presenter.cancelOrderReason();
                break;
        }
    }
}
