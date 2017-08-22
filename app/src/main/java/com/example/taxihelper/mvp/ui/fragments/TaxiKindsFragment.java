package com.example.taxihelper.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.ui.fragments.base.BaseFragment;

import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/8/22.
 */

public class TaxiKindsFragment extends BaseFragment {
    
    @InjectView(R.id.car_type)
    TextView carType;
    @InjectView(R.id.money_cost)
    TextView moneyCost;
    /**
     * 数据
     */
    private String type;
    private float price;
    private int cargroupid;

    public int getCargroupid() {
        return cargroupid;
    }

    public void setCargroupid(int cargroupid) {
        this.cargroupid = cargroupid;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(Constant.CAR_TYPE);
            price = getArguments().getFloat(Constant.CAR_PRICES);
        }
    }


    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        carType.setText(type);
        moneyCost.setText(price+"元");
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_taxi_kinds;
    }

    
}
