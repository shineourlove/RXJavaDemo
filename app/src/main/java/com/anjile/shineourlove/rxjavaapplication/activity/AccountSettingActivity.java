package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountSettingActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.ll_account_setting_phone)
    LinearLayout llAccountSettingPhone;
    @BindView(R.id.ll_account_setting_push_setting)
    LinearLayout llAccountSettingPushSetting;
    @BindView(R.id.ll_account_setting_check_update)
    LinearLayout llAccountSettingCheckUpdate;
    @BindView(R.id.ll_account_setting_clear_cache)
    LinearLayout llAccountSettingClearCache;
    @BindView(R.id.ll_account_setting_about_us)
    LinearLayout llAccountSettingAboutUs;
    @BindView(R.id.ll_account_setting_log_out)
    LinearLayout llAccountSettingLogOut;
    @BindView(R.id.txt_account_setting_cache)
    TextView txtAccountSettingCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.account_setting);

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        llAccountSettingPhone.setOnClickListener(this);
        llAccountSettingPushSetting.setOnClickListener(this);
        llAccountSettingCheckUpdate.setOnClickListener(this);
        llAccountSettingClearCache.setOnClickListener(this);
        llAccountSettingAboutUs.setOnClickListener(this);
        llAccountSettingLogOut.setOnClickListener(this);

        txtAccountSettingCache.setText("0B");
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
            case R.id.ll_account_setting_phone:
                break;
            case R.id.ll_account_setting_push_setting:
                break;
            case R.id.ll_account_setting_check_update:
                break;
            case R.id.ll_account_setting_clear_cache:
                break;
            case R.id.ll_account_setting_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.ll_account_setting_log_out:
                break;
        }
    }
}
