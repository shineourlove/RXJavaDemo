package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDataActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.ll_person_data_head_portrait)
    LinearLayout llPersonDataHeadPortrait;
    @BindView(R.id.ll_person_data_name)
    LinearLayout llPersonDataName;
    @BindView(R.id.ll_person_data_gender)
    LinearLayout llPersonDataGender;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_person_data);
        ButterKnife.bind(this);
        initTitle();
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
            case R.id.ll_person_data_head_portrait:
                Toast.makeText(this, "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_person_data_name:
                Toast.makeText(this, "姓名", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_person_data_gender:
                Toast.makeText(this, "性别", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    public void initTitle() {
        txtTopStatusBarTitle.setText(R.string.person_data);
        llPersonDataHeadPortrait.setOnClickListener(this);
        llPersonDataName.setOnClickListener(this);
        llPersonDataGender.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
    }
}
