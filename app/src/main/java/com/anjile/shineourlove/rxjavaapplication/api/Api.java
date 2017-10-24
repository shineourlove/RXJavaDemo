package com.anjile.shineourlove.rxjavaapplication.api;


import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.anjile.shineourlove.rxjavaapplication.entity.GitHubApiEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.Retrofit2EntrtyTest1;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("architecture/verification.user")
    Call<ResponseBody> securityCodePostCall(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("architecture/verification.user")
    Call<ResponseBody> checkSecurityCodePostCall(@Field("phone") String phone, @Field("authCode") String authCode);

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

    /**
     * 使用@Field查询需要使用@FormUrlEncoded序列化
     *
     * @param phone    电话号码
     * @param authCode 验证码
     * @return 观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/verification.user")
    io.reactivex.Observable<CheckSecurityCodeBean> loginPostObservable(@Field("phone") String phone, @Field("authCode") String authCode);
}
