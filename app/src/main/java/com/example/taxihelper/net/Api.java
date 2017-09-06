package com.example.taxihelper.net;

import com.example.taxihelper.mvp.entity.CitiesInfo;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.Data;
import com.example.taxihelper.mvp.entity.GainAccessToken;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.entity.UserInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 猿人 on 2017/5/24.
 */

public interface Api {

    /**
     * 获取accessToken
     *
     * @param clientId
     * @param clientSecret
     * @param type
     * @param code
     * @param redirectUrl
     * @return
     */
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<GainAccessToken> gainAccessToken(@Query("client_id") String clientId, @Field("client_secret") String clientSecret
            , @Field("grant_type") String type, @Field("code") String code, @Field("redirect_uri") String redirectUrl);

    /**
     * 更新授权
     *
     * @param clientId
     * @param cliendSeret
     * @param type
     * @param refreshToken
     * @return
     */
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<GainAccessToken> refreshAccessToken(@Query("client_id") String clientId, @Field("client_secret") String cliendSeret, @Field("grant_type") String type,
                                                   @Field("refresh_token") String refreshToken);

    /**
     * 获取城市服务
     *
     * @param accessToken
     * @param slat
     * @param slng
     * @return
     */
    @POST("/v1/resource/common/getCityService")
    @FormUrlEncoded
    Observable<Data<CityInfo>> getCityInfo(@Field("access_token") String accessToken, @Field("slat") double slat, @Field("slng") double slng);

    /**
     * 获取城市列表
     */
    @GET("/v1/resource/common/getCityList")
    Observable<Data<List<CitiesInfo>>> getCitiesInfo(@Query("access_token") String accessToken, @Query("type") String type, @Query("serviceId") Integer serviceId);

    /**
     * 获取临近车辆
     */
    @GET("/v1/resource/common/getNearbyCarInfo")
    Observable<Data<NearbyCarInfo>> getNearbyCarInfo(@Query("access_token") String accessToken, @Query("slat") double slat, @Query("slng") double slot);

    /**
     * 费用预估
     */
    @POST("/v1/resource/common/estimate/price")
    @FormUrlEncoded
    Observable<Data<TaxiPriceInfo>> getTaxiPriceInfo(@Query("access_token") String accessToken, @Field("slat") double slat, @Field("slng") double slot,
                                                     @Field("elat") double elat, @Field("elng") double elng,
                                                     @Field("serviceId") Integer serviceId);
    /**
     *  @Field("cityId") Integer cityId
     @Field("departureTime") Long departureTime,
     @Field("flt") String flt,@Field("flightDate") Long flightDate,
     @Field("flightDelayTime") Integer flightDelayTime,
     @Field("airCode") String airCode)
     */


    /**
     * 创建订单
     */
    @POST("/v1/action/order/create")
    @FormUrlEncoded
    Observable<Data<CreateOrder>> createOrder(@Query("access_token") String accessToken, @Field("serviceId") Integer serviceId,
                                              @Field("carGroupId") Integer carGroupId, @Field("passengerMobile") String passengerMobil,
                                              @Field("passengerName") String passengerName, @Field("slat") double slat, @Field("slng") double slot,
                                              @Field("startName") String startName, @Field("startAddress") String startAddress,
                                              @Field("endName") String endName, @Field("endAddress") String endAddress,
                                              @Field("elat") double elat, @Field("elng") double elot, @Field("estimateId") String estimateId);

    /**
     * 用户信息
     */
    @GET("/v1/resource/user/getUserInfo")
    Observable<Data<UserInfo>> getUserInfo(@Query("access_token") String accessToken);

    /**
     * 订单详情
     */
    @GET("/v1/resource/order/getOrderDetail")
    Observable<Data<OrderDetailInfo>> getOrderDetail(@Query("access_token") String accessToken,@Query("orderId") String orderId);

    /**
     * 充值
     */
    @POST("/v1/action/user/recharge")
    @FormUrlEncoded
    Observable<Data<String>> chargeAccount(@Field("access_token") String accessToken,@Field("amount") Integer amount,@Field("mobile") String phoneNum);

    /**
     * 模拟接单
     */
    @POST("/v1/action/order/changeStatus")
    @FormUrlEncoded
    Observable<Data<OrderStatus>> changOrderStatus(@Field("access_token") String accessToken,@Field("orderId") String orderId,
                                                   @Field("driverId") long driverId,@Field("status") String status);
    
}
    
