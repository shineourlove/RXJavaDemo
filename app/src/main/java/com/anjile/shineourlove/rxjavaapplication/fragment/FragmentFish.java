package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.FishViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/13.
 */

public class FragmentFish extends Fragment {
    TextView txtF1;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fish_layout, container, false);
        txtF1 = view.findViewById(R.id.txt_fragment_one);
        viewPager = view.findViewById(R.id.view_pager_fragment_one);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtF1.setText("加载的第一个fragmentFish");
        EventBus.getDefault().post("去吧皮卡丘!");
        initBasic(savedInstanceState);
    }

    public void initBasic(Bundle savedInstanceState) {
        LayoutInflater lf = getLayoutInflater(savedInstanceState);
        View view1, view2, view3;
        view1 = lf.inflate(R.layout.fish_view_pager_layout, null);
        view2 = lf.inflate(R.layout.fish_view_pager_layout, null);
        view3 = lf.inflate(R.layout.fish_view_pager_layout, null);

        ArrayList<View> viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        ArrayList<String> photos = new ArrayList<>();
        photos.add("http://pic133.nipic.com/file/20170624/9448607_155821950000_2.jpg");
        photos.add("http://pic135.nipic.com/file/20170709/9448607_203036152000_2.jpg");
        photos.add("http://pic138.nipic.com/file/20170815/9311975_142635215032_2.jpg");
        ArrayList<String> hints = new ArrayList<>();
        hints.add("这是第一个item");
        hints.add("这是第二个item");
        hints.add("这是第三个item");
        FishViewPagerAdapter pagerAdapter = new FishViewPagerAdapter(viewList, photos, hints);
        viewPager.setAdapter(pagerAdapter);
    }
}
