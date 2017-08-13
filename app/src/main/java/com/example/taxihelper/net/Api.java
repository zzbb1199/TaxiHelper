package com.example.taxihelper.net;

import com.example.taxihelper.mvp.entity.CitiesInfo;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.Data;
import com.example.taxihelper.mvp.entity.GainAccessToken;

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
}
