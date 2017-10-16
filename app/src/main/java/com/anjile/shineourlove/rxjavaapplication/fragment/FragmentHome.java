package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.ArchitectureRecycleAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.DetailsPagerAdapter;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.holder.NetImageHolder;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.utils.DividerItemDecoration;
import com.anjile.shineourlove.rxjavaapplication.view.AutoFitViewpager;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/10.
 */

public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener, View.OnClickListener {
    ConvenientBanner banner;
    ArrayList<String> photos;
    TextView txtHot1, txtHot2;
    TextView txtArea;
    RecyclerView rcvCompany;//已注释
    AutoFitViewpager pagerDetails;
    TextView txtCompany, txtCompanyLine, txtInfo, txtInfoLine, txtBuilder, txtBuilderLine;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        banner = view.findViewById(R.id.cb_fragment_home);
        txtHot1 = view.findViewById(R.id.txt_fragment_home_hot_one);
        txtHot2 = view.findViewById(R.id.txt_fragment_home_hot_two);
        txtArea = view.findViewById(R.id.txt_fragment_home_area);
        //rcvCompany = view.findViewById(R.id.rcv_fragment_home_details);
        pagerDetails = view.findViewById(R.id.vp_fragment_home_details);
        txtCompany = view.findViewById(R.id.txt_fragment_home_company);
        txtCompanyLine = view.findViewById(R.id.txt_fragment_home_company_line);
        txtInfo = view.findViewById(R.id.txt_fragment_home_info);
        txtInfoLine = view.findViewById(R.id.txt_fragment_home_info_line);
        txtBuilder = view.findViewById(R.id.txt_fragment_home_builder);
        txtBuilderLine = view.findViewById(R.id.txt_fragment_home_builder_line);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCB();
        initHotPoint();
        //initArchitecture();
        initPager(savedInstanceState);
        initDetails();
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

    public void initHotPoint() {
        txtHot1.setText("市政公用工程施工总承包三级资质申请通过审查");
        txtHot2.setText("建筑装修装饰工程专业承包二级资质申请通过审查");
        txtArea.setText("重庆");
    }

    public void initArchitecture() {
        rcvCompany.setFocusable(false);
        ArrayList<CompanyDetails> companyDetailses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            companyDetailses.add(new CompanyDetails("红旗渠建设集团有限公司", 1506787200000l + 24 * 60 * 60 * 1000 * i, 1, 2, 3));
        }
        ArchitectureRecycleAdapter architectureAdapter = new ArchitectureRecycleAdapter(companyDetailses, getContext());
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getContext());
        rcvCompany.setNestedScrollingEnabled(false);
        rcvCompany.setLayoutManager(linearLayoutManager);
        rcvCompany.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rcvCompany.setAdapter(architectureAdapter);
        architectureAdapter.notifyDataSetChanged();
        architectureAdapter.setOnItemClickListener(new ArchitectureRecycleAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("recycle_view_click", "onItemClick: " + position);
            }
        });
    }

    public void initPager(Bundle savedInstanceState) {
        LayoutInflater lf = getLayoutInflater(savedInstanceState);
        View view1, view2, view3;
        view1 = lf.inflate(R.layout.details_pager_layout, null);
        view2 = lf.inflate(R.layout.details_pager_layout, null);
        view3 = lf.inflate(R.layout.details_pager_layout, null);

        ArrayList<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        DetailsPagerAdapter adapter = new DetailsPagerAdapter(viewList);
        pagerDetails.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pagerDetails.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("view_pager_select", "onPageSelected: " + position);
                switch (position) {
                    case 0:
                        clickCompany();
                        break;
                    case 1:
                        clickInfo();
                        break;
                    case 2:
                        clickBuilder();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initDetails() {
        txtCompany.setOnClickListener(this);
        txtCompanyLine.setOnClickListener(this);
        txtInfo.setOnClickListener(this);
        txtInfoLine.setOnClickListener(this);
        txtBuilder.setOnClickListener(this);
        txtBuilderLine.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        txtCompany.setTextColor(getResources().getColor(R.color.blue_word));
        txtInfo.setTextColor(getResources().getColor(R.color.gray_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.gray_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.white));
        pagerDetails.setCurrentItem(0);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_fragment_home_company:
                clickCompany();
                break;
            case R.id.txt_fragment_home_info:
                clickInfo();
                break;
            case R.id.txt_fragment_home_builder:
                clickBuilder();
                break;
        }
    }

    public void clickCompany() {
        txtCompany.setTextColor(getResources().getColor(R.color.blue_word));
        txtInfo.setTextColor(getResources().getColor(R.color.gray_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.gray_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.white));
        pagerDetails.setCurrentItem(0);
    }

    public void clickInfo() {
        txtCompany.setTextColor(getResources().getColor(R.color.gray_word));
        txtInfo.setTextColor(getResources().getColor(R.color.blue_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.gray_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.white));
        pagerDetails.setCurrentItem(1);
    }

    public void clickBuilder() {
        txtCompany.setTextColor(getResources().getColor(R.color.gray_word));
        txtInfo.setTextColor(getResources().getColor(R.color.gray_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.blue_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        pagerDetails.setCurrentItem(2);
    }
}
