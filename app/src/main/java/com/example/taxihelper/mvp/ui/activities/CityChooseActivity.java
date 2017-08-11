package com.example.taxihelper.mvp.ui.activities;

import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/8/11.
 */

public class CityChooseActivity extends BaseActivity {
    @InjectView(R.id.current_city)
    TextView currentCityTv;
    @InjectView(R.id.citys)
    EasyRecyclerView citysRecycler;

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        //取得当前城市
        String currentCity = getIntent().getStringExtra(Constant.CURRENT_CITY);
        if (currentCity == null){
            return;
        }
        currentCityTv.append(currentCity);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_city_choose;
    }

  
}
