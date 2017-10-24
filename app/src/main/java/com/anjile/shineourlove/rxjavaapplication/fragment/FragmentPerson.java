package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class FragmentPerson extends Fragment implements View.OnClickListener {
    TextView txtName;
    LinearLayout llForm, llCollect, llShow, llCooperator, llInvite, llSetting, llFeedback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_layout, container, false);
        txtName = view.findViewById(R.id.txt_fragment_person_name);
        llForm = view.findViewById(R.id.ll_fragment_home_order_form);
        llCollect = view.findViewById(R.id.ll_fragment_home_collect);
        llShow = view.findViewById(R.id.ll_fragment_home_show);
        llCooperator = view.findViewById(R.id.ll_fragment_home_cooperator);
        llInvite = view.findViewById(R.id.ll_fragment_home_invite);
        llSetting = view.findViewById(R.id.ll_fragment_setting);
        llFeedback = view.findViewById(R.id.ll_fragment_home_feed_back);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtName.setText("甜蜜蜜");
        llForm.setOnClickListener(this);
        llCollect.setOnClickListener(this);
        llShow.setOnClickListener(this);
        llCooperator.setOnClickListener(this);
        llInvite.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        llFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_fragment_home_order_form:
                Toast.makeText(getContext(), "我的订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_home_collect:
                Toast.makeText(getContext(), "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_home_show:
                Toast.makeText(getContext(), "企业展示", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_home_cooperator:
                Toast.makeText(getContext(), "合作单位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_home_invite:
                Toast.makeText(getContext(), "邀请朋友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_setting:
                Toast.makeText(getContext(), "账号设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fragment_home_feed_back:
                Toast.makeText(getContext(), "意见反馈", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
