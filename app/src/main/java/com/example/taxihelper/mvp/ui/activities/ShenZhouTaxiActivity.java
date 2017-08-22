package com.example.taxihelper.mvp.ui.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
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
import com.example.taxihelper.mvp.contract.TaxiContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.LocationChoose;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.presenter.TaxiPresenterImpl;
import com.example.taxihelper.mvp.ui.adapters.TaxiResultInfoViewPager;
import com.example.taxihelper.mvp.ui.fragments.TaxiKindsFragment;
import com.example.taxihelper.utils.image.DialogProgressUtils;
import com.example.taxihelper.utils.others.ToastUtil;
import com.example.taxihelper.utils.system.ActivityStack;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.widget.CircleView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.amap.api.maps.model.BitmapDescriptorFactory.HUE_RED;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class ShenZhouTaxiActivity extends AppCompatActivity implements TaxiContract.View, AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {


    @InjectView(R.id.location_dot)
    CircleView locationDot;
    @InjectView(R.id.location_text)
    TextView locationText;
    @InjectView(R.id.go_to_text)
    TextView goToText;
    @InjectView(R.id.toolbar)
    Toolbar mToolBar;
    @InjectView(R.id.type_linear)
    LinearLayout typeLinear;
    @InjectView(R.id.map)
    MapView map;
    @InjectView(R.id.taxi_right_now)
    Button taxiRightNow;
    AMap aMap = null;
    @InjectView(R.id.distance)
    TextView distanceTv;
    @InjectView(R.id.time_cost)
    TextView timeCostTv;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.taxi_result_info)
    CardView taxiResultInfo;


    /**
     * 辅助变量
     */
    private ActivityComponent mActivityComponent;
    private String TAG = this.getClass().getSimpleName();
    private float nowZoom = 18f;
    private String nowCity;
    private String addressStr;
    boolean userChooseLocation = false;
    private boolean isChooseEnd = false;
    double slat, slot, elat, elot;
    private int choosedServiceId = 14;//默认为立即叫车
    private String estimateId;
    private String passengerName = "张兴锐";
    private String passengerMobile = "15086943358";
    private int carGroupId;
    TaxiResultInfoViewPager adapter;
    /**
     * presenter
     */
    @Inject
    TaxiPresenterImpl presenter;

    /**
     * 地图相关
     */
    MyLocationStyle myLocationStyle;
    GeocodeSearch geocodeSearch;
    MapView mMapView = null;
    private int cityId;

    public void initInjector() {
        mActivityComponent.inject(this);
        presenter.injectView(this);
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
                        if (locationChoose.type.equals(Constant.TYPE_START)) {
                            userChooseLocation = true;
                            locationText.setText(locationChoose.getLocatoin());
                            //解析经纬度
                            // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode  
                            GeocodeQuery query = new GeocodeQuery(locationChoose.getFormatLocation(), nowCity);
                            geocodeSearch.getFromLocationNameAsyn(query);
                        } else {
                            isChooseEnd = true;
                            goToText.setText(locationChoose.getLocatoin());
                            taxiRightNow.setVisibility(View.VISIBLE);
                            GeocodeQuery query = new GeocodeQuery(locationChoose.getFormatLocation(), nowCity);
                            geocodeSearch.getFromLocationNameAsyn(query);
                        }

                    }
                });
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void initViews() {
        //首先获取当前城市位置
        //        mPresenter.getCityInfo(AccessTokenUtils.getAccessToken(),slat,slot);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBar.setTitle("TaxiHelper");
        mToolBar.setTitleTextColor(Color.WHITE);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //定义定位方式
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //自定义定位图标
        ///////////////////////////////////
        myLocationStyle.strokeColor(getColor(R.color.touming));
        myLocationStyle.radiusFillColor(getColor(R.color.touming));
        myLocationStyle.strokeWidth(0);
        BitmapDescriptor bm = BitmapDescriptorFactory.defaultMarker(HUE_RED);
        myLocationStyle.myLocationIcon(bm);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setZoomGesturesEnabled(true);


        //设置默认定位按钮是否显示，非必需设置
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(this);
        //设置当前定位级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(nowZoom));

        //搜索功能初始化
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);


        adapter = new TaxiResultInfoViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    /**
     * 获取经纬度
     *
     * @param location
     */
    private boolean hasRequestType = false;//避免多次请求城市可用服务类

    @Override
    public void onMyLocationChange(Location location) {
        //逆地址解析
        double lat = location.getLatitude();
        double lot = location.getLongitude();

        slat = lat;
        slot = lot;

        LatLonPoint latLonPoint = new LatLonPoint(lat, lot);
        //添加一个marker
        LatLng latLng = new LatLng(lat, lot);
        myLatLng = latLng;
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.draggable(false);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.now_location_icon)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(false);//设置marker平贴地图效果
        aMap.addMarker(markerOption);

        //发起逆地址解析请求
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
        //如果不是用户自己选择城市，以当前定位的地方进行请求城市车辆的服务类型
        presenter.getCityInfo(lat, lot);
        presenter.getNearbyCarInfo(lat, lot);
    }


    //逆地址解析回调
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i == 1000) {
            RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
            nowCity = address.getCity();

            Log.i(TAG, address.getBuilding().toString());
            if (!userChooseLocation) {
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
        if (i == 1000) {
            LatLonPoint latLonPoint = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
            if (latLonPoint != null) {
                if (!isChooseEnd) {
                    slat = latLonPoint.getLatitude();
                    slot = latLonPoint.getLongitude();
                } else {
                    elat = latLonPoint.getLatitude();
                    elot = latLonPoint.getLongitude();
                }
            }

        }
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

    private int nowIndex = 0;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void getCityInfo(CityInfo cityInfo) {
        //得到当前城市的数据后进行排布
        Log.i(TAG, cityInfo.toString());
        //捕捉城市id
        cityId = cityInfo.getCityId();
        //得到城市的类型以后进行类型的设置
        CityInfo.ServicesBean servicesBean = cityInfo.getServices();
        addTypeViews(servicesBean.get_$14().getName(), 0);
        addTypeViews(servicesBean.get_$13().getName(), 1);
        addTypeViews(servicesBean.get_$11().getName(), 2);
        addTypeViews(servicesBean.get_$7().getName(), 3);
        addTypeViews(servicesBean.get_$8().getName(), 4);
        typeLinear.invalidate();
        //默认第一个为选择类型
        TextView tv = ((TextView) typeLinear.getChildAt(0));
        tv.setTextColor(getColor(R.color.colorAccent));
        tv.setTextSize(18);

    }

    @Override
    public void showCreateOrderResult(CreateOrder createOrder) {
        Log.i(TAG,createOrder.toString());
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void changToType(int fromIndex, int toIndex) {
        TextView fromTv = (TextView) typeLinear.getChildAt(fromIndex);
        fromTv.setTextSize(15);
        TextView toTv = (TextView) typeLinear.getChildAt(toIndex);
        toTv.setTextSize(18);
        fromTv.setTextColor(getColor(R.color.secondary_text));
        toTv.setTextColor(getColor(R.color.colorAccent));
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addTypeViews(String type, int index) {
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(lp);
        tv.setText(type);
        tv.setTextSize(15);
        tv.setTextColor(getResources().getColor(R.color.secondary_text, null));
        tv.setTag(index);
        typeLinear.addView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changToType(nowIndex, (Integer) view.getTag());
                nowIndex = (int) view.getTag();
            }
        });
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

    LatLng myLatLng;

    @OnClick({R.id.location_view, R.id.go_to_view, R.id.location, R.id.taxi_right_now, R.id.confirm_taxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_view:
                //跳转到定位选择
                Intent intent1 = new Intent(this, LocationChooseActivity.class);
                intent1.putExtra(Constant.CURRENT_CITY, nowCity);
                intent1.putExtra(Constant.CURRENT_LOCATION, addressStr);
                intent1.putExtra(Constant.TYPE, Constant.TYPE_START);
                intent1.putExtra(Constant.SERVICE_ID, choosedServiceId);
                startActivity(intent1);
                break;
            case R.id.go_to_view:
                //跳转到下车界面
                Intent intent2 = new Intent(this, LocationChooseActivity.class);
                intent2.putExtra(Constant.CURRENT_CITY, nowCity);
                intent2.putExtra(Constant.TYPE, Constant.TYPE_END);
                intent2.putExtra(Constant.SERVICE_ID, choosedServiceId);
                startActivity(intent2);
                break;
            case R.id.location:
                if (myLatLng != null) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, nowZoom));
                }
                break;
            case R.id.taxi_right_now:
                //一旦叫车，不可输入
                presenter.getTaxiPrice(slat, slot, elat, elot, choosedServiceId, cityId, null, null, null, null, null);
                break;
            case R.id.confirm_taxi:
                taxiResultInfo.setVisibility(View.INVISIBLE);
                //进行叫车，判断是什么车的类型
                int pos =  viewPager.getCurrentItem();
                int carGroupId = ((TaxiKindsFragment)adapter.getItem(pos)).getCargroupid();
                String startName = locationText.getText().toString().trim();
                String endName = goToText.getText().toString().trim();
                presenter.createOrder(choosedServiceId,carGroupId,passengerMobile,passengerName,slat,slot
                        ,startName,startName,endName,endName,elat,elot,estimateId);
                break;
        }
    }


    @Override
    public void showNearbyCarInfo(NearbyCarInfo nearbyCarInfo) {
        Log.i(TAG, nearbyCarInfo.toString());
        if (nearbyCarInfo.getNumber() == 0) {
            //如果附近没有车辆，提示用户
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("很遗憾，附近没有可乘车辆，请换个地方试试")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        } else {
            //根据各车的经纬度，在地图上显示
            for (NearbyCarInfo.CarListBean car : nearbyCarInfo.getCarList()) {
                LatLng latLng = new LatLng(Double.valueOf(car.getLat()), Double.valueOf(car.getLng()));
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.draggable(false);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.drawable.now_location_icon)));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(false);//设置marker平贴地图效果
                aMap.addMarker(markerOption);
            }
        }
    }

    DecimalFormat df = new DecimalFormat("#.0");

    @Override
    public void showPriceResult(TaxiPriceInfo taxiPriceInfo) {
        Log.i(TAG, taxiPriceInfo.toString());
        //叫车成功，消失
        taxiRightNow.setVisibility(View.INVISIBLE);
        //加入车辆
        taxiResultInfo.setVisibility(View.VISIBLE);
        estimateId = taxiPriceInfo.getEstimateid();
        distanceTv.setText("预估距离:"+df.format(taxiPriceInfo.getDistance() / 1000) + "km");
        timeCostTv.setText("预计耗时:"+taxiPriceInfo.getDuration() + "min");
        List<Fragment> fragments = new ArrayList<>();

        //初始化Fragment
        for (TaxiPriceInfo.Prices prices : taxiPriceInfo.getPrices()) {
            Fragment fragment = new TaxiKindsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CAR_TYPE, prices.getName());
            bundle.putFloat(Constant.CAR_PRICES, prices.getPrice());
            bundle.putInt(Constant.CAR_GROUP_ID,prices.getCargroupid());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();
    }
}
