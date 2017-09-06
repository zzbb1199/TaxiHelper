package com.example.taxihelper.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.NoDriverAccept;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.presenter.AcceptOrderPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.service.OrderDetailService;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.RxBus;

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
    public final String STATUS[] = new String[]{"正在为您分配","车辆正在赶来","车辆已到达，请上车","服务开始","服务结束","提交费用中","已完成支付","本次乘车已结束"};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick(R.id.accept)
    public void onViewClicked() {
        accept.setClickable(false);
        presenter.changOrderStatus(orderId, Constant.SHANGWU_DRIVER, Constant.ORDER_STATUS[count]);

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
}
