package com.example.taxihelper.mvp.ui.activities;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.CancelOrder;
import com.example.taxihelper.mvp.entity.CancelOrderReason;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.presenter.AcceptOrderPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.fragments.CommentFragment;
import com.example.taxihelper.mvp.ui.fragments.OrderStatusFragment;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.system.DensityUtil;
import com.example.taxihelper.utils.system.RxBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import rx.functions.Action1;

/**
 * Created by Raven on 2017/8/26.
 */

public class WaitingDriverArriveActivity extends RxBusSubscriberBaseActivity implements AcceptOrderContract.View  {

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
        mActivityComponent.inject(this);
        presenter.injectView(this);
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
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_taxi_cancle,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Inject
    AcceptOrderPresenterImpl presenter ;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.cancelOrderReason();
        return super.onOptionsItemSelected(item);
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

    @Override
    public void showOrderStatus(OrderStatus orderStatus) {
        
    }

    @Override
    public void onError() {

    }

    @Override
    public void cancelOrder(CancelOrder cancelOrder) {

    }

    AlertDialog dialog;
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
                    presenter.cancelOrder(orderInfo.getOrder().getId(), true, (Integer) tv.getTag());
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

  
}
