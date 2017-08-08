package com.example.taxihelper.mvp.model.base;

/**
 * Created by 猿人 on 2017/5/24.
 */


import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.Data;
import com.example.taxihelper.net.Api;
import com.example.taxihelper.net.ApiException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * 该类主要提供网络请求所需要的service和进行状态码的过滤
 */
public class BaseModelImpl {
    private OkHttpClient client = new OkHttpClient.Builder()
            .build();
    private Retrofit mRetrofit1;
    private Retrofit mRetrofit2;

    public BaseModelImpl() {
        mRetrofit1 = new Retrofit.Builder()
                .baseUrl(Constant.SHENZHOU_TEST_AUTH+"/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mRetrofit2 = new Retrofit.Builder()
                .baseUrl(Constant.SHENZHOU_TEST_API+"/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    
    protected Api getAuthApi(){
        return mRetrofit1.create(Api.class);
    }
    protected Api getApi(){
        return mRetrofit2.create(Api.class);
    }
    

    public Observable filterStatus(Observable observable) {
        return observable.map(new ResultFilter());
    }

    private class ResultFilter<T> implements Func1<Data<T>, T> {
        @Override
        public T call(Data<T> tHttpBean) {
            if (tHttpBean.getCode() != 1) {
                throw new ApiException(tHttpBean.getCode());
            }
            return tHttpBean.getContent();
        }
    }
}
