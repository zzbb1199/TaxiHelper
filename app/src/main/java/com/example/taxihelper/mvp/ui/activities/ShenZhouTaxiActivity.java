package com.example.taxihelper.mvp.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
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
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.taxihelper.App;
import com.example.taxihelper.BuildConfig;
import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.dagger.component.ActivityComponent;
import com.example.taxihelper.dagger.component.DaggerActivityComponent;
import com.example.taxihelper.dagger.module.ActivityModule;
import com.example.taxihelper.mvp.contract.OrderDetailContract;
import com.example.taxihelper.mvp.contract.TaxiContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.GoingOrder;
import com.example.taxihelper.mvp.entity.LocationChoose;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.entity.UserInfo;
import com.example.taxihelper.mvp.presenter.OrderDetailPresenterImpl;
import com.example.taxihelper.mvp.presenter.TaxiPresenterImpl;
import com.example.taxihelper.mvp.ui.adapters.TaxiResultInfoViewPager;
import com.example.taxihelper.mvp.ui.fragments.TaxiKindsFragment;
import com.example.taxihelper.mvp.ui.service.OrderDetailService;
import com.example.taxihelper.utils.image.ToastUtil;
import com.example.taxihelper.utils.others.overlay.DrivingRouteOverlay;
import com.example.taxihelper.utils.system.ActivityStack;
import com.example.taxihelper.utils.system.DensityUtil;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.utils.system.ToActivityUtil;
import com.example.taxihelper.widget.CircleView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.functions.Action1;

import static com.example.taxihelper.R.id.location;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

@RuntimePermissions
public class ShenZhouTaxiActivity extends AppCompatActivity implements TaxiContract.View, GeocodeSearch.OnGeocodeSearchListener, AMap.OnCameraChangeListener, RouteSearch.OnRouteSearchListener, AMap.OnMapLoadedListener, NavigationView.OnNavigationItemSelectedListener, OrderDetailContract.View {


    @InjectView(R.id.location_dot)
    CircleView locationDot;
    @InjectView(R.id.location_text)
    TextView locationText;
    @InjectView(R.id.go_to_text)
    TextView goToText;
    @InjectView(R.id.toolbar)
    Toolbar mToolBar;
    @InjectView(R.id.map)
    MapView map;
    AMap aMap = null;
    @InjectView(R.id.distance)
    TextView distanceTv;
    @InjectView(R.id.time_cost)
    TextView timeCostTv;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.taxi_result_info)
    CardView taxiResultInfo;
    @InjectView(R.id.card_view)
    CardView cardView;
    @InjectView(R.id.location_view)
    LinearLayout locationView;
    @InjectView(R.id.go_to_view)
    LinearLayout goToView;
    @InjectView(R.id.confirm_taxi)
    Button confirmTaxi;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.center_image)
    ImageView centerImage;
    @InjectView(R.id.type_choose)
    ImageView typeChoose;
    @InjectView(R.id.main_container)
    LinearLayout mainContainer;
    private LinearLayout typeLinear;
    private PopupWindow pw;


    /**
     * 辅助变量
     */
    private ActivityComponent mActivityComponent;
    private String TAG = this.getClass().getSimpleName();
    private float nowZoom = 14f;
    private String nowCity;
    private String addressStr;
    boolean userChooseLocation = false;
    private boolean isChooseEnd = false;
    private boolean hasCallTaxi = false;
    private boolean isFirstMapLoad = false;
    double slat, slot, elat, elot;
    private int choosedServiceId = 14;//默认为立即叫车
    private String estimateId;
    private String passengerName = "张兴锐";
    private String passengerMobile = "15086943358";

    private int startCityId;
    private int screenCenterX;
    private int screenCenterY;
    private LatLng myLatLng;//自动定位的位置
    private LatLng centerLocation;//中心定位位置
    private RouteSearch routeSearch;
    TaxiResultInfoViewPager adapter;
    /**
     * 测试使用的数据
     */
    //    private double testLocationLat = 39.166798;
    //    private double testLocationLot = 117.397561;
    public static final int GONGWU_TYPE_GROUP = 2;
    public static final int SHANGWU_TYPE_GROUP = 0;
    public static final int HAOHUA_TYPE_GROUP = 7;
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
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.i(TAG, aMapLocation.toString());
            Log.i(TAG, "首次定位");
            //逆地址解析
            double lat = aMapLocation.getLatitude();
            double lot = aMapLocation.getLongitude();
            slat = lat;
            slot = lot;
            presenter.getCityInfo(lat, lot);
            presenter.getNearbyCarInfo(lat, lot);

            centerLocation = new LatLng(lat, lot);
            myLatLng = centerLocation;
            if (myLatLng != null) {
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, nowZoom));//触发地图层移动，致使定位后续操作
            }


        }
    };


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
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        getLocationPermission();

    }


    /*初始化 搜索路线所需的类*/
    private void initRouteSearch() {
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
    }

    private void initRxBus() {
        RxBus.getDefault().toObservable(LocationChoose.class)
                .subscribe(new Action1<LocationChoose>() {
                    @Override
                    public void call(LocationChoose locationChoose) {
                        if (locationChoose.type.equals(Constant.TYPE_START)) {
                            userChooseLocation = true;
                            startCityId = locationChoose.getCityId();
                            locationText.setText(locationChoose.getLocatoin());
                            //解析经纬度
                            // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode  
                            GeocodeQuery query = new GeocodeQuery(locationChoose.getFormatLocation(), nowCity);
                            geocodeSearch.getFromLocationNameAsyn(query);
                        } else {
                            isChooseEnd = true;
                            goToText.setText(locationChoose.getLocatoin());
                            GeocodeQuery query = new GeocodeQuery(locationChoose.getFormatLocation(), nowCity);
                            geocodeSearch.getFromLocationNameAsyn(query);
                        }

                    }
                });
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void initViews() {
        /**
         * view相关
         */
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //        mToolBar.setTitle("TaxiHelper");
        //        mToolBar.setTitleTextColor(Color.WHITE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * 地图显示
         */
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //定义定位方式
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        ///////////////////////////////////
        myLocationStyle.strokeColor(getColor(R.color.touming));
        myLocationStyle.showMyLocation(false);
        myLocationStyle.radiusFillColor(getColor(R.color.touming));
        myLocationStyle.strokeWidth(0);

        //设置默认定位按钮是否显示，非必需设置
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(false);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setZoomGesturesEnabled(true);
        aMap.getUiSettings().setRotateGesturesEnabled(false);//禁止地图旋转手势
        aMap.getUiSettings().setTiltGesturesEnabled(false);//禁止倾斜手势
        aMap.setOnMapLoadedListener(this);
        //设置当前定位级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(nowZoom));
        aMap.setOnCameraChangeListener(this);// 对amap添加移动地图事件监听器

        /**
         * 定图定位
         */
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();


        /**
         * 搜索
         */
        //搜索功能初始化
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);


        /**
         * view相关
         */
        adapter = new TaxiResultInfoViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //初始化poupwindow
        CardView view = (CardView) LayoutInflater.from(this).inflate(R.layout.poup_window_more_taxi_type, null);
        typeLinear = (LinearLayout) view.getChildAt(0);
        pw = new PopupWindow(view, 320, 95 * 5);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);

        initDatas();
        //价差是否存在已有订单
        presenter.checkGoingOrder();

    }

    private void initDatas() {
        presenter.getUserInfo();
    }


    /**
     * 坐标转文字
     *
     * @param regeocodeResult
     * @param i
     */
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

    /**
     * 文字转坐标
     *
     * @param geocodeResult
     * @param i
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == 1000) {
            LatLonPoint latLonPoint = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
            LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
            if (latLonPoint != null) {
                if (!isChooseEnd) {
                    slat = latLonPoint.getLatitude();
                    slot = latLonPoint.getLongitude();
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, nowZoom));
                } else {
                    elat = latLonPoint.getLatitude();
                    elot = latLonPoint.getLongitude();

                    //适度缩放，连接路线
                    //第一步，清理扫描点
                    centerImage.setVisibility(View.INVISIBLE);
                    aMap.clear();
                    //第二部，放置marker
                    addMarker(slat, slot, Constant.TYPE_START);
                    addMarker(elat, elot, Constant.TYPE_END);
                    //第三部，计算路线
                    calculateDriveRoute();
                    presenter.getTaxiPrice(slat, slot, elat, elot, choosedServiceId, startCityId, null, null, null, null, null);
                    hasCallTaxi = true;


                }
            }

        }
    }

    private void addMarker(double lat, double lot, String type) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(lat, lot));
        markerOption.draggable(false);//设置Marker可拖动
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(false);//设置marker平贴地图效果
        Bitmap bitMap;
        if (type == Constant.TYPE_START) {
            bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.go_up_location);
        } else if (type == Constant.TYPE_END) {
            bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.go_down_location);
        } else {
            bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.car_map_icon);
        }
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = DensityUtil.dip2px(this, 30);
        int newHeight = newWidth;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix,
                true);
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(bitMap));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        aMap.addMarker(markerOption);
    }

    private void calculateDriveRoute() {
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(slat, slot),
                new LatLonPoint(elat, elot));
        // fromAndTo包含路径规划的起点和终点，drivingMode表示驾车模式
        // 第三个参数表示途经点（最多支持16个），第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
        RouteSearch.DriveRouteQuery driveRouteQuery = new RouteSearch.DriveRouteQuery(
                fromAndTo, RouteSearch.DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION, null, null, "");
        routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
    }


    /*********************************************************************************************/

    public int getLayout() {
        return R.layout.activity_shenzhou_taxi;
    }

    @Override
    public void showProgress() {
//        DialogProgressUtils.ShowDialogProgress(this);
    }

    @Override
    public void hideProgress() {
//        DialogProgressUtils.hideDialogProgress();
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
        //捕捉城市id
        startCityId = cityInfo.getCityId();
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
        tv.setTextColor(getColor(R.color.primary_text));
    }

    @Override
    public void showCreateOrderResult(CreateOrder createOrder) {
        Log.i(TAG, createOrder.toString());
        //将创建的订单，存入到数据库

        //将得到orderId，传入给下一个Activity
        Intent intent = new Intent(this, WaitingDriveAcceptActivity.class);
        intent.putExtra(Constant.ORDER_ID, createOrder.getOrderId());
        adapter = null;
        startActivity(intent);
        finish();
    }

    @Override
    public void showUserInfo(UserInfo userInfo) {
        App.getDaoSession().getUserInfoDao().insert(userInfo);
    }


    @Override
    public void showGoingOrderResult(List<GoingOrder> goingOrder) {
        //        if (goingOrder.getOrderStatus().equals(Constant.ORDER_DISPATCHED)){
        //            //如果有订单存在
        //        }
        if (goingOrder != null && goingOrder.size() != 0) {
            Log.i("正在进行的订单", goingOrder.toString());
//            DialogProgressUtils.ShowDialogProgressWithMsg(this, "发现您有正在进行中的订单，正在为您跳转...");
            //如果订单存在，请求具体订单
            OrderDetailPresenterImpl presenter = new OrderDetailPresenterImpl();
            presenter.injectView(this);
            presenter.getOrderDetialInfo(goingOrder.get(0).getOrderId());
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void changToType(int fromIndex, int toIndex) {
        TextView fromTv = (TextView) typeLinear.getChildAt(fromIndex);
        TextView toTv = (TextView) typeLinear.getChildAt(toIndex);
        fromTv.setTextColor(getColor(R.color.secondary_text));
        toTv.setTextColor(getColor(R.color.primary_text));
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addTypeViews(String type, int index) {
        final TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.poup_window_item, null);
        tv.setText(type);
        tv.setTag(index);
        if (index == 4) {
            tv.setBackground(null);
        }
        typeLinear.addView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changToType(nowIndex, (Integer) view.getTag());
                nowIndex = (int) view.getTag();
                pw.dismiss();
            }
        });
    }


    @OnClick({R.id.location_view, R.id.go_to_view, location, R.id.confirm_taxi, R.id.type_choose})
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
            case location:
                if (myLatLng != null) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, nowZoom));
                }
                break;
            case R.id.confirm_taxi:
                taxiResultInfo.setVisibility(View.INVISIBLE);
                //进行叫车，判断是什么车的类型
                int pos = viewPager.getCurrentItem();
                int carGroupId = ((TaxiKindsFragment) adapter.getItem(pos)).getCargroupid();
                String startName = locationText.getText().toString().trim();
                String endName = goToText.getText().toString().trim();
                presenter.createOrder(choosedServiceId, carGroupId, passengerMobile, passengerName, slat, slot
                        , startName, startName, endName, endName, elat, elot, estimateId);
                break;
            case R.id.type_choose:
                pw.update();
                pw.showAsDropDown(typeChoose);
                break;
        }
    }

    private void setInputClickAble(boolean clickAble) {
        cardView.setClickable(clickAble);
        locationView.setClickable(clickAble);
        goToView.setClickable(clickAble);
    }

    /**
     * 附近车辆
     *
     * @param nearbyCarInfo
     */
    AlertDialog dialog;
    @Override
    public void showNearbyCarInfo(NearbyCarInfo nearbyCarInfo) {
        Log.i(TAG, nearbyCarInfo.toString());
        if (nearbyCarInfo.getNumber() == 0) {
            //如果附近没有车辆，提示用户
            dialog =  new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("很遗憾，附近没有可乘车辆，请换个地方试试")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
            dialog.show();
        } else {
            //根据各车的经纬度，在地图上显示
            for (NearbyCarInfo.CarListBean car : nearbyCarInfo.getCarList()) {
                addMarker(Double.valueOf(car.getLat()), Double.valueOf(car.getLng()), Constant.TYPE_CAR);
            }
            /**
             * 每辆车有个最短到达时间，后期可能能用到
             */
        }
    }

    /**
     * 格式控制器
     */
    DecimalFormat df = new DecimalFormat("#.0");

    /**
     * 打车价格结果
     *
     * @param taxiPriceInfo
     */
    @Override
    public void showPriceResult(TaxiPriceInfo taxiPriceInfo) {
        Log.i(TAG, taxiPriceInfo.toString());
        //加入车辆
        taxiResultInfo.setVisibility(View.VISIBLE);
        estimateId = taxiPriceInfo.getEstimateId();
        distanceTv.setText("预估距离:" + df.format(taxiPriceInfo.getDistance() / 1000) + "km");
        timeCostTv.setText("预计耗时:" + taxiPriceInfo.getDuration() + "min");
        List<Fragment> fragments = new ArrayList<>();
        adapter.clear();
        //初始化Fragment
        for (TaxiPriceInfo.PricesBean prices : taxiPriceInfo.getPrices()) {
            Fragment fragment = new TaxiKindsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CAR_TYPE, prices.getName());
            bundle.putFloat(Constant.CAR_PRICES, prices.getPrice());
            bundle.putInt(Constant.CAR_GROUP_ID, prices.getCarGroupId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }


    /**
     * 地图层移动监听 用来根据屏幕中心来进行移动定位位置显示
     *
     * @param cameraPosition
     */
    boolean isFirstRecocd = true;//记录屏幕中心点的辅助变量

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        //获取可视区
        Log.i(TAG, "地图移动层完成");
        if (!isFirstMapLoad) {
            //因为必须要等地图加载完成，才可以用屏幕点进行定位
            return;
        }
        if (hasCallTaxi) {
            return;
        }

        if (isFirstRecocd) {
            //aMap.clear();//清除原点定位
            setOriginLocationMarker();
            isFirstRecocd = false;
        }

        nowZoom = cameraPosition.zoom;//更新zoom
        //根据屏幕中心点重新进行定位
        Projection projection = aMap.getProjection();
        //根据屏幕中心找到地图中心
        //记录当前定位在屏幕中的哪个位置
        LatLng centerLatLng = projection.fromScreenLocation(new Point(screenCenterX, screenCenterY));
        if (centerLatLng != null) {
            //查询地点，重新
            //            final Marker marker = aMap.addMarker(new MarkerOptions().position(centerLatLng).title("当前定位点").snippet("DefaultMarker"));
            slat = centerLatLng.latitude;
            slot = centerLatLng.longitude;
            LatLonPoint latLonPoint = new LatLonPoint(centerLatLng.latitude, centerLatLng.longitude);
            //解析当前点
            //搜索开始前，清空文本
            locationText.setText("正在加载地址，请稍后...");
            RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
            geocodeSearch.getFromLocationAsyn(query);
        }
    }

    /**
     * 设置原始定位点和初始化屏幕中心
     */
    private void setOriginLocationMarker() {
        //添加一个marker
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(centerLocation);
        markerOption.draggable(false);//设置Marker可拖动

        Bitmap bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.auto_location_record_icon);
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = DensityUtil.dip2px(this, 20);
        int newHeight = newWidth;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix,
                true);
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(bitMap));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);

        //初始化screenX,screenY
        Projection projection = aMap.getProjection();
        Point centerPoint = projection.toScreenLocation(centerLocation);
        screenCenterX = centerPoint.x;
        screenCenterY = centerPoint.y;
        //为将定位点与扫描点对其，进行误差纠正 ***********核心
        centerImage.setY(screenCenterY - centerImage.getHeight());
        centerImage.invalidate();
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }


    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                this, aMap, driveRouteResult.getPaths().get(0),
                driveRouteResult.getStartPos(), driveRouteResult.getTargetPos(), null);
        aMap.clear();
        drivingRouteOverlay.removeFromMap();
        drivingRouteOverlay.addToMap();
        drivingRouteOverlay.zoomToSpan();
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onMapLoaded() {
        isFirstMapLoad = true;
    }

    /*********************************************************************************************/
    @Override
    protected void onDestroy() {
        Log.i(TAG,"Destory");
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (pw != null) {
            pw = null;//释放引用
        }
        ActivityStack.getScreenManager().popActivity(this);
        if (dialog != null &&  dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
//        DialogProgressUtils.clear();
     
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.charge_amount:
                ToActivityUtil.toNextActivity(this, ChargeActivity.class);
                break;
            case R.id.history_order:
                ToActivityUtil.toNextActivity(this, HistoryOrderActivity.class);
                break;
            case R.id.team:
                ToActivityUtil.toNextActivity(this, AboutTeamActivity.class);
                break;
            case R.id.author:
                ToActivityUtil.toNextActivity(this, AboutUsActivity.class);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void showOrderDetial(OrderDetailInfo orderDetailInfo) {
        //一旦请求成功，开启服务
        Intent intent = new Intent(this, OrderDetailService.class);
        intent.putExtra(Constant.ORDER_ID, orderDetailInfo.getOrder().getId());//传入orderid
        startService(intent);
        //同时调转Activity
        Intent intent1 = new Intent(this, WaitingDriverArriveActivity.class);
        intent1.putExtra(Constant.ORDER_DETAIL_INFO, orderDetailInfo);
        startActivity(intent1);
//        DialogProgressUtils.hideDialogProgress();
        finish();//结束
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
//        DialogProgressUtils.hideDialogProgress();
//        DialogProgressUtils.clear();
    }

    private void getLocationPermission() {
        ShenZhouTaxiActivityPermissionsDispatcher.locationNeedsWithCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void locationNeeds() {
        //        ToastUtil.shortToast("成功授权");
        Log.i(TAG, "成功授权");
        initActivityComponent();
        //初始化组件 注入器
        initInjector();
        initViews();
        initRxBus();
        initRouteSearch();
    }
    

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ShenZhouTaxiActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        //拒绝的时候
        Log.i(TAG, "result");
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void locationOnShow(final PermissionRequest request) {
        Log.i(TAG, "show");
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("使用本软件需要定位权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        request.proceed();
                    }
                }).setNegativeButton("算了吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void locationDenied() {
        Log.i(TAG, "拒绝");
        ToastUtil.shortToast("您取消了授权，程序将无法使用");
        finish();
    }


    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void locationDeniedForever() {
        Log.i(TAG, "永久jude");
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("您未授予定位权限，这将导致本软件无法使用，请您主动授予")
                .setCancelable(false)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        gotoMiuiPermission();
                    }
                }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ToastUtil.shortToast("您取消了授权，程序将无法使用");
                finish();
            }
        }).show();
    }

    private void gotoMiuiPermission() {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", getPackageName());
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            gotoMeizuPermission();
        }
    }

    private void gotoMeizuPermission() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            gotoHuaweiPermission();
        }
    }

    private void gotoHuaweiPermission() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startActivity(getAppDetailSettingIntent());
        }

    }

    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
}
