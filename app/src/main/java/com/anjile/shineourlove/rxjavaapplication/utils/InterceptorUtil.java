package com.anjile.shineourlove.rxjavaapplication.utils;

import android.content.Context;
import android.util.Log;

import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/11/14.
 */
public class InterceptorUtil {
    public static String TAG = "ok_http_log";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "ok_http_log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等
                Request mRequest = chain.request();
                return chain.proceed(mRequest);
            }
        };
    }

    public static Interceptor HeaderWithInterceptor(final String token,final String phone) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等
                Request request = chain.request().newBuilder()
                        .addHeader("token", token)
                        .addHeader("phone", phone)
                        .build();
                return chain.proceed(request);
            }
        };

    }
}
