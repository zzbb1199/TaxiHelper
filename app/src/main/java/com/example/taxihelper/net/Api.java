package com.example.taxihelper.net;

import com.example.taxihelper.mvp.entity.GainAccessToken;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 猿人 on 2017/5/24.
 */

public interface Api {
    
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<GainAccessToken> gainAccessToken(@Query("client_id") String clientId, @Field("client_secret") String clientSecret
    ,@Field("grant_type") String type,@Field("code") String code,@Field("redirect_uri") String redirectUrl);
    
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<GainAccessToken> refreshAccessToken(@Query("client_id") String clientId,@Field("client_secret") String cliendSeret,@Field("grant_type") String type,
                                                   @Field("refresh_token") String refreshToken);
}
