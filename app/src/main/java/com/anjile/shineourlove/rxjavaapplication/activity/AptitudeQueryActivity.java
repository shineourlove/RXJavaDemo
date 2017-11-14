package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.AptitudeQueryItemAdapter;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeQueryItemEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AptitudeQueryActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.rcv_aptitude_query_details)
    RecyclerView rcvAptitudeQueryDetails;
    AptitudeQueryItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_aptitude_query);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.aptitude_query);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        initQuery();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_status_bar_back:
                finish();
                break;
            case R.id.txt_top_status_bar_left:
                finish();
                break;
        }
    }

    private int count = 0;
    List<EnterpriseSearchEntity.DataBean> searchList;

    public void initQuery() {
        searchList = new ArrayList<>();
        adapter = new AptitudeQueryItemAdapter(searchList, this);
        rcvAptitudeQueryDetails.setLayoutManager(new LinearLayoutManager(this));
        rcvAptitudeQueryDetails.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.167:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        /*UserInfoDao infoDao = new UserInfoDao(this);
        String phone = infoDao.query().get(0).getPhone();*/
        Log.i("aptitude_query", "initQuery: " + getIntent().getStringExtra("parameter"));
        Log.i("aptitude_query", "count: " + count);
        Log.i("aptitude_query", "count + 20: " + (count + 20));
        api.fuzzySearchObservable(new UserInfoDao(this).query().get(0).getPhone()
                , getIntent().getStringExtra("parameter"), (count) + "", (20) + ""
                ,new UserInfoDao(this).query().get(0).getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnterpriseSearchEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("aptitude_query", "Disposable: ");
                    }

                    @Override
                    public void onNext(EnterpriseSearchEntity value) {
                        Log.i("aptitude_query", "onNext: " + value.getData().size());
                        if (value.getData() != null && value.getData().size() > 0) {
                            for (int i = 0; i < value.getData().size(); i++) {
                                searchList.add(value.getData().get(i));
                            }
                        } else {
                            Toast.makeText(AptitudeQueryActivity.this, "没有查询到符合条件的企业", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aptitude_query", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("aptitude_query", "onComplete: ");
                    }
                });
        count = count + 20;
    }
}
