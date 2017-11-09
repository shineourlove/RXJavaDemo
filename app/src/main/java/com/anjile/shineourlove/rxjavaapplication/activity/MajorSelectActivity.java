package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.InitialAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.PersonalMajorAdapter;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllDao;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.view.FeedbackEditText;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MajorSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.edt_major_select_condition)
    FeedbackEditText edtMajorSelectCondition;
    @BindView(R.id.rcv_major_select_list)
    RecyclerView rcvMajorSelectList;
    @BindView(R.id.rcv_major_select_initial)
    RecyclerView rcvMajorSelectInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_major_select);
        ButterKnife.bind(this);
        txtTopStatusBarTitle.setText(getIntent().getStringExtra("type"));

        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);

        initAdapter();
        initInitial();
        initListener();
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

    PersonalMajorAdapter majorAdapter;
    List<PersonalAllBean> list;
    List<String> listInitial;
    InitialAdapter initialAdapter;

    public void initAdapter() {
        PersonalAllDao dao = new PersonalAllDao(this);
        list = dao.queryWhere("type_name", getIntent().getStringExtra("type"));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setInitial(dao.queryFirst("type_name", list.get(i).getType_name()).getInitial());
        }
        majorAdapter = new PersonalMajorAdapter(list, this, this);
        rcvMajorSelectList.setLayoutManager(new LinearLayoutManager(this));
        rcvMajorSelectList.setAdapter(majorAdapter);
    }

    public void initListener(){
        edtMajorSelectCondition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void initInitial() {
        listInitial = new ArrayList<>();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        initialAdapter = new InitialAdapter(listInitial, this, this, metrics.heightPixels);
        rcvMajorSelectInitial.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvMajorSelectInitial.setAdapter(initialAdapter);
    }

    public void transInitial() {
        listInitial.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                if (!list.get(i).getInitial().equals(list.get(i - 1).getInitial())) {
                    listInitial.add(list.get(i).getInitial());
                }
            } else {
                listInitial.add(list.get(0).getInitial());
            }
        }
        initialAdapter.notifyDataSetChanged();
    }
}
