package com.anjile.shineourlove.rxjavaapplication.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.utils.InterceptorUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/11.
 */

public class NetManager {
    public Api getApi() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    public Api getHeaderApi(final Context context) {
        UserInfoDao infoDao = new UserInfoDao(context);
        final String token = infoDao.query().get(0).getToken();
        final String phone = infoDao.query().get(0).getPhone();

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("token", "123456")
                                .addHeader("phone", "789123")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        Api api = retrofit.create(Api.class);
        return api;
    }
}
