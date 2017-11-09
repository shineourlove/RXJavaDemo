package com.anjile.shineourlove.rxjavaapplication.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.ProjectSelectAdapter;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingBean;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingDao;
import com.anjile.shineourlove.rxjavaapplication.db.PurposeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PurposeAllDao;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.entity.ProjectSelectEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.PurposeEntity;
import com.anjile.shineourlove.rxjavaapplication.popup.MyPopupWindow;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.rcy_project_select_options)
    RecyclerView rcyProjectSelectOptions;
    List<PurposeAllBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_project_select);
        ButterKnife.bind(this);
        initTitle();
        initAdapter();
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
            case R.id.txt_top_status_bar_right:
                savePurpose();
                break;
        }
    }

    public void initTitle() {
        txtTopStatusBarTitle.setText("工程用途");
        txtTopStatusBarRight.setText("确定");

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);
    }

    public void initAdapter() {
        list = new PurposeAllDao(this).query();
        final ProjectSelectAdapter selectAdapter = new ProjectSelectAdapter(list, this);
        rcyProjectSelectOptions.setLayoutManager(new LinearLayoutManager(this));
        rcyProjectSelectOptions.setAdapter(selectAdapter);
        EnterprisePerformanceSettingDao performanceSettingDao = new EnterprisePerformanceSettingDao(this);
        List<EnterprisePerformanceSettingBean> queryList = performanceSettingDao.query();
        for (int i = 0; i < list.size(); i++) {
            if (queryList.size() > 0 && queryList.get(0).getUse() != null)
                if (queryList.get(0).getUse().contains(list.get(i).getId() + "")) {
                    list.get(i).setCheck(true);
                } else {
                    list.get(i).setCheck(false);
                }
        }
        selectAdapter.notifyDataSetChanged();
        selectAdapter.setOnItemClickListener(new ProjectSelectAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectAdapter.notifyDataSetChanged();
                Log.i("project_select", "onItemClick: " + list.get(position).getCheck());
            }
        });
    }

    public void savePurpose() {
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        settingDao.clearAll();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheck()) {
                builder.append("," + list.get(i).getId());
            }
        }
        settingDao.updateOnly("use", builder.toString().replaceFirst(",", ""));
        finish();
    }
}
