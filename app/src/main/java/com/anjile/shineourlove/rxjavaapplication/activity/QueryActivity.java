package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.img_query_area_more)
    ImageView imgQueryAreaMore;
    @BindView(R.id.img_query_aptitude)
    ImageView imgQueryAptitude;
    @BindView(R.id.img_query_area_performance)
    ImageView imgQueryAreaPerformance;
    @BindView(R.id.img_query_personal)
    ImageView imgQueryPersonal;
    @BindView(R.id.img_query_reputation)
    ImageView imgQueryReputation;
    @BindView(R.id.txt_query_company_query_now)
    TextView txtQueryCompanyQueryNow;
    @BindView(R.id.img_query_location)
    ImageView imgQueryLocation;
    @BindView(R.id.txt_query_finish_time)
    TextView txtQueryFinishTime;
    @BindView(R.id.txt_query_start_to_end)
    TextView txtQueryStartToEnd;
    @BindView(R.id.txt_query_start_time)
    TextView txtQueryStartTime;
    @BindView(R.id.txt_query_check_unit)
    TextView txtQueryCheckUnit;
    @BindView(R.id.txt_query_input_scale)
    TextView txtQueryInputScale;
    @BindView(R.id.txt_query_performance_query_now)
    TextView txtQueryPerformanceQueryNow;

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
        imgTopStatusBarBack.setImageResource(R.drawable.back);
        txtTopStatusBarTitle.setText(R.string.search_hint);
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
