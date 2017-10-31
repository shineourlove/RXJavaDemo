package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenVIPActivityActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.ll_open_vip_1year)
    LinearLayout llOpenVip1year;
    @BindView(R.id.ll_open_vip_2year)
    LinearLayout llOpenVip2year;
    @BindView(R.id.ll_open_vip_3year)
    LinearLayout llOpenVip3year;
    @BindView(R.id.ll_open_vip_4year)
    LinearLayout llOpenVip4year;
    @BindView(R.id.ll_open_vip_5year)
    LinearLayout llOpenVip5year;
    @BindView(R.id.btn_open_vip_hot_line)
    Button btnOpenVipHotLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_open_activity);
        ButterKnife.bind(this);
        initTitle();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.ll_open_vip_1year:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_open_vip_2year:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_open_vip_3year:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_open_vip_4year:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_open_vip_5year:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_open_vip_hot_line:
                Toast.makeText(this, "请拨打热线电话，按指示开通VIP", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void initTitle() {
        txtTopStatusBarTitle.setText(R.string.vip);
        llOpenVip1year.setOnClickListener(this);
        llOpenVip2year.setOnClickListener(this);
        llOpenVip3year.setOnClickListener(this);
        llOpenVip4year.setOnClickListener(this);
        llOpenVip5year.setOnClickListener(this);
        btnOpenVipHotLine.setOnClickListener(this);
    }
}
