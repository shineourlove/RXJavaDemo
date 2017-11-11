package com.anjile.shineourlove.rxjavaapplication.manager;

import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/11.
 */

public class NetManager {
    public Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }
}
