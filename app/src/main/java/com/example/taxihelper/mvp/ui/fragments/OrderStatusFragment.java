package com.example.taxihelper.mvp.ui.fragments;

import android.util.Log;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.CurrentOrderStatus;
import com.example.taxihelper.mvp.ui.fragments.base.RxBusBaseFragment;
import com.example.taxihelper.utils.system.RxBus;

import butterknife.InjectView;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public class OrderStatusFragment extends RxBusBaseFragment {
    @InjectView(R.id.order_status)
    TextView statusTv;


    @Override
    public void initRxBus() {
        mSubscription = RxBus.getDefault().toObservable(CurrentOrderStatus.class)
                .subscribe(new Action1<CurrentOrderStatus>() {
                    @Override
                    public void call(CurrentOrderStatus currentOrderStatus) {
                        Log.i(TAG,"状态更该");
                        String status = currentOrderStatus.getCurrentOrderStauts();
                        if (status.equals(Constant.ORDER_ARRIVING)) {
                            //车辆赶来中
                            statusTv.setText("司机正在赶来..");
                        } else if (status.equals(Constant.ORDER_ARRIVED)) {
                            statusTv.setText("车辆已到达,请尽快上车");
                        } else if (status.equals(Constant.ORDER_SERVICE_STARTED)) {
                            //开始
                            statusTv.setText("服务开始");
                        } else if (status.equals(Constant.ORDER_SERVICE_FINISHED)) {
                            Log.i(TAG, "服务结束,新界面即将生成");
                            RxBus.getDefault().post(new String(Constant.ORDER_FEE_SUBMITTED));
                        }
                    }
                });
    
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_status;
    }
}
