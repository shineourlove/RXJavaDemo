package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.EnterpriseAptitudeDetailsAdapter;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseDetailsEntity;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.manager.NetManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EnterpriseDetailActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_enterprise_detail_name)
    TextView txtEnterpriseDetailName;
    @BindView(R.id.rcv_enterprise_detail_aptitude)
    RecyclerView rcvEnterpriseDetailAptitude;
    @BindView(R.id.ll_enterprise_detail_person)
    LinearLayout llEnterpriseDetailPerson;
    @BindView(R.id.txt_enterprise_detail_contact)
    TextView txtEnterpriseDetailContact;
    @BindView(R.id.txt_enterprise_detail_authentication)
    TextView txtEnterpriseDetailAuthentication;
    @BindView(R.id.img_enterprise_detail_call)
    ImageView imgEnterpriseDetailCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_enterprise_detail);
        ButterKnife.bind(this);
        txtTopStatusBarTitle.setText("企业详情");
        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        queryEnterpriseDetails();
        initAdapter();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_top_status_bar_left:
                finish();
                break;
            case R.id.img_top_status_bar_back:
                finish();
                break;
        }
    }

    public void queryEnterpriseDetails() {
        Api api = new NetManager().getApi();
        api.enterpriseDetailsObservable(getIntent().getIntExtra("id", 108) + ""
                , new UserInfoDao(this).query().get(0).getPhone()
                , new UserInfoDao(this).query().get(0).getToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnterpriseDetailsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EnterpriseDetailsEntity value) {
                        loadInfo(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    EnterpriseAptitudeDetailsAdapter adapter;
    List<String> stringList;

    public void initAdapter() {
        stringList = new ArrayList<>();
        rcvEnterpriseDetailAptitude.setLayoutManager(new FullyLinearLayoutManager(this));
        adapter = new EnterpriseAptitudeDetailsAdapter(stringList, this);
        rcvEnterpriseDetailAptitude.setAdapter(adapter);
    }

    public void loadInfo(EnterpriseDetailsEntity value) {
        txtEnterpriseDetailName.setText(value.getData().get(0).getName());
        txtEnterpriseDetailContact.setText(value.getData().get(0).getCorporate());
        txtEnterpriseDetailAuthentication.setText("已认证");
        String s = value.getData().get(0).getAptitude();
        String[] strings = s.split("[,]");
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals(""))
                stringList.add(strings[i]);
        }
        adapter.notifyDataSetChanged();
    }
}
