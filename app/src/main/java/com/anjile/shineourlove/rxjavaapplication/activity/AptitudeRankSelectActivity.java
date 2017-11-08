package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.AptitudeSelectItemAdapter;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AptitudeRankSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.rcv_aptitude_rank_select_list)
    RecyclerView rcvAptitudeRankSelectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_aptitude_rank_select);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(getIntent().getStringExtra("aptitude_name"));
        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        initRankList();
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
        }
    }

    public void initRankList() {
        AptitudeAllDao dao = new AptitudeAllDao(this);
        List<AptitudeAllBean> list =  dao.queryWhere("aptitude_name", getIntent().getStringExtra("aptitude_name"));
        AptitudeSelectItemAdapter selectItemAdapter = new AptitudeSelectItemAdapter(list,this,this);
        rcvAptitudeRankSelectList.setLayoutManager(new LinearLayoutManager(this));
        rcvAptitudeRankSelectList.setAdapter(selectItemAdapter);
    }
}
