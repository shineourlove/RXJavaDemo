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
import com.anjile.shineourlove.rxjavaapplication.adapter.ProjectSelectAdapter;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.entity.ProjectSelectEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    ArrayList<ProjectSelectEntity> list;

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

    }

    public void initTitle() {
        imgTopStatusBarBack.setImageResource(R.drawable.ssdk_oks_classic_instapaper);
        txtTopStatusBarLeft.setText("");
        txtTopStatusBarTitle.setText("工程用途");
        txtTopStatusBarRight.setText("确定");
    }

    public void initAdapter() {
        list = new ArrayList<>();
        final ProjectSelectAdapter selectAdapter = new ProjectSelectAdapter(list, this);
        rcyProjectSelectOptions.setLayoutManager(new LinearLayoutManager(this));
        rcyProjectSelectOptions.setAdapter(selectAdapter);
        for (int i = 0; i < 10; i++) {
            list.add(new ProjectSelectEntity("索尼大法好", true));
        }
        selectAdapter.notifyDataSetChanged();
        selectAdapter.setOnItemClickListener(new ProjectSelectAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectAdapter.notifyDataSetChanged();
                Log.i("project_select", "onItemClick: " + list.get(position).isCheck());
            }
        });
    }
}
