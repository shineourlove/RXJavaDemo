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
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleDao;

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
        loadLocalData();
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
                clearData();
                break;
            case R.id.ll_person_search_condition_register:
                Intent intentR = new Intent(this, PersonalSettingActivity.class);
                intentR.putExtra("title", "注册类人员");
                startActivityForResult(intentR, RequestCode.PERSONAL_SETTING_REGISTER);
                break;
            case R.id.ll_person_search_condition_title:
                Intent intentT = new Intent(this, PersonalSettingActivity.class);
                intentT.putExtra("title", "职称类人员");
                startActivityForResult(intentT, RequestCode.PERSONAL_SETTING_TITLE);
                break;
            case R.id.ll_person_search_condition_field_management:
                Intent intentM = new Intent(this, PersonalSettingActivity.class);
                intentM.putExtra("title", "现场管理人员");
                startActivityForResult(intentM, RequestCode.PERSONAL_SETTING_MANAGER);
                break;
        }
    }

    public void loadLocalData() {
        PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
        PersonalTitleDao titleDao = new PersonalTitleDao(this);
        PersonalManagerDao managerDao = new PersonalManagerDao(this);
        if (registerDao.query() != null && registerDao.query().size() > 0) {
            txtPersonSearchConditionRegister.setText("已选择" + registerDao.query().size() + "项注册人员要求");
        } else {
            txtPersonSearchConditionRegister.setText("");
        }
        if (titleDao.query() != null && titleDao.query().size() > 0) {
            txtPersonSearchConditionTitle.setText("已选择" + titleDao.query().size() + "项注册人员要求");
        } else {
            txtPersonSearchConditionTitle.setText("");
        }
        if (managerDao.query() != null && managerDao.query().size() > 0) {
            txtPersonSearchConditionFieldManagement.setText("已选择" + managerDao.query().size() + "项注册人员要求");
        } else {
            txtPersonSearchConditionFieldManagement.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCode.PERSONAL_SETTING_REGISTER:
                loadLocalData();
                break;
            case RequestCode.PERSONAL_SETTING_TITLE:
                loadLocalData();
                break;
            case RequestCode.PERSONAL_SETTING_MANAGER:
                loadLocalData();
                break;
        }
    }

    public void clearData() {
        new PersonalRegisterDao(this).clearAll();
        new PersonalTitleDao(this).clearAll();
        new PersonalManagerDao(this).clearAll();
        loadLocalData();
    }
}
