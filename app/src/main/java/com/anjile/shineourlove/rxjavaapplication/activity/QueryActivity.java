package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
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
}
