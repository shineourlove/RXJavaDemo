package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.IntegralDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralDetailsActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_integral_details_integral)
    TextView txtIntegralDetailsIntegral;
    @BindView(R.id.rcv_integral_details_list)
    RecyclerView rcvIntegralDetailsList;
    IntegralDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_integral_details);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.integral_gain_details);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtIntegralDetailsIntegral.setText("1,111");

        initDetailsList();
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

    protected void initDetailsList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("+" + i);
        }
        adapter = new IntegralDetailsAdapter(list, this);
        rcvIntegralDetailsList.setLayoutManager(new LinearLayoutManager(this));
        rcvIntegralDetailsList.setAdapter(adapter);
        setHeaderView(rcvIntegralDetailsList, "1,111");
    }

    private void setHeaderView(RecyclerView view, String integral) {
        View header = LayoutInflater.from(this).inflate(R.layout.integral_details_header, view, false);
        TextView txtIntegral = header.findViewById(R.id.txt_integral_details_integral);
        txtIntegral.setText(integral);
        adapter.setHeaderView(header);
    }
}
