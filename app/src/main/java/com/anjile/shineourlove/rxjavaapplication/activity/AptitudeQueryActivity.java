package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.AptitudeQueryItemAdapter;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeQueryItemEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AptitudeQueryActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.rcv_aptitude_query_details)
    RecyclerView rcvAptitudeQueryDetails;
    AptitudeQueryItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_aptitude_query);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.aptitude_query);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        initQuery();
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

    public void initQuery(){
        ArrayList<AptitudeQueryItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new AptitudeQueryItemEntity("索尼大法好",1,"十一区",true));
            list.add(new AptitudeQueryItemEntity("天灭任天堂",2,"十一区",false));
        }
        adapter = new AptitudeQueryItemAdapter(list, this);
        rcvAptitudeQueryDetails.setLayoutManager(new LinearLayoutManager(this));
        rcvAptitudeQueryDetails.setAdapter(adapter);
    }
}
