package com.anjile.shineourlove.rxjavaapplication.api;


import com.anjile.shineourlove.rxjavaapplication.activity.EnterpriseDetailActivity;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseDetailsEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.GitHubApiEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.HomePagePagerEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.PersonalAllEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.PurposeEntity;
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

    //  http://192.168.1.167:8080/architecture/queryEnterprise.enterprise

    /**
     * 模糊查询企业的api
     */
    @FormUrlEncoded
    @POST("architecture/queryEnterprise.enterprise")
    io.reactivex.Observable<EnterpriseSearchEntity> fuzzySearchObservable(
            @Field("phone") String phone,
            @Field("parameter") String parameter,
            @Field("start") String start,
            @Field("end") String end);

    /**
     * 获取所有资质目录的api
     *
     * @return 资质目录对应的观察者对象
     */
    @POST("architecture/corporate.enterprise")
    io.reactivex.Observable<AptitudeAllEntity> aptitudeIndexObservable();

    /**
     * 获取所有工程用途目录的api
     *
     * @return 资质工程用途对应的观察者对象
     */
    @POST("architecture/purpose.enterprise")
    io.reactivex.Observable<PurposeEntity> purposeObservable();

    /**
     * 获取所有人员分类目录的api
     *
     * @return 人员分类目录对应的观察者对象
     */
    @POST("architecture/staff.enterprise")
    io.reactivex.Observable<PersonalAllEntity> personalObservable();

    /**
     * 条件查询企业的api
     *
     * @param phone       电话号码
     * @param start       开始条数
     * @param number      请求条数
     * @param area        企业地区（省市）
     * @param ifLocal     是否本地企业 1是 0否 3不限
     * @param corporate   法人
     * @param aptitude    资质 ["资质级别ID","资质级别ID"]
     * @param performance 业绩 格式
     *                    {“mixdate”:”开始时间”,” maxdate”:”结束时间”,” purpose”:”工程用途” ,” project_scale”:”业绩规模”, ”number”:”业绩数量”,” units”:”1-万元,0-其他”}
     *                    时间戳
     * @param staff       人员要求  格式:[{"grade_id":"人员类别等级ID","number":"数量"},{"grade_id":"1","number":"2"}]
     * @return 观察着对象
     */
    @FormUrlEncoded
    @POST("architecture/enterprise.enterprise")
    io.reactivex.Observable<EnterpriseSearchEntity> conditionSearchObservable(
            @Field("phone") String phone,
            @Field("currentPage") String start,
            @Field("pageSize") String number,
            @Field("site") String area,
            @Field("iflocal") String ifLocal,
            @Field("corporate") String corporate,
            @Field("aptitude") String aptitude,
            @Field("performance") String performance,
            @Field("staff") String staff);

    /**
     * 获取单个企业详情的api
     *
     * @return 企业详情对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/particulars.enterprise")
    io.reactivex.Observable<EnterpriseDetailsEntity> enterpriseDetailsObservable(@Field("enterpriseid") String id);

    /**
     * 获取首页轮播的api
     *
     * @return 首页轮播对应的观察者对象
     */
    @POST("architecture/carousel.notoken")
    io.reactivex.Observable<HomePagePagerEntity> mainPagerObservable();
}
