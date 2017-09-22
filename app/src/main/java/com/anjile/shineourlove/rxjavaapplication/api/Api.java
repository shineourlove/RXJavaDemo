package com.anjile.shineourlove.rxjavaapplication.api;


import com.anjile.shineourlove.rxjavaapplication.entity.LoginRequest;
import com.anjile.shineourlove.rxjavaapplication.entity.LoginResponse;
import com.anjile.shineourlove.rxjavaapplication.entity.RegisterRequest;
import com.anjile.shineourlove.rxjavaapplication.entity.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/8/29.
 */

public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest request);
}
