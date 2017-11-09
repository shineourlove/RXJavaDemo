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
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class EnterpriseQueryResultActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.rcv_aptitude_query_details)
    RecyclerView rcvAptitudeQueryDetails;
    AptitudeQueryItemAdapter adapter;
    List<EnterpriseSearchEntity.DataBean> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_aptitude_query);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        txtTopStatusBarTitle.setText("企业查询结果");
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        initAdapter();
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

    public void initAdapter() {
        searchList = new ArrayList<>();
        adapter = new AptitudeQueryItemAdapter(searchList, this);
        rcvAptitudeQueryDetails.setLayoutManager(new LinearLayoutManager(this));
        rcvAptitudeQueryDetails.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadResult(EnterpriseSearchEntity entity) {
        searchList.addAll(entity.getData());
        adapter.notifyDataSetChanged();
        if (searchList.size() == 0) {
            Toast.makeText(this, "未查询到符合条件的企业", Toast.LENGTH_SHORT).show();
        } else {
            if (entity.getData().size() == 0) {
                Toast.makeText(this, "没有更多符合条件的企业", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
