package com.anjile.shineourlove.rxjavaapplication.activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentOne;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentFour;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentThree;
import com.anjile.shineourlove.rxjavaapplication.fragment.FragmentTwo;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private FragmentOne fragmentFish;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private BadgeItem badgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void viewClick(View v) {

    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_main);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /**
         * 设置底部item的提示泡泡
         */
        badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.orange)
                .setBorderWidth(0);

        mBottomNavigationBar.setMode(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//设置底部导航按钮的模式
        mBottomNavigationBar.setBarBackgroundColor(R.color.blue);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.white);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_four).setActiveColorResource(R.color.glass_green))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(MainActivity.this);
        setDefaultFragment();
    }

    /**
     * 设置加载默认的碎片
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (fragmentFish == null)
            fragmentFish = new FragmentOne();
        transaction.replace(R.id.ll_content, fragmentFish).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (fragmentFish == null)
                    fragmentFish = new FragmentOne();
                transaction.replace(R.id.ll_content, fragmentFish);
                break;
            case 1:
                if (fragmentTwo == null)
                    fragmentTwo = new FragmentTwo();
                transaction.replace(R.id.ll_content, fragmentTwo);
                break;
            case 2:
                if (fragmentThree == null)
                    fragmentThree = new FragmentThree();
                transaction.replace(R.id.ll_content, fragmentThree);
                break;
            case 3:
                if (fragmentFour == null)
                    fragmentFour = new FragmentFour();
                transaction.replace(R.id.ll_content, fragmentFour);
                break;
            default:
                if (fragmentFish == null)
                    fragmentFish = new FragmentOne();
                transaction.replace(R.id.ll_content, fragmentFish);
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
