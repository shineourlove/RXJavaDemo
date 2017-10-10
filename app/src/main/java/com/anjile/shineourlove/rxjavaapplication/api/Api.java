package com.anjile.shineourlove.rxjavaapplication.api;


import com.anjile.shineourlove.rxjavaapplication.entity.GitHubApiEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.LoginRequest;
import com.anjile.shineourlove.rxjavaapplication.entity.LoginResponse;
import com.anjile.shineourlove.rxjavaapplication.entity.RegisterRequest;
import com.anjile.shineourlove.rxjavaapplication.entity.RegisterResponse;
import com.anjile.shineourlove.rxjavaapplication.entity.Retrofit2EntrtyTest1;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/8/29.
 */

public interface Api {
    @POST("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsPostCall(@Path("owner") String owner, @Path("repo") String repo);

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsGetCall(@Path("owner") String owner, @Path("repo") String repo);

    /**
     * method 表示请求的方法，区分大小写
     * path表示路径
     * hasBody表示是否有请求体(如果是直接返回对象，则需要指定hasBody = true)
     */
    @HTTP(method = "GET", path = "repos/{owner}/{repo}/contributors", hasBody = true)
    Call<List<Retrofit2EntrtyTest1>> detailsGetCall(@Path("owner") String owner, @Path("repo") String repo);

    @HTTP(method = "GET", path = "repos/{owner}/{repo}/contributors", hasBody = true)
    Call<List<GitHubApiEntity>> gitHubGetCall(@Path("owner") String owner, @Path("repo") String repo);

    @HTTP(method = "GET", path = "repos/{owner}/{repo}/contributors", hasBody = true)
    io.reactivex.Observable<List<Retrofit2EntrtyTest1>> login(@Path("owner") String owner, @Path("repo") String repo);
}
