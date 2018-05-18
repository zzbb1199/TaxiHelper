package com.example.taxihelper.constant;

/**
 * Created by 张兴锐 on 2017/8/7.
 */

public class Constant {
    
    //===============================神州
    /**
     * 测试url
     */
    public static final String SHENZHOU_TEST_AUTH = "https://sandboxoauth.10101111.com";
    public static final String SHENZHOU_TEST_API = "https://sandboxapi.10101111.com";

    /**
     * 正式url
     */
    public static final String SHENZHOU_OFFICAL_AUTH = "https://oauth.10101111.com";
    public static final String SHENZHOU_OFFICAL_API = "https://openapi.10101111.com";

    /**
     * 相关
     */
    public static final String SHENZHOU_CLIENT_ID = "A73371C10000030A";
    public static final String SHENZHOU_CLIENT_PASSWORD = "0ema19eje46m8j0gxp77";
    /**
     * 重定向URL
     */
    public static final String SHENZHOUE_REDIRECT_URL = "http://nmid.cqupt.edu.cn/";

    /**
     * 电话号码
     */
    public static final String PHONE_NUM = "phone_number";
    /**
     * 字符串
     */
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_ACCESS_TOKEN ="refresh_access_token";
    public static final String EXPIRED_TIME = "expired_time";
    
    public static final String REFRESH_TYPE = "refresh_token";
    
    //
    public static final String CURRENT_CITY = "current_city";
    public static final String CURRENT_LOCATION = "location";
    public static final String TYPE = "type";
    public static final String TYPE_START = "start";
    public static final String TYPE_END = "end";
    public static final String TYPE_CAR = "car";
    public static final String SERVICE_ID = "serviceId";

    /**
     * 打车结果常亮
     */
    public static final String CAR_TYPE = "car_type";
    public static final String CAR_PRICES = "price";
    public static final String CAR_GROUP_ID ="car_group_id";


    /**
     * 订单
     */
    public static final String ORDER_ID = "order_id";


    /**
     * 测试接单司机
     */
    public static final long SHANGWU_DRIVER= 43851;
    public static final long GONGWU_DRIVER = 42393;
    public static final long HAOHUA_DRIVER = 44118;

    /**
     * 测试订单时选择的类型
     */
    public static final String SHANGWU_TYPE = "shangwu_type";
    public static final String GONGWU_TYPE = "gongwu_type";
    public static final String HAOHUA_TYPE = "haohua_type";

    /**
     * created > dispatched > arriving > arrived > serviceStarted > serviceFinished > feeSubmitted > paid > completed
     */
    public static final String ORDER_CREATED = "created";
    public static final String ORDER_DISPATCHED = "dispatched";
    public static final String ORDER_ARRIVING = "arriving";
    public static final String ORDER_ARRIVED = "arrived";
    public static final String ORDER_SERVICE_STARTED = "serviceStarted";
    public static final String ORDER_SERVICE_FINISHED = "serviceFinished";
    public static final String ORDER_FEE_SUBMITTED = "feeSubmitted";
    public static final String ORDER_PAID = "paid";
    public static final String ORRDER_COMPLEDTED = "completed";
    public static final String [] ORDER_STATUS = new String[]{"dispatched","arriving","arrived",
            "serviceStarted","serviceFinished","feeSubmitted","paid","completed"};
    
    public static final String ORDER_DETAIL_INFO = "orderDetailInfo";


    public static final String TAXI_PRICE = "taxi_price_data"; 

}
