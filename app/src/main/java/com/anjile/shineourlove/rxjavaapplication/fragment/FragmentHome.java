package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.FuzzySearchActivity;
import com.anjile.shineourlove.rxjavaapplication.activity.QueryActivity;
import com.anjile.shineourlove.rxjavaapplication.adapter.ArchitectureRecycleAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.DetailsPagerAdapter;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.entity.HomePagePagerEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.ProvinceEntity;
import com.anjile.shineourlove.rxjavaapplication.holder.NetImageHolder;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.manager.NetManager;
import com.anjile.shineourlove.rxjavaapplication.utils.DividerItemDecoration;
import com.anjile.shineourlove.rxjavaapplication.utils.ProvinceUtil;
import com.anjile.shineourlove.rxjavaapplication.view.AutoFitViewpager;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.anjile.shineourlove.rxjavaapplication.R.id.textView;

/**
 * Created by Administrator on 2017/10/10.
 */

public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener, View.OnClickListener {
    ConvenientBanner banner;
    ArrayList<String> photos;
    ArrayList<String> urls;
    TextView txtHot1, txtHot2;
    RecyclerView rcvCompany;//已注释
    AutoFitViewpager pagerDetails;//已注释
    TextView txtCompany, txtCompanyLine, txtInfo, txtInfoLine, txtBuilder, txtBuilderLine;
    RelativeLayout rlSearch;
    @BindView(R.id.txt_fragment_home_area)
    TextView txtFragmentHomeArea;
    @BindView(R.id.img_fragment_home_area)
    ImageView imgFragmentHomeArea;
    Unbinder unbinder;

    @BindView(R.id.cb_fragment_home)  //轮播
            ConvenientBanner cbFragmentHome;
    @BindView(R.id.ll_fragment_home_advanced_query)  //高级查询
            LinearLayout llFragmentHomeAdvancedQuery;
    @BindView(R.id.ll_fragment_home_talent_pool)  //人才库
            LinearLayout llFragmentHomeTalentPool;
    @BindView(R.id.ll_fragment_home_budget_program)  //方案预算
            LinearLayout llFragmentHomeBudgetProgram;

    @BindView(R.id.ll_fragment_home_vip)//vip
            LinearLayout llFragmentHomeVip;
    @BindView(R.id.ll_fragment_home_credit)//信誉值
            LinearLayout llFragmentHomeCredit;
    @BindView(R.id.ll_fragment_home_material_choose)//材料选购
            LinearLayout llFragmentHomeMaterialChoose;

    @BindView(R.id.txt_fragment_home_hot_one)
    TextView txtFragmentHomeHotOne;
    @BindView(R.id.txt_fragment_home_hot_two)
    TextView txtFragmentHomeHotTwo;

    @BindView(R.id.txt_fragment_home_company)
    TextView txtFragmentHomeCompany;
    @BindView(R.id.txt_fragment_home_company_line)
    TextView txtFragmentHomeCompanyLine;
    @BindView(R.id.txt_fragment_home_info)
    TextView txtFragmentHomeInfo;
    @BindView(R.id.txt_fragment_home_info_line)
    TextView txtFragmentHomeInfoLine;
    @BindView(R.id.txt_fragment_home_builder)
    TextView txtFragmentHomeBuilder;
    @BindView(R.id.txt_fragment_home_builder_line)
    TextView txtFragmentHomeBuilderLine;
    @BindView(R.id.vp_fragment_home_details)
    AutoFitViewpager vpFragmentHomeDetails;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        ButterKnife.bind(getActivity());
        banner = view.findViewById(R.id.cb_fragment_home);
        txtHot1 = view.findViewById(R.id.txt_fragment_home_hot_one);
        txtHot2 = view.findViewById(R.id.txt_fragment_home_hot_two);
        //rcvCompany = view.findViewById(R.id.rcv_fragment_home_details);
        //pagerDetails = view.findViewById(R.id.vp_fragment_home_details);
        txtCompany = view.findViewById(R.id.txt_fragment_home_company);
        txtCompanyLine = view.findViewById(R.id.txt_fragment_home_company_line);
        txtInfo = view.findViewById(R.id.txt_fragment_home_info);
        txtInfoLine = view.findViewById(R.id.txt_fragment_home_info_line);
        txtBuilder = view.findViewById(R.id.txt_fragment_home_builder);
        txtBuilderLine = view.findViewById(R.id.txt_fragment_home_builder_line);
        rlSearch = view.findViewById(R.id.rl_fragment_home_search);
        unbinder = ButterKnife.bind(this, view);
        llFragmentHomeTalentPool.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBasic();
        initCbHeight();
        initCbUrl();
        initHotPoint();
        //initArchitecture();
        //initPager(savedInstanceState);
        initDetails();
        initArea();
    }

    public void initBasic() {
        rlSearch.setOnClickListener(this);
        txtFragmentHomeArea.setOnClickListener(this);
        imgFragmentHomeArea.setOnClickListener(this);
    }

    public void initCbHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        // int height = dm.heightPixels;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) banner.getLayoutParams();
        layoutParams.height = (int) (width * 0.562);
        layoutParams.width = width;
        banner.setLayoutParams(layoutParams);
    }

    public void initCbUrl() {
        photos = new ArrayList<>();
        urls = new ArrayList<>();
        Api api = new NetManager().getApi();
        api.mainPagerObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePagePagerEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("fragment_home", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(HomePagePagerEntity value) {
                        Log.i("fragment_home", "onNext: ");
                        for (int i = 0; i < value.getData().size(); i++) {
                            photos.add(value.getData().get(i).getPicture_url());
                            urls.add(value.getData().get(i).getActivities_url());
                        }
                        initCB();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("fragment_home", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("fragment_home", "onComplete: ");
                    }
                });
    }

    public void initCB() {
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
        //pagerDetails.setCurrentItem(0);
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
            case R.id.rl_fragment_home_search:
                Intent intent = new Intent(getContext(), FuzzySearchActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_fragment_home_area:
                areaClick();
                break;
            case R.id.img_fragment_home_area:
                areaClick();
                break;
            case R.id.ll_fragment_home_talent_pool:
                Intent intentP = new Intent(getContext(), QueryActivity.class);
                startActivity(intentP);
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
        //pagerDetails.setCurrentItem(0);
    }

    public void clickInfo() {
        txtCompany.setTextColor(getResources().getColor(R.color.gray_word));
        txtInfo.setTextColor(getResources().getColor(R.color.blue_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.gray_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.white));
        //pagerDetails.setCurrentItem(1);
    }

    public void clickBuilder() {
        txtCompany.setTextColor(getResources().getColor(R.color.gray_word));
        txtInfo.setTextColor(getResources().getColor(R.color.gray_word));
        txtBuilder.setTextColor(getResources().getColor(R.color.blue_word));
        txtCompanyLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtInfoLine.setBackgroundColor(getResources().getColor(R.color.white));
        txtBuilderLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        //pagerDetails.setCurrentItem(2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化位置信息
     */
    public void initArea() {
        txtFragmentHomeArea.setText("重庆");
        UserInfoDao infoDao = new UserInfoDao(getContext());
        if (infoDao.query() != null && infoDao.query().size() > 0)
            txtFragmentHomeArea.setText(infoDao.query().get(0).getCity());
    }

    /**
     * 地区选择
     */
    public void areaClick() {
        startActivityForResult(new Intent(getContext(), ProvinceEntity.class), RequestCode.HOME_AREA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCode.HOME_AREA_REQUEST:
                initArea();
                break;
        }
    }
}
