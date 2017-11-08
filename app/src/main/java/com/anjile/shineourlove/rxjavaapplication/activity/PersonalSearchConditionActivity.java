package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalSearchConditionActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.txt_person_search_condition_register)
    TextView txtPersonSearchConditionRegister;
    @BindView(R.id.ll_person_search_condition_register)
    LinearLayout llPersonSearchConditionRegister;
    @BindView(R.id.txt_person_search_condition_title)
    TextView txtPersonSearchConditionTitle;
    @BindView(R.id.ll_person_search_condition_title)
    LinearLayout llPersonSearchConditionTitle;
    @BindView(R.id.txt_person_search_condition_field_management)
    TextView txtPersonSearchConditionFieldManagement;
    @BindView(R.id.ll_person_search_condition_field_management)
    LinearLayout llPersonSearchConditionFieldManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_personal_search_condition);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText("人员搜索条件");
        txtTopStatusBarRight.setText("清空");

        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);
        llPersonSearchConditionRegister.setOnClickListener(this);
        llPersonSearchConditionTitle.setOnClickListener(this);
        llPersonSearchConditionFieldManagement.setOnClickListener(this);
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
            case R.id.txt_top_status_bar_right://取消
                break;
            case R.id.ll_person_search_condition_register:
                Intent intent = new Intent(this, PersonalSettingActivity.class);
                intent.putExtra("title", "注册类人员");
                startActivityForResult(intent, RequestCode.PERSONAL_SETTING_REGISTER);
                break;
            case R.id.ll_person_search_condition_title:
                break;
            case R.id.ll_person_search_condition_field_management:
                break;
        }
    }
}
