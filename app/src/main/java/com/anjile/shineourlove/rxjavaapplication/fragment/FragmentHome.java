package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.holder.NetImageHolder;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/10.
 */

public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener {
    ConvenientBanner banner;
    ArrayList<String> photos;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        banner = view.findViewById(R.id.cb_fragment_home);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCB();
    }

    public void initCB() {
        photos = new ArrayList<>();
        photos.add("http://pic133.nipic.com/file/20170624/9448607_155821950000_2.jpg");
        photos.add("http://pic135.nipic.com/file/20170709/9448607_203036152000_2.jpg");
        photos.add("http://pic138.nipic.com/file/20170815/9311975_142635215032_2.jpg");
        banner.setPages(new CBViewHolderCreator<NetImageHolder>() {

            @Override
            public NetImageHolder createHolder() {
                return new NetImageHolder();
            }
        }, photos);
        banner.setOnItemClickListener(this);
        banner.setOnPageChangeListener(this);
        banner.startTurning(5000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemClick(int position) {

    }
}
