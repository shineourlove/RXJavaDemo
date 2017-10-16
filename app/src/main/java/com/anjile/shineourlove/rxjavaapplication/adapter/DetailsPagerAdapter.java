package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class DetailsPagerAdapter extends PagerAdapter {
    private List<View> mListViews;

    public DetailsPagerAdapter(List<View> mListViews) {
        this.mListViews = mListViews;
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
            holder.rcvCompany = convertView.findViewById(R.id.rcv_details_pager_company);
            holder.rcvInformation = convertView.findViewById(R.id.rcv_details_pager_information);
            holder.rcvBuilder = convertView.findViewById(R.id.rcv_details_pager_builder);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (holder == null) {
            holder = new Holder();
            holder.rcvCompany = convertView.findViewById(R.id.rcv_details_pager_company);
            holder.rcvInformation = convertView.findViewById(R.id.rcv_details_pager_information);
            holder.rcvBuilder = convertView.findViewById(R.id.rcv_details_pager_builder);
            convertView.setTag(holder);
        }
        switch (position) {
            case 0:
                initArchitecture(holder.rcvCompany, container.getContext(), "红旗渠建设集团有限公司");
                holder.rcvCompany.setVisibility(View.VISIBLE);
                holder.rcvInformation.setVisibility(View.GONE);
                holder.rcvBuilder.setVisibility(View.GONE);
                break;
            case 1:
                initArchitecture(holder.rcvInformation, container.getContext(), "五星出东方利中国");
                holder.rcvCompany.setVisibility(View.GONE);
                holder.rcvInformation.setVisibility(View.VISIBLE);
                holder.rcvBuilder.setVisibility(View.GONE);
                break;
            case 2:
                initArchitecture(holder.rcvBuilder, container.getContext(), "刘德华");
                holder.rcvCompany.setVisibility(View.GONE);
                holder.rcvInformation.setVisibility(View.GONE);
                holder.rcvBuilder.setVisibility(View.VISIBLE);
                break;
            default:
/*                initArchitecture(holder.rcvCompany, container.getContext(), "红旗渠建设集团有限公司");
                holder.rcvCompany.setVisibility(View.VISIBLE);
                holder.rcvInformation.setVisibility(View.GONE);
                holder.rcvBuilder.setVisibility(View.GONE);*/
        }
        container.addView(convertView, 0);//添加页卡
        return convertView;
    }

    public void initArchitecture(RecyclerView view, Context context, String s) {
        view.setFocusable(false);
        ArrayList<CompanyDetails> companyDetailses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            companyDetailses.add(new CompanyDetails(s, 1506787200000l + 24 * 60 * 60 * 1000 * i, 3, 2, 1));
        }
        ArchitectureRecycleAdapter architectureAdapter = new ArchitectureRecycleAdapter(companyDetailses, context);
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context);
        view.setNestedScrollingEnabled(false);
        view.setLayoutManager(linearLayoutManager);
        view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        view.setAdapter(architectureAdapter);
        architectureAdapter.notifyDataSetChanged();
        architectureAdapter.setOnItemClickListener(new ArchitectureRecycleAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("recycle_view_click", "onItemClick: " + position);
            }
        });
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
        RecyclerView rcvCompany, rcvInformation, rcvBuilder;
    }
}
