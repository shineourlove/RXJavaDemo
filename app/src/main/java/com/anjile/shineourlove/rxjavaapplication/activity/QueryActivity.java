package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;

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
        }
    }

    public void setEnterpriseQuery() {
        rbQueryEnterpriseUnlimited.setOnCheckedChangeListener(this);
        rbQueryEnterpriseEcdemic.setOnCheckedChangeListener(this);
        rbQueryEnterpriseLocal.setOnCheckedChangeListener(this);
    }

    private int localType = 0;

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
}
