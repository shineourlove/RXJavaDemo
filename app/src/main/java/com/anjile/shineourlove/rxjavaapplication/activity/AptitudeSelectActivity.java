package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.view.TopMiddlePopup;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
    @BindView(R.id.sv_aptitude_select_initial)
    RecyclerView svAptitudeSelectInitial;

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

        txtTopStatusBarTitle.setText("选择资质");

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtAptitudeSelectClassify.setOnClickListener(this);

        txtAptitudeSelectClassify.setText("这是测试的分类");
        queryTest();
        getScreenPixels();
        getItemsName();
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
     *
     * @return
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

    public void loadQueryIndex() {
        List<AptitudeAllBean> list = dao.queryWhere("aptitude_type", txtAptitudeSelectClassify.getText().toString().trim());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setInitial(dao.queryFirst("aptitude_name",list.get(i).getAptitude_name()).getInitial());
        }
        AptitudeSelectAdapter selectAdapter = new AptitudeSelectAdapter(list,this);
        svAptitudeSelectItem.setLayoutManager(new LinearLayoutManager(this));
        svAptitudeSelectItem.setAdapter(selectAdapter);
    }
}
