package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class FragmentFour extends Fragment {
    TextView txtTop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_layout, container, false);
        txtTop = view.findViewById(R.id.txt_fragment_four_top);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtTop.setText("这是加载的第四个fragment！");
    }
}
