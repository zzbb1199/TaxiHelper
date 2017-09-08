package com.example.taxihelper.mvp.ui.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.fragments.CommentFragment;
import com.example.taxihelper.mvp.ui.fragments.OrderStatusFragment;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.RxBus;

import butterknife.InjectView;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class WaitingDriverArriveActivity extends RxBusSubscriberBaseActivity {

    @InjectView(R.id.driver_name)
    TextView driverName;
    @InjectView(R.id.driver_phone_num)
    TextView driverPhoneNum;
    @InjectView(R.id.score)
    TextView score;
    @InjectView(R.id.car_type)
    TextView carType;
    @InjectView(R.id.car_num)
    TextView carNum;

  
    private OrderDetailInfo orderInfo;
    private Fragment orderStatusFragment;
    private Fragment commentFragment;

    private void initDatas() {
        OrderDetailInfo.DriverBean driverInfo = orderInfo.getDriver();
        driverName.setText(driverInfo.getDriverName());
        driverPhoneNum.setText(driverInfo.getVirtualPhone4Passenger());
        score.setText(driverInfo.getDriverScore());
        carType.setText(driverInfo.getVehicleModel().replaceAll(" ", ""));
        carNum.setText(driverInfo.getVehicleNo());
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        orderInfo = getIntent().getParcelableExtra(Constant.ORDER_DETAIL_INFO);
        if (orderInfo == null) {
            ToastUtil.shortToast("发生未知错误，正在退出...");
            return;
        }
        initDatas();
        orderStatusFragment = new OrderStatusFragment();
        commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ORDER_ID,orderInfo.getOrder().getId());
        commentFragment.setArguments(bundle);
        
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_frame_layout, orderStatusFragment);
        ft.commit();
    }

    private void changFragment(Fragment fromFragment, Fragment toFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (toFragment.isAdded()) {
            ft.hide(fromFragment).show(toFragment).commit();
        } else {
            ft.add(R.id.main_frame_layout, toFragment).hide(fromFragment).show(toFragment).commit();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_driver_arrive;
    }


    @Override
    public void initRxBus() {
        mSubscription = RxBus.getDefault().toObservable(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (s.equals(Constant.ORDER_FEE_SUBMITTED)) {
                            changFragment(orderStatusFragment, commentFragment);
                        }
                    }
                });
    }

  
}
