package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeAllEntity;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.BackstageDownloadControl;
import com.anjile.shineourlove.rxjavaapplication.service.BackstageDownloadService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;

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

public class QueryActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.ll_query_company_query)
    LinearLayout llQueryCompanyQuery;
    @BindView(R.id.ll_query_performance_query)
    LinearLayout llQueryPerformanceQuery;
    @BindView(R.id.txt_query_company_query)
    TextView txtQueryCompanyQuery;
    @BindView(R.id.txt_query_performance_query)
    TextView txtQueryPerformanceQuery;
    @BindView(R.id.txt_query_company_query_line)
    TextView txtQueryCompanyQueryLine;
    @BindView(R.id.txt_query_performance_query_line)
    TextView txtQueryPerformanceQueryLine;
    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;

    //企业查询
    @BindView(R.id.edt_query_enterprise_enterprise_name)
    EditText edtQueryEnterpriseEnterpriseName;
    @BindView(R.id.rl_query_enterprise_area)
    RelativeLayout rlQueryEnterpriseArea;
    @BindView(R.id.rb_query_enterprise_unlimited)
    RadioButton rbQueryEnterpriseUnlimited;
    @BindView(R.id.rb_query_enterprise_ecdemic)
    RadioButton rbQueryEnterpriseEcdemic;
    @BindView(R.id.rb_query_enterprise_local)
    RadioButton rbQueryEnterpriseLocal;
    @BindView(R.id.edt_query_enterprise_legal_person)
    EditText edtQueryEnterpriseLegalPerson;
    @BindView(R.id.rl_query_enterprise_qualification)
    RelativeLayout rlQueryEnterpriseQualification;
    @BindView(R.id.rl_query_enterprise_performance)
    RelativeLayout rlQueryEnterprisePerformance;
    @BindView(R.id.rl_query_enterprise_person)
    RelativeLayout rlQueryEnterprisePerson;
    @BindView(R.id.btn_query_company_query_now)
    Button btnQueryCompanyQueryNow;

    //业绩查询
    @BindView(R.id.edt_query_performance_project_name)
    EditText edtQueryPerformanceProjectName;
    @BindView(R.id.edt_query_performance_enterprise_name)
    EditText edtQueryPerformanceEnterpriseName;
    @BindView(R.id.edt_query_performance_builder_name)
    EditText edtQueryPerformanceBuilderName;
    @BindView(R.id.rl_query_performance_area)
    RelativeLayout rlQueryPerformanceArea;
    @BindView(R.id.txt_query_finish_time)
    TextView txtQueryFinishTime;
    @BindView(R.id.txt_query_start_time)
    TextView txtQueryStartTime;
    @BindView(R.id.txt_query_check_unit)
    TextView txtQueryCheckUnit;
    @BindView(R.id.txt_query_input_scale)
    TextView txtQueryInputScale;
    @BindView(R.id.btn_query_performance_query_now)
    Button btnQueryPerformanceQueryNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        imgTopStatusBarBack.setImageResource(R.drawable.back);
        txtTopStatusBarTitle.setText(R.string.search_hint);
        txtTopStatusBarLeft.setOnClickListener(this);

        txtQueryCompanyQuery.setOnClickListener(this);
        txtQueryPerformanceQuery.setOnClickListener(this);
        llQueryCompanyQuery.setVisibility(View.VISIBLE);
        llQueryPerformanceQuery.setVisibility(View.GONE);
        txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
        Intent intentBack = new Intent(this, BackstageDownloadService.class);
        startService(intentBack);

        setEnterpriseQuery();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_query_company_query:
                llQueryCompanyQuery.setVisibility(View.VISIBLE);
                llQueryPerformanceQuery.setVisibility(View.GONE);
                txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
                txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.txt_query_performance_query:
                llQueryCompanyQuery.setVisibility(View.GONE);
                llQueryPerformanceQuery.setVisibility(View.VISIBLE);
                txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
                txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
                break;
            case R.id.rl_query_enterprise_qualification://企业查询-资质选择
                Intent intent = new Intent(this, AptitudeSelectActivity.class);
                startActivityForResult(intent, RequestCode.QUERY_ENTERPRISE_APTITUDE_SELEST);
                break;
            case R.id.rl_query_enterprise_performance://企业查询-业绩选择
                break;
            case R.id.rl_query_enterprise_area://企业查询-省市地区
                break;
            case R.id.btn_query_company_query_now://企业查询-立即查询
                break;
        }
    }

    //企业查询的基础操作
    public void setEnterpriseQuery() {
        rbQueryEnterpriseUnlimited.setOnCheckedChangeListener(this);
        rbQueryEnterpriseEcdemic.setOnCheckedChangeListener(this);
        rbQueryEnterpriseLocal.setOnCheckedChangeListener(this);

        rlQueryEnterpriseQualification.setOnClickListener(this);
        rlQueryEnterprisePerformance.setOnClickListener(this);
        rlQueryEnterpriseArea.setOnClickListener(this);
        btnQueryCompanyQueryNow.setOnClickListener(this);

        //initAptitudeIndex();
    }

    private int localType = 0;

    /**
     * 选择变化 监听
     *
     * @param compoundButton radioButton
     * @param b              改变后的选择状态
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_query_enterprise_unlimited://不限
                if (b)
                    localType = 0;
                break;
            case R.id.rb_query_enterprise_ecdemic://外地
                if (b)
                    localType = 1;
                break;
            case R.id.rb_query_enterprise_local://本地
                if (b)
                    localType = 2;
                break;
        }
    }

    public void initAptitudeIndex() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new BackstageDownloadControl(0));
            }
        },1000);
    }
}
