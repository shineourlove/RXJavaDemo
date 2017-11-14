package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;

import butterknife.ButterKnife;

public class StartPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_start_page);
        ButterKnife.bind(this);

        UserInfoDao infoDao = new UserInfoDao(this);
        if (infoDao.query() == null || infoDao.query().size() == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartPageActivity.this, AccountLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartPageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }
    }

    @Override
    public void viewClick(View v) {

    }
}
