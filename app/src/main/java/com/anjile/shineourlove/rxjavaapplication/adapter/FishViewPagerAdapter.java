package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.anjile.shineourlove.rxjavaapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class FishViewPagerAdapter extends PagerAdapter {
    private List<View> mListViews;
    private List<String> photos;
    private List<String> hints;

    public FishViewPagerAdapter(List<View> mListViews, List<String> p, List<String> h) {
        this.mListViews = mListViews;
        this.photos = p;
        this.hints = h;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));//删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = mListViews.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            holder.imageView = convertView.findViewById(R.id.img_fish_viewpager_adapter);
            holder.txt1 = convertView.findViewById(R.id.txt_fish_viewpager_adapter);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (holder == null) {
            holder = new Holder();
            holder.imageView = convertView.findViewById(R.id.img_fish_viewpager_adapter);
            holder.txt1 = convertView.findViewById(R.id.txt_fish_viewpager_adapter);
            convertView.setTag(holder);
        }
        Glide.with(container)
                .load(photos.get(position))
                .apply(new RequestOptions().placeholder(R.drawable.test1))
                .into(holder.imageView);
        holder.txt1.setText(hints.get(position));
        container.addView(convertView, 0);//添加页卡
        return convertView;
    }

    @Override
    public int getCount() {
        return mListViews.size();//返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;//官方提示这样写
    }

    private class Holder {
        ImageView imageView;
        TextView txt1;
    }
}
