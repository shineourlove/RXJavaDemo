package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.PersonalSettingAdapter;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterDao;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalSettingActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.rcv_personal_setting_list)
    RecyclerView rcvPersonalSettingList;
    @BindView(R.id.btn_personal_setting_add)
    Button btnPersonalSettingAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_personal_setting);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(getIntent().getStringExtra("title"));
        txtTopStatusBarRight.setText(R.string.confirm);

        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);

        btnPersonalSettingAdd.setOnClickListener(this);
        if (getIntent().getStringExtra("title").equals("注册类人员")) {
            initRegisterList();
        } else if (getIntent().getStringExtra("title").equals("职称类人员")) {

        } else {

        }
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
            case R.id.txt_top_status_bar_right:
                break;
            case R.id.btn_personal_setting_add:
                break;
        }
    }

    PersonalSettingAdapter adapter;
    List<PersonalRegisterBean> registerBeanList;

    public void initRegisterList() {
        PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
        registerBeanList = registerDao.query();
        if (registerBeanList.size() == 0) {
            registerBeanList.add(new PersonalRegisterBean());
        }
        adapter = new PersonalSettingAdapter(registerBeanList, this, this);
        rcvPersonalSettingList.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvPersonalSettingList.setAdapter(adapter);
    }
}
