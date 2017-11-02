package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.HotSpotAdapter;
import com.anjile.shineourlove.rxjavaapplication.entity.ProjectSelectEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotSpotActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.sv_hot_spot_details)
    RecyclerView svHotSpotDetails;
    HotSpotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_hot_spot);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.new_hot_spot);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);

        initHotSpotList();
    }

    @Override
    public void viewClick(View v) {

    }

    protected void initHotSpotList() {
        ArrayList<ProjectSelectEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ProjectSelectEntity("建筑业寒冬", false));
        }
        adapter = new HotSpotAdapter(list, this);
        svHotSpotDetails.setLayoutManager(new LinearLayoutManager(this));
        svHotSpotDetails.setAdapter(adapter);
    }
}
