package com.anjile.shineourlove.rxjavaapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/9/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //禁用横屏
        ButterKnife.bind(this);
        initBasic();
    }

    @Override
    public void onClick(View view) {
        viewClick(view);
    }

    public abstract void viewClick(View v);

    public abstract void initBasic();
}
