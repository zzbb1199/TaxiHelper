package com.example.taxihelper.mvp.ui.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.taxihelper.App;
import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.dagger.component.ActivityComponent;
import com.example.taxihelper.dagger.component.DaggerActivityComponent;
import com.example.taxihelper.dagger.module.ActivityModule;
import com.example.taxihelper.mvp.contract.GetCityInfoContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.LocationChoose;
import com.example.taxihelper.mvp.presenter.GetCityInfoPresenterImpl;
import com.example.taxihelper.utils.image.DialogProgressUtils;
import com.example.taxihelper.utils.others.ToastUtil;
import com.example.taxihelper.utils.system.ActivityStack;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.widget.CircleView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class ShenZhouTaxiActivity extends AppCompatActivity implements GetCityInfoContract.View, AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    /**
     * 测试用经纬度
     */
    //经度
    private final double slot = 106.33;
    //唯独
    private final double slat = 29.35;
    @InjectView(R.id.location_dot)
    CircleView locationDot;
    @InjectView(R.id.location_text)
    TextView locationText;
    @InjectView(R.id.go_to_text)
    TextView goToText;
    private ActivityComponent mActivityComponent;
    private String TAG = this.getClass().getSimpleName();

    @Inject
    GetCityInfoPresenterImpl mPresenter;

    /**
     * 地图相关
     */
    MyLocationStyle myLocationStyle;
    GeocodeSearch geocodeSearch;
    MapView mMapView = null;

    public void initInjector() {
        mActivityComponent.inject(this);
        mPresenter.injectView(this);
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getmApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.inject(this);
        ActivityStack.getScreenManager().pushActivity(this);

        initActivityComponent();
        //初始化组件 注入器
        initInjector();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        initViews();
        initRxBus();
    }

    private void initRxBus() {
        RxBus.getDefault().toObservable(LocationChoose.class)
                .subscribe(new Action1<LocationChoose>() {
                    @Override
                    public void call(LocationChoose locationChoose) {
                        userChooseLocation = true;
                        locationText.setText(locationChoose.getLocatoin());
                    }
                });
    }


    public void initViews() {
        //首先获取当前城市位置
        //        mPresenter.getCityInfo(AccessTokenUtils.getAccessToken(),slat,slot);

        //初始化地图控制器对象
        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //定义定位方式
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //自定义定位图标
        ///////////////////////////////////


        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(this);

        //搜索功能初始化
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);

    }

    /**
     * 获取经纬度
     *
     * @param location
     */
    @Override
    public void onMyLocationChange(Location location) {
        //逆地址解析
        double lat = location.getLatitude();
        double lot = location.getLongitude();
        LatLonPoint latLonPoint = new LatLonPoint(lat, lot);
        //发起逆地址解析请求
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
    }

    private String nowCity;
    private String addressStr;
    boolean userChooseLocation = false;
    //逆地址解析回调
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i == 1000) {
            RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
            nowCity = address.getCity();
            Log.i(TAG, address.getBuilding().toString());
            if (!userChooseLocation){
                //如果不是用户自己选择了地点，那么就自动定位
                addressStr = address.getFormatAddress();
                locationText.setText(addressStr);
                //定位成功，显示为绿色
                locationDot.setColor(getColor(R.color.green_500));
            }
        }
    }
    
    //返回了搜索结果
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
    }


    /*********************************************************************************************/

    public int getLayout() {
        return R.layout.activity_shenzhou_taxi;
    }

    @Override
    public void showProgress() {
        DialogProgressUtils.ShowDialogProgress(this);
    }

    @Override
    public void hideProgress() {
        DialogProgressUtils.hideDialogProgress();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.shortToast(msg);
    }

    @Override
    public void getCityInfo(CityInfo cityInfo) {
        //得到当前城市的数据后进行排布
        Log.i(TAG, cityInfo.toString());
    }

    /*********************************************************************************************/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @OnClick({R.id.location_view, R.id.go_to_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_view:
                //跳转到定位选择
                Intent intent = new Intent(this,LocationChooseActivity.class);
                intent.putExtra(Constant.CURRENT_CITY,nowCity);
                intent.putExtra(Constant.CURRENT_LOCATION,addressStr);
                startActivity(intent);
                break;
            case R.id.go_to_view:
                break;
        }
    }
}
