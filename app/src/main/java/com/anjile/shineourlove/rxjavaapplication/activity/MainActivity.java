package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.entity.Retrofit2EntrtyTest1;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.BackstageDownloadControl;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentHome;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentPerson;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentMessage;
import com.anjile.shineourlove.rxjavaapplication.service.BackstageDownloadService;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private FragmentHome fragmentHome;
    private FragmentMessage fragmentTwo;
    private FragmentPerson fragmentPerson;
    private BadgeItem badgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void viewClick(View v) {

    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_main);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /**
         * 设置底部item的提示泡泡
         */
        badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.orange)
                .setBorderWidth(0);
        BottomNavigationItem item1 = new BottomNavigationItem(R.drawable.home_select, R.string.tab_one);
        item1.setInactiveIconResource(R.drawable.home);
        item1.setActiveColorResource(R.color.blue);
        item1.setBadgeItem(badgeItem);

        BottomNavigationItem item2 = new BottomNavigationItem(R.drawable.message_select, R.string.tab_two);
        item2.setInactiveIconResource(R.drawable.message);
        item2.setActiveColorResource(R.color.blue);

        BottomNavigationItem item3 = new BottomNavigationItem(R.drawable.my_select, R.string.tab_three);
        item3.setInactiveIconResource(R.drawable.my);
        item3.setActiveColorResource(R.color.blue);


        mBottomNavigationBar.setMode(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//设置底部导航按钮的模式
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.gray_word);//unSelected icon color
        mBottomNavigationBar.addItem(item1)
                .addItem(item2)
                .addItem(item3)
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(MainActivity.this);
        setDefaultFragment();
        //netDemo();
        netDemo2();

        initAptitudeIndex();
    }

    public void initAptitudeIndex() {
        Intent intentBack = new Intent(this, BackstageDownloadService.class);
        startService(intentBack);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new BackstageDownloadControl(0));
                EventBus.getDefault().post(new BackstageDownloadControl(1));
                EventBus.getDefault().post(new BackstageDownloadControl(4));
            }
        }, 50);
    }

    /**
     * 设置加载默认的碎片
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (fragmentHome == null)
            fragmentHome = new FragmentHome();
        transaction.replace(R.id.ll_content, fragmentHome).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (fragmentHome == null)
                    fragmentHome = new FragmentHome();
                transaction.replace(R.id.ll_content, fragmentHome);
                break;
            case 1:
                if (fragmentTwo == null)
                    fragmentTwo = new FragmentMessage();
                transaction.replace(R.id.ll_content, fragmentTwo);
                break;
            case 2:
                if (fragmentPerson == null)
                    fragmentPerson = new FragmentPerson();
                transaction.replace(R.id.ll_content, fragmentPerson);
                break;
            default:
                if (fragmentHome == null)
                    fragmentHome = new FragmentHome();
                transaction.replace(R.id.ll_content, fragmentHome);
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * Rxjava 操作符示例
     */
    public void initDemo2() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(5);
                e.onNext(6);
                e.onNext(7);
                e.onNext(8);
                Log.i("rxJava_test", "subscribe thread: " + Thread.currentThread().getName());
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer i) throws Exception {
                return "这是处理过的数字：" + i;
            }
        });

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("rxJava_test", "consumer thread: " + Thread.currentThread().getName());
                Log.i("rxJava_test", "accept: " + s);
            }
        };
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(consumer);
    }

    /**
     * 网络请求
     */
    public void netDemo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        Api api = retrofit.create(Api.class);
        Call call = api.contributorsGetCall("square", "retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("retrofit_test", "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void netDemo2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
/*        Call call = api.contributorsGetCall("square", "retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string();
                    Log.i("retrofit2_test", "onResponse: " + res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

        Call<List<Retrofit2EntrtyTest1>> call = api.detailsGetCall("square", "retrofit");
        call.enqueue(new Callback<List<Retrofit2EntrtyTest1>>() {
            @Override
            public void onResponse(Call<List<Retrofit2EntrtyTest1>> call, Response<List<Retrofit2EntrtyTest1>> response) {
                List<Retrofit2EntrtyTest1> entrtyTest1s = response.body();
                Log.i("retrofit2_test", "onResponse: " + entrtyTest1s.get(0).getAvatar_url());
            }

            @Override
            public void onFailure(Call<List<Retrofit2EntrtyTest1>> call, Throwable t) {
                Log.i("retrofit2_test", "onFailure: ");
            }
        });

        api.login("square", "retrofit").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Retrofit2EntrtyTest1>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Retrofit2EntrtyTest1> value) {
                        Log.i("retrofit2_test", "onResponse: " + value.get(0).getAvatar_url());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("retrofit2_test", "onError: 请求错误");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("retrofit2_test", "onComplete: 请求完成");
                    }
                });
    }
}
