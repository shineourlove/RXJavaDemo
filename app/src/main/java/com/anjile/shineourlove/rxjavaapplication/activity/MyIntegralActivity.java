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

public class MyIntegralActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_my_integral_enable_integral)
    TextView txtMyIntegralEnableIntegral;
    @BindView(R.id.ll_my_integral_member_information)
    LinearLayout llMyIntegralMemberInformation;
    @BindView(R.id.ll_my_integral_gain_integral)
    LinearLayout llMyIntegralGainIntegral;
    @BindView(R.id.ll_my_integral_consume_record)
    LinearLayout llMyIntegralConsumeRecord;
    @BindView(R.id.ll_my_integral_integral_rule)
    LinearLayout llMyIntegralIntegralRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_my_integral);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.my_integral);
        txtMyIntegralEnableIntegral.setText("0");

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        llMyIntegralMemberInformation.setOnClickListener(this);
        llMyIntegralGainIntegral.setOnClickListener(this);
        llMyIntegralConsumeRecord.setOnClickListener(this);
        llMyIntegralIntegralRule.setOnClickListener(this);
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
            case R.id.ll_my_integral_member_information:
                Toast.makeText(this, "会员信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_my_integral_gain_integral:
                Toast.makeText(this, "获取积分", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_my_integral_consume_record:
                Toast.makeText(this, "消费记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_my_integral_integral_rule:
                Toast.makeText(this, "积分规则", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
