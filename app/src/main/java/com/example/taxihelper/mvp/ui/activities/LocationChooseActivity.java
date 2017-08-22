package com.example.taxihelper.mvp.ui.activities;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.CityChoose;
import com.example.taxihelper.mvp.entity.LocationChoose;
import com.example.taxihelper.mvp.ui.activities.base.RxBusSubscriberBaseActivity;
import com.example.taxihelper.mvp.ui.adapters.LocationChooseAdapter;
import com.example.taxihelper.utils.system.RxBus;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/11.
 */

public class LocationChooseActivity extends RxBusSubscriberBaseActivity implements Inputtips.InputtipsListener, RecyclerArrayAdapter.OnItemClickListener {
    @InjectView(R.id.city_choose_tv)
    TextView cityChooseTv;
    @InjectView(R.id.location_input)
    EditText locationInput;
    @InjectView(R.id.search_result_list)
    EasyRecyclerView listView;
    private int currentCityId;
    private boolean hasSearch = false;
    String type;
    Integer serviceId;
    private String currentCity;
    private String currentLocation;
    private LocationChooseAdapter adapter;

    /**
     * 关键字搜索相关
     */
    InputtipsQuery query;
    Inputtips inputTips;

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        currentCity = getIntent().getStringExtra(Constant.CURRENT_CITY);
        currentLocation = getIntent().getStringExtra(Constant.CURRENT_LOCATION);
        type = getIntent().getStringExtra(Constant.TYPE);
        serviceId = getIntent().getIntExtra(Constant.SERVICE_ID,-1);
        if (currentCity != null) {
            cityChooseTv.setText(currentCity);
        } else {
            cityChooseTv.setText("城市");
        }
        locationInput.addTextChangedListener(tw);
        
        adapter = new LocationChooseAdapter(this);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        listView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        //初始化搜索
        if (type == Constant.TYPE_START){
            getSupportActionBar().setTitle("选择上车地点");
            query = new InputtipsQuery(currentLocation, currentCity);
            query.setCityLimit(true);//限制在当前城市
        }else {
            getSupportActionBar().setTitle("选择下车地点");
            query = new InputtipsQuery(currentCity,currentCity);
        }
        
        inputTips = new Inputtips(this, query);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
        locationInput.setSingleLine();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_location_choose;
    }

    @OnClick(R.id.city_choose_tv)
    public void onViewClicked() {
        Intent intent = new Intent(this, CityChooseActivity.class);
        intent.putExtra(Constant.CURRENT_CITY, currentCity);
        intent.putExtra(Constant.TYPE,type);
        intent.putExtra(Constant.SERVICE_ID,serviceId);
        startActivity(intent);
        //用户更改城市，为了重新搜索
        hasSearch = false;
    }

    @Override
    public void initRxBus() {
        RxBus.getDefault().toObservable(CityChoose.class)
                .subscribe(new Action1<CityChoose>() {
                    @Override
                    public void call(CityChoose cityChoose) {
                        currentCityId = cityChoose.getCityId();
                        currentCity = cityChoose.getCity();
                        cityChooseTv.setText(currentCity);
                        //用户改变了城市选择，这里重新请求
                        if (!hasSearch){
                            query = new InputtipsQuery(currentCity, currentCity);
                            inputTips.setQuery(query);
                            inputTips.requestInputtipsAsyn();
                            hasSearch = true;
                        }
                    }
                });

    }

    private TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            query = new InputtipsQuery(charSequence.toString(), currentCity);
            inputTips.setQuery(query);
            inputTips.requestInputtipsAsyn();
            
            if (charSequence.toString().equals("")){
                query = new InputtipsQuery(currentLocation,currentCity);
                inputTips.setQuery(query);
                inputTips.requestInputtipsAsyn();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        Log.i(TAG, list.toString());
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        String location = adapter.getItem(position).getName();
        String formatLocation = adapter.getItem(position).getDistrict()+adapter.getItem(position).getName();
        RxBus.getDefault().post(new LocationChoose(location,type,formatLocation,currentCityId));
        finish();
    }
}
