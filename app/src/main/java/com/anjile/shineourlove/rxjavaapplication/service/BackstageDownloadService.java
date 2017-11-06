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
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.BackstageDownloadControl;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2016/10/15.
 */
public class BackstageDownloadService extends Service {

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
            case 0:
                Log.i("backstage_download", "initDownloadControl: ");
                addAptitudeIndex();
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
}