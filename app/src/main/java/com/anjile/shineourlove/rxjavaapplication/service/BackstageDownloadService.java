package com.anjile.shineourlove.rxjavaapplication.service;


import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingBean;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingDao;
import com.anjile.shineourlove.rxjavaapplication.db.EnterpriseQueryDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleDao;
import com.anjile.shineourlove.rxjavaapplication.db.PurposeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PurposeAllDao;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.PersonalAllEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.PurposeEntity;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.BackstageDownloadControl;
import com.anjile.shineourlove.rxjavaapplication.manager.NetManager;
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;
import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by shineourlove on 2016/10/15.
 * 后台下载服务
 */
public class BackstageDownloadService extends Service {
    private int enterpriseCount = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("back_service", "onBind>>>>>>>>>>>>>");
        return null;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("back_service", "onUnbind>>>>>>>>>>>>>");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        Log.i("back_service", "onCreate>>>>>>>>>>>>>");
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        Log.i("back_service", "onDestroy>>>>>>>>>>>>>");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("back_service", "onStartCommand>>>>>>>>>>>>>");
        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initDownloadControl(BackstageDownloadControl c) {
        switch (c.getType()) {
            case 0: //添加所有资质到数据库
                Log.i("backstage_download", "initDownloadControl: ");
                addAptitudeIndex();
                break;
            case 1://添加所有工程用途到数据库
                addPurposeIndex();
                break;
            case 2://条件查询 从开始目录请求
                enterpriseCount = 0;
                conditionQuery(enterpriseCount);
                enterpriseCount += 20;
                break;
            case 3://条件查询 继续请求（页数）
                conditionQuery(enterpriseCount);
                break;
            case 4://添加所有人员目录到数据库
                addPersonalIndex();
                break;
        }
    }

    public void addAptitudeIndex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        api.aptitudeIndexObservable().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()) //onNext()所在线程
                .subscribe(new Observer<AptitudeAllEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("backstage_download", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(AptitudeAllEntity value) {
                        Log.i("backstage_download", "onNext: ");
                        saveAptitudeIndex(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("backstage_download", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("backstage_download", "onComplete: ");
                    }
                });
    }

    public void saveAptitudeIndex(List<AptitudeAllEntity.DataBean> list) {
        AptitudeAllDao dao = new AptitudeAllDao(this);
        dao.clearAll();
        List<AptitudeAllBean> beanList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            beanList.add(new AptitudeAllBean(list.get(i).getAptitude_type(),
                    list.get(i).getAptitude_name(),
                    list.get(i).getAptitude_id(),
                    list.get(i).getAptitude_Grade(),
                    list.get(i).getType_id(),
                    list.get(i).getInitial(),
                    list.get(i).getId()));
        }
        dao.addAll(beanList);
    }

    public void addPurposeIndex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        api.purposeObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<PurposeEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PurposeEntity value) {
                        savePurposeIndex(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addPersonalIndex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        api.personalObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<PersonalAllEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonalAllEntity value) {
                        savePersonalIndex(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void savePurposeIndex(List<PurposeEntity.DataBean> list) {
        PurposeAllDao dao = new PurposeAllDao(this);
        dao.clearAll();
        List<PurposeAllBean> beanList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            beanList.add(new PurposeAllBean(list.get(i).getProject_purpose(), list.get(i).getId(), list.get(i).getClock() + "", false));
        }
        dao.addAll(beanList);
    }

    public void savePersonalIndex(PersonalAllEntity personalAllEntity) {
        PersonalAllDao dao = new PersonalAllDao(this);
        dao.clearAll();
        dao.addAll(personalAllEntity.getData());
    }

    public void conditionQuery(int count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        String area = "";
        String require = "1";
        String legalPerson = "";
        if (queryDao.query().size() > 0 && queryDao.query().get(0).getProvince() != null) {
            area = queryDao.query().get(0).getProvince();
        }
        if (queryDao.query().size() > 0 && queryDao.query().get(0).getRequire() != null) {
            require = queryDao.query().get(0).getRequire();
        }
        if (queryDao.query().size() > 0 && queryDao.query().get(0).getLegal_person() != null) {
            legalPerson = queryDao.query().get(0).getLegal_person();
        }
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        String startDate = "0";
        String endDate = "0";
        String purpose = "";
        int scale = 0;
        int number = 1;
        String unit = "1";
        List<Integer> purList = new ArrayList<>();
        List<EnterprisePerformanceSettingBean> queryList = settingDao.query();
        if (queryList != null && queryList.size() > 0) {
            if (queryList.get(0).getUse() != null) {
                purpose = queryList.get(0).getUse();
                String[] purs = queryList.get(0).getUse().split("[,]");
                for (int i = 0; i < purs.length; i++) {
                    purList.add(Integer.parseInt(purs[i]));
                }
                int[] purArr = new int[purList.size()];
                for (int i = 0; i < purList.size(); i++) {
                    purArr[i] = purList.get(i);
                }

            }
            if (queryList.get(0).getStart() != null && !queryList.get(0).getStart().equals("") && !queryList.get(0).getStart().equals("0")) {
                startDate = queryList.get(0).getStart();
            }
            if (queryList.get(0).getEnd() != null && !queryList.get(0).getEnd().equals("") && !queryList.get(0).getEnd().equals("0")) {
                endDate = queryList.get(0).getEnd();
            }
            if (queryList.get(0).getNumber() > 0) {
                number = queryList.get(0).getNumber();
            }
            if (queryList.get(0).getScale() > 0)
                scale = queryList.get(0).getScale();
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("0"))
                unit = "1";
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("1"))
                unit = "0";
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("2"))
                unit = "0";
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("3"))
                unit = "0";
        }
        AptitudeSelectedDao selectedDao = new AptitudeSelectedDao(this);
        List<String> aptitudes = new ArrayList<>();
        if (selectedDao.query() != null && selectedDao.query().size() > 0) {
            List<AptitudeSelectedBean> selectedBeanList = selectedDao.query();
            for (int i = 0; i < selectedBeanList.size(); i++) {
                String[] aps = selectedBeanList.get(i).getDetails().split(",");
                for (int j = 0; j < aps.length; j++) {
                    if (!aps[j].equals("")) {
                        aptitudes.add(aps[j]);
                    }
                }
            }
        }
        String[] aptArr = new String[aptitudes.size()];
        for (int i = 0; i < aptitudes.size(); i++) {
            aptArr[i] = aptitudes.get(i);
        }
        Log.i("background_service", "aptArr: " + Arrays.asList(aptArr).toString());
        JSONObject jsonObj = new JSONObject();//对象，json形式
        try {
            jsonObj.put("mixdate", Long.parseLong(startDate));//向pet对象里面添加值
            jsonObj.put("maxdate", Long.parseLong(endDate));
            jsonObj.put("purpose", purpose);
            jsonObj.put("project_scale", scale);
            jsonObj.put("number", number);
            jsonObj.put("units", unit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String performance = "{}";
        if (settingDao.query() == null || settingDao.query().size() == 0) {
            performance = "{}";
        } else {
            performance = jsonObj.toString();
        }
        //staff 格式:[{"grade_id":"人员类别等级ID","number":"数量"},{"grade_id":"1","number":"2"}]
        PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
        PersonalTitleDao titleDao = new PersonalTitleDao(this);
        PersonalManagerDao managerDao = new PersonalManagerDao(this);

        JSONArray jsonArray = new JSONArray();
        if (registerDao.query() != null && registerDao.query().size() > 0) {
            List<PersonalRegisterBean> list = registerDao.query();
            for (int i = 0; i < list.size(); i++) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("grade_id", list.get(i).getDetails());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (titleDao.query() != null && titleDao.query().size() > 0) {
            List<PersonalTitleBean> list = titleDao.query();
            for (int i = 0; i < list.size(); i++) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("grade_id", list.get(i).getDetails());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (managerDao.query() != null && managerDao.query().size() > 0) {
            List<PersonalManagerBean> list = managerDao.query();
            for (int i = 0; i < list.size(); i++) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("grade_id", list.get(i).getDetails());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i("background_service", "jsonObject: " + jsonArray.toString());
        Log.i("background_service", "aptitude: " + Arrays.asList(aptArr).toString());
        Log.i("background_service", "performance: " + performance);
        api.conditionSearchObservable("13637897256", count + "",
                count + 20 + "", area, require,
                legalPerson, Arrays.asList(aptArr).toString(), performance, jsonArray.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnterpriseSearchEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("background_service", "Disposable: ");
                    }

                    @Override
                    public void onNext(EnterpriseSearchEntity value) {
                        Log.i("background_service", "onNext: " + value.getData().size());
                        EventBus.getDefault().post(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("background_service", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("background_service", "onComplete: ");
                    }
                });
    }

    /**
     * 清空查询条件数据库
     */
    public void clearQueryDataBase() {
        new AptitudeSelectedDao(this).clearAll();
        new EnterprisePerformanceSettingDao(this).clearAll();
        new EnterpriseQueryDao(this).clearAll();
    }


}