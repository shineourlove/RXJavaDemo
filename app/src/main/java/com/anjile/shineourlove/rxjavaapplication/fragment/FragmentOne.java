package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;


import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.FishViewPagerAdapter;
import com.anjile.shineourlove.rxjavaapplication.holder.NetImageHolder;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/9/13.
 */

public class FragmentOne extends Fragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener {
    TextView txtF1;
    ViewPager viewPager;
    ConvenientBanner banner;
    ArrayList<String> photos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fish_layout, container, false);
        txtF1 = view.findViewById(R.id.txt_fragment_one);
        viewPager = view.findViewById(R.id.view_pager_fragment_one);
        banner = view.findViewById(R.id.cb_fragment_one);
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

        ArrayList<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        photos.add("http://pic133.nipic.com/file/20170624/9448607_155821950000_2.jpg");
        photos.add("http://pic135.nipic.com/file/20170709/9448607_203036152000_2.jpg");
        photos.add("http://pic138.nipic.com/file/20170815/9311975_142635215032_2.jpg");
        ArrayList<String> hints = new ArrayList<>();
        hints.add("这是第一个item");
        hints.add("这是第二个item");
        hints.add("这是第三个item");
        FishViewPagerAdapter pagerAdapter = new FishViewPagerAdapter(viewList, photos, hints);
        viewPager.setAdapter(pagerAdapter);
        initCB();
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 轮播当前显示的位置
     *
     * @param position 位置index
     */
    @Override
    public void onPageSelected(int position) {
        Log.i("cb_test", "onPageSelected: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("cb_test", "onPageScrollStateChanged: " + state);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("cb_test", "onItemClick: 1");
    }

    /**
     * 轮播的点击监听
     *
     * @param position 轮播的index
     */
    @Override
    public void onItemClick(int position) {
        Log.i("cb_test", "onItemClick: " + position);
        switch (position) {
            case 0:
                showShare();
                break;
            case 1:
                showShare();
                break;
            case 2:
                showShare();
                break;
        }
    }

    public void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getContext());
    }
}
