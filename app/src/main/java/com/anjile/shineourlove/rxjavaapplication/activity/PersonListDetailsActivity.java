package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.MFragmentPagerAdapter;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentFieldManager;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentRegister;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentTitle;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class PersonListDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_person_list_details);
        ButterKnife.bind(this);
        context = this;

        //初始化TextView
        InitTextView();

        //初始化ImageView
        InitImageView();

        //初始化Fragment
        InitFragment();

        //初始化ViewPager
        InitViewPager();
    }

    @Override
    public void viewClick(View v) {

    }

    private TextView registerTextView;
    private TextView titleTextView;
    private TextView managementTextView;

    //实现Tab滑动效果
    private ViewPager mViewPager;

    //动画图片
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;

    //动画图片宽度
    private int bmpW;

    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    public Context context;

    public static final String TAG = "MainActivity";

    /**
     * 初始化头标
     */
    private void InitTextView() {

        registerTextView = (TextView) findViewById(R.id.txt_person_list_details_register);
        titleTextView = (TextView) findViewById(R.id.txt_person_list_details_title);
        managementTextView = (TextView) findViewById(R.id.txt_person_list_details_manager);

        //添加点击事件
        registerTextView.setOnClickListener(new MyOnClickListener(0));
        titleTextView.setOnClickListener(new MyOnClickListener(1));
        managementTextView.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.vp_person_list_details);
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        registerTextView.setTextColor(getResources().getColor(R.color.blue_word));

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.img_person_list_details_cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW / 3);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) ((screenW-60) / 3.0);
        position_two = position_one * 2;

    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment() {
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new FragmentRegister());
        fragmentArrayList.add(new FragmentTitle());
        fragmentArrayList.add(new FragmentFieldManager());
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * 头标点击监听
     *
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 页面切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {

                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                        registerTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    } else if (currIndex == 2) {//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                        registerTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    }
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        titleTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                        titleTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    }
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        resetTextViewTextColor();
                        managementTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        resetTextViewTextColor();
                        managementTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.blue_word));
                    }
                    break;
            }
            currIndex = position;

            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    ;

    /**
     * 设置动画图片宽度
     *
     * @param mWidth 偏移量
     */
    private void setBmpW(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor() {
        registerTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.black_word));
        titleTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.black_word));
        managementTextView.setTextColor(ContextCompat.getColor(PersonListDetailsActivity.this, R.color.black_word));
    }
}
