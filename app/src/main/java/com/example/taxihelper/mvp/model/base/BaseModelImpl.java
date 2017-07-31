package com.example.taxihelper.mvp.model.base;

/**
 * Created by 猿人 on 2017/5/24.
 */


import com.example.taxihelper.mvp.entity.Data;
import com.example.taxihelper.net.Api;
import com.example.taxihelper.net.ApiException;
import com.example.taxihelper.net.ResponseCons;

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
    private Retrofit mRetrofit;
    protected Api api;

    public BaseModelImpl() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ResponseCons.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = mRetrofit.create(Api.class);
    }

    public Observable filterStatus(Observable observable) {
        return observable.map(new ResultFilter());
    }

    private class ResultFilter<T> implements Func1<Data<T>, T> {
        @Override
        public T call(Data<T> tHttpBean) {
            if (tHttpBean.getCode() != 200) {
                throw new ApiException(tHttpBean.getCode());
            }
            return tHttpBean.getData();
        }
    }
}
