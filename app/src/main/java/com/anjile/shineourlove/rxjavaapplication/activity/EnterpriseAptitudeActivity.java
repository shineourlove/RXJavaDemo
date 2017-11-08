package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.AptitudeSelectedAdapter;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.AptitudeBackControl;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.EnterpriseAptitudeAddControl;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterpriseAptitudeActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.sv_enterprise_aptitude_list)
    SwipeMenuRecyclerView svEnterpriseAptitudeList;
    @BindView(R.id.txt_enterprise_aptitude_add)
    TextView txtEnterpriseAptitudeAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_enterprise_aptitude);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        txtTopStatusBarTitle.setText("企业资质");
        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);

        txtEnterpriseAptitudeAdd.setText("添加");
        txtEnterpriseAptitudeAdd.setOnClickListener(this);
        initSelectedList();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_top_status_bar_left:
                backToQuery();
                break;
            case R.id.img_top_status_bar_back:
                backToQuery();
                break;
            case R.id.txt_enterprise_aptitude_add:
                if (getIntent().getIntExtra("type", 1) == 0) {
                    Intent intent = new Intent(this, AptitudeSelectActivity.class);
                    startActivity(intent);
                    backToQuery();
                } else {
                    backToQuery();
                }
                break;
        }
    }

    public void initSelectedList() {
        AptitudeSelectedDao dao = new AptitudeSelectedDao(this);
        List<AptitudeSelectedBean> list = dao.query();
        EventBus.getDefault().post(new EnterpriseAptitudeAddControl(list.size()));
        svEnterpriseAptitudeList.setLayoutManager(new LinearLayoutManager(this));
        AptitudeSelectedAdapter selectedAdapter = new AptitudeSelectedAdapter(list, this);
        svEnterpriseAptitudeList.setAdapter(selectedAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addTextControl(EnterpriseAptitudeAddControl c) {
        if (c.getSize() == 0) {
            txtEnterpriseAptitudeAdd.setText("添加");
        } else {
            txtEnterpriseAptitudeAdd.setText("继续添加");
        }
    }

    public void backToQuery() {
        EventBus.getDefault().post(new AptitudeBackControl(0));
        finish();
    }

    /**
     * 处理返回键的事件
     *
     * @param keyCode 事件类型
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                backToQuery();
                break;
            case KeyEvent.KEYCODE_MENU:
                break;

        }
        return super.onKeyDown(keyCode, event);
    }
}
