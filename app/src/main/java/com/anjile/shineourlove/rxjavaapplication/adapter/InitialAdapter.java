package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.AptitudeRankSelectActivity;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.AptitudeInitialControl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class InitialAdapter extends RecyclerView.Adapter<InitialAdapter.ArchitectureHolder> {
    private List<String> beanList;
    private Context context;
    private Activity activity;
    private int mRecyclerViewHeight;

    public InitialAdapter(List<String> companyList, Context context, Activity activity, int mRecyclerViewHeight) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
        this.mRecyclerViewHeight = mRecyclerViewHeight;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aptitude_select_initial_include, parent, false);
        Log.i("InitialAdapter", "mRecyclerViewHeight: " + mRecyclerViewHeight);
        view.getLayoutParams().height = (mRecyclerViewHeight * 3 / 4) / beanList.size();
        return new ArchitectureHolder(view);
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtHead.setText(beanList.get(position));
    }

    @Override
    public int getItemCount() {
        if (beanList == null)
            return 0;
        else
            return beanList.size();
    }

    public class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtHead;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtHead = itemView.findViewById(R.id.txt_aptitude_select_initial_include);
            txtHead.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_select_initial_include:
                    Log.i("aptitude_select_act", "onClick: "+getAdapterPosition());
                    EventBus.getDefault().post(new AptitudeInitialControl(getAdapterPosition()));
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
