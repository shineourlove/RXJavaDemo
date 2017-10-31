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

public class CreditSituationActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.txt_person_search_condition_highway)
    TextView txtPersonSearchConditionHighway;
    @BindView(R.id.ll_person_search_condition_highway)
    LinearLayout llPersonSearchConditionHighway;
    @BindView(R.id.txt_person_search_condition_building_municipal)
    TextView txtPersonSearchConditionBuildingMunicipal;
    @BindView(R.id.ll_person_search_condition_building_municipal)
    LinearLayout llPersonSearchConditionBuildingMunicipal;
    @BindView(R.id.txt_person_search_condition_hydraulic)
    TextView txtPersonSearchConditionHydraulic;
    @BindView(R.id.ll_person_search_condition_hydraulic)
    LinearLayout llPersonSearchConditionHydraulic;
    @BindView(R.id.txt_person_search_condition_water_transport)
    TextView txtPersonSearchConditionWaterTransport;
    @BindView(R.id.ll_person_search_condition_water_transport)
    LinearLayout llPersonSearchConditionWaterTransport;
    @BindView(R.id.txt_person_search_condition_park)
    TextView txtPersonSearchConditionPark;
    @BindView(R.id.ll_person_search_condition_park)
    LinearLayout llPersonSearchConditionPark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_credit_situation);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.credit_condition);
        txtTopStatusBarRight.setText(R.string.confirm);

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);

        llPersonSearchConditionHighway.setOnClickListener(this);
        llPersonSearchConditionBuildingMunicipal.setOnClickListener(this);
        llPersonSearchConditionHydraulic.setOnClickListener(this);
        llPersonSearchConditionWaterTransport.setOnClickListener(this);
        llPersonSearchConditionPark.setOnClickListener(this);
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
            case R.id.ll_person_search_condition_highway:
                break;
            case R.id.ll_person_search_condition_building_municipal:
                break;
            case R.id.ll_person_search_condition_hydraulic:
                break;
            case R.id.ll_person_search_condition_water_transport:
                break;
            case R.id.ll_person_search_condition_park:
                break;
        }
    }
}
