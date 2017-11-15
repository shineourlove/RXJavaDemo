package com.anjile.shineourlove.rxjavaapplication.api;


import com.anjile.shineourlove.rxjavaapplication.activity.EnterpriseDetailActivity;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseDetailsEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterprisePersonDetailsEntity;
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
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/8/29.
 */

public interface Api {

    /**
     * 使用@Field查询需要使用@FormUrlEncoded序列化
     *
     * @param phone    电话号码
     * @param authCode 验证码
     * @return 观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/verification.user")
    io.reactivex.Observable<CheckSecurityCodeBean> loginPostObservable(@Field("phone") String phone,
                                                                       @Field("authCode") String authCode);

    /**
     * 使用@Field查询需要使用@FormUrlEncoded序列化
     *
     * @param phone 电话号码
     * @return 观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/verification.user")
    io.reactivex.Observable<CheckSecurityCodeBean> loginCodeObservable(@Field("phone") String phone);
    //  http://192.168.1.167:8080/architecture/queryEnterprise.enterprise

    /**
     * 模糊查询企业的api
     * currentPage 开始的记录数
     * pageSize 查询条数
     */
    @FormUrlEncoded
    @POST("architecture/queryEnterprise.enterprise")
    io.reactivex.Observable<EnterpriseSearchEntity> fuzzySearchObservable(
            @Field("phone") String phone,
            @Field("parameter") String parameter,
            @Field("currentPage") String start,
            @Field("pageSize") String end,
            @Field("token") String token);

    /**
     * 获取所有资质目录的api
     *
     * @return 资质目录对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/corporate.enterprise")
    io.reactivex.Observable<AptitudeAllEntity> aptitudeIndexObservable(@Field("token") String token,
                                                                       @Field("phone") String phone);

    /**
     * 获取所有工程用途目录的api
     *
     * @return 资质工程用途对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/purpose.enterprise")
    io.reactivex.Observable<PurposeEntity> purposeObservable(@Field("token") String token,
                                                             @Field("phone") String phone);

    /**
     * 获取所有人员分类目录的api
     *
     * @return 人员分类目录对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/staff.enterprise")
    io.reactivex.Observable<PersonalAllEntity> personalObservable(@Field("token") String token,
                                                                  @Field("phone") String phone);

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
            @Field("staff") String staff,
            @Field("token") String token);

    /**
     * 获取单个企业详情的api
     *
     * @return 企业详情对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/particulars.enterprise")
    io.reactivex.Observable<EnterpriseDetailsEntity> enterpriseDetailsObservable(@Field("enterpriseid") String id,
                                                                                 @Field("token") String token,
                                                                                 @Field("phone") String phone);

    /**
     * 获取首页轮播的api
     *
     * @return 首页轮播对应的观察者对象
     */
    @POST("architecture/carousel.notoken")
    io.reactivex.Observable<HomePagePagerEntity> mainPagerObservable();

    /**
     * 获取所有人员分类目录的api
     *
     * @return 人员分类目录对应的观察者对象
     */
    @FormUrlEncoded
    @POST("architecture/enterpriseStaff.enterprise")
    io.reactivex.Observable<EnterprisePersonDetailsEntity> enterpeisePersonObservable(@Field("token") String token,
                                                                                      @Field("phone") String phone,
                                                                                      @Field("enterpriseid") String enterpriseid,
                                                                                      @Field("pageSize") int pageSize,
                                                                                      @Field("currentPage") int currentPage);
}
