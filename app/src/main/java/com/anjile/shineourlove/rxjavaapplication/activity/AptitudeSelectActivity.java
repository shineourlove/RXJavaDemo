package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.AptitudeSelectAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.InitialAdapter;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.AptitudeInitialControl;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.view.TopMiddlePopup;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AptitudeSelectActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_aptitude_select_classify)
    TextView txtAptitudeSelectClassify;
    @BindView(R.id.txt_aptitude_select_search)
    EditText txtAptitudeSelectSearch;
    @BindView(R.id.sv_aptitude_select_item)
    SwipeMenuRecyclerView svAptitudeSelectItem;
    @BindView(R.id.rcv_aptitude_select_iintial)
    RecyclerView rcvAptitudeSelectIintial;

    private ArrayList<String> classifyItems;
    private AptitudeAllDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_aptitude_select);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        txtTopStatusBarTitle.setText("选择资质");

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtAptitudeSelectClassify.setOnClickListener(this);

        txtAptitudeSelectClassify.setText("这是测试的分类");
        queryTest();
        getScreenPixels();
        initInitial();
        getItemsName();
        initInputListener();
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
            case R.id.txt_aptitude_select_classify:
                setPopup(1);
                middlePopup.show(txtAptitudeSelectClassify);
                break;
        }
    }

    public void queryTest() {

    }

    private TopMiddlePopup middlePopup;

    /**
     * 设置弹窗
     *
     * @param type
     */
    private void setPopup(int type) {
        middlePopup = new TopMiddlePopup(AptitudeSelectActivity.this, screenW, screenH,
                onItemClickListener, classifyItems, type);
    }

    /**
     * 设置弹窗内容
     */
    private void getItemsName() {
        classifyItems = new ArrayList<>();
        dao = new AptitudeAllDao(this);
        List<AptitudeAllBean> list = dao.queryDistinct("aptitude_type");
        for (int i = 0; i < list.size(); i++) {
            classifyItems.add(list.get(i).getAptitude_type());
        }
        txtAptitudeSelectClassify.setText(classifyItems.get(0));
        loadQueryIndex();
    }

    /**
     * 弹窗点击事件
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            txtAptitudeSelectClassify.setText(classifyItems.get(position));
            transClassify(classifyItems.get(position).trim());
            middlePopup.dismiss();
        }
    };

    public static int screenW, screenH;

    /**
     * 获取屏幕的宽和高
     */
    public void getScreenPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels;
    }

    List<AptitudeAllBean> list;
    AptitudeSelectAdapter selectAdapter;
    LinearLayoutManager layoutManager;

    public void loadQueryIndex() {
        list = dao.queryDistinct("aptitude_type", txtAptitudeSelectClassify.getText().toString().trim(), txtAptitudeSelectSearch.getText().toString().trim());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setInitial(dao.queryFirst("aptitude_name", list.get(i).getAptitude_name()).getInitial());
        }
        selectAdapter = new AptitudeSelectAdapter(list, this, this);
        layoutManager = new LinearLayoutManager(this);
        svAptitudeSelectItem.setLayoutManager(layoutManager);
        svAptitudeSelectItem.setAdapter(selectAdapter);
        transInitial();
    }

    public void transClassify(String name) {
        List<AptitudeAllBean> newList = dao.queryDistinct("aptitude_type", name, txtAptitudeSelectSearch.getText().toString().trim());
        list.clear();
        for (int i = 0; i < newList.size(); i++) {
            list.add(newList.get(i));
            list.get(i).setInitial(dao.queryFirst("aptitude_name", list.get(i).getAptitude_name()).getInitial());
        }
        Log.i("aptitude_select_act", "transClassify: " + list.size());
        selectAdapter.notifyDataSetChanged();
        transInitial();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCode.APTITUDE_SELECT_ACTIVITY_REQUEST:
                selectAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 输入框的监听
     */
    public void initInputListener() {
        txtAptitudeSelectSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                transSearch(editable.toString().trim());
            }
        });
    }

    public void transSearch(String input) {
        List<AptitudeAllBean> newList = dao.queryDistinct("aptitude_type", txtAptitudeSelectClassify.getText().toString(), input);
        list.clear();
        for (int i = 0; i < newList.size(); i++) {
            list.add(newList.get(i));
            list.get(i).setInitial(dao.queryFirst("aptitude_name", list.get(i).getAptitude_name()).getInitial());
        }
        Log.i("aptitude_select_act", "transClassify: " + list.size());
        selectAdapter.notifyDataSetChanged();
        transInitial();
    }

    List<String> listInitial;
    InitialAdapter initialAdapter;

    public void initInitial() {
        listInitial = new ArrayList<>();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        initialAdapter = new InitialAdapter(listInitial, this, this, metrics.heightPixels);
        rcvAptitudeSelectIintial.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvAptitudeSelectIintial.setAdapter(initialAdapter);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeInitial(AptitudeInitialControl c) {
        Log.i("aptitude_select_act", "changeInitial: " + c.getType());
        String initial = listInitial.get(c.getType());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getInitial().equals(initial)) {
                layoutManager.scrollToPositionWithOffset(i, 0);
                layoutManager.setStackFromEnd(true);
            }
        }
    }
}
