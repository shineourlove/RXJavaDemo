package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterprisePerformanceSettingActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_enterprise_performance_setting_start)
    TextView txtEnterprisePerformanceSettingStart;
    @BindView(R.id.txt_enterprise_performance_setting_end)
    TextView txtEnterprisePerformanceSettingEnd;
    @BindView(R.id.txt_enterprise_performance_setting_use)
    TextView txtEnterprisePerformanceSettingUse;
    @BindView(R.id.ll_enterprise_performance_setting_use)
    LinearLayout llEnterprisePerformanceSettingUse;
    @BindView(R.id.edt_enterprise_performance_setting_scale)
    EditText edtEnterprisePerformanceSettingScale;
    @BindView(R.id.txt_enterprise_performance_setting_unit)
    TextView txtEnterprisePerformanceSettingUnit;
    @BindView(R.id.btn_enterprise_performance_setting_confirm)
    Button btnEnterprisePerformanceSettingConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_enterprise_performance_setting);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText("企业业绩");
        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        initInfo();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_top_status_bar_left:
                break;
            case R.id.img_top_status_bar_back:
                break;
            case R.id.ll_enterprise_performance_setting_use:
                break;
            case R.id.btn_enterprise_performance_setting_confirm:
                break;
        }
    }

    public void initInfo() {
        txtEnterprisePerformanceSettingStart.setText("开始时间");
        txtEnterprisePerformanceSettingEnd.setText("结束时间");
        txtEnterprisePerformanceSettingUse.setText("工程用途");
        txtEnterprisePerformanceSettingUnit.setText("选择单位");

        llEnterprisePerformanceSettingUse.setOnClickListener(this);
        btnEnterprisePerformanceSettingConfirm.setOnClickListener(this);
        edtEnterprisePerformanceSettingScale.addTextChangedListener(textWatcher);
    }

    /**
     * 监听输入框输入内容的变化
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("enterprise_performance",
                    "onTextChanged: " + "CharSequence:" + s + "   start:" + start + "   before" + before + "   count:" + count);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i("enterprise_performance",
                    "onTextChanged: " + "beforeTextChanged:" + s + "   start:" + start + "   count" + count + "   after:" + after);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.i("enterprise_performance",
                    "onTextChanged: " + "afterTextChanged:" + s.toString());
        }
    };
}
