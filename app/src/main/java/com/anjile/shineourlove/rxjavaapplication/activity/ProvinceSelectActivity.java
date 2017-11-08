package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.ProvinceSelectedAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProvinceSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.rcv_province_select_list)
    RecyclerView rcvProvinceSelectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_province_select);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText("选择省市");
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        loadProvinceList();
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
        }
    }

    public void loadProvinceList() {
        ArrayList<String> list = new ArrayList<>();
        String[] arr = getResources().getStringArray(R.array.chinese_province);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        ProvinceSelectedAdapter selectedAdapter = new ProvinceSelectedAdapter(list, this, this);
        rcvProvinceSelectList.setLayoutManager(new LinearLayoutManager(this));
        rcvProvinceSelectList.setAdapter(selectedAdapter);
    }
}
