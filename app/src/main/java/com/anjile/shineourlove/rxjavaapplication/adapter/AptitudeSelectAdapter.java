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

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectAdapter extends RecyclerView.Adapter<AptitudeSelectAdapter.ArchitectureHolder> {
    private List<AptitudeAllBean> beanList;
    private Context context;
    private Activity activity;

    public AptitudeSelectAdapter(List<AptitudeAllBean> companyList, Context context, Activity activity) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_select_adapter_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        List<AptitudeSelectedBean> selectedList = new AptitudeSelectedDao(context).query();
        boolean isSelected = false;
        for (int i = 0; i < selectedList.size(); i++) {
            if (selectedList.get(i).getName().equals(beanList.get(position).getAptitude_name())) {
                holder.txtSelected.setText("已选择");
                isSelected = true;
            }
        }
        if (!isSelected)
            holder.txtSelected.setText("");
        Log.i("aptitude_select_adapter", "onBindViewHolder: ");
        holder.txtName.setText(beanList.get(position).getAptitude_name().trim());
        if (position > 0) {
            if (!beanList.get(position).getInitial().equals(beanList.get(position - 1).getInitial())) {
                holder.txtHead.setText(beanList.get(position).getInitial());
                holder.txtHead.setVisibility(View.VISIBLE);
            } else {
                holder.txtHead.setVisibility(View.GONE);
            }
        } else {
            holder.txtHead.setText(beanList.get(position).getInitial());
            holder.txtHead.setVisibility(View.VISIBLE);
        }
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
        private TextView txtName;
        private TextView txtSelected;
        private LinearLayout llName;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_aptitude_select_adapter_name);
            txtHead = itemView.findViewById(R.id.txt_aptitude_select_adapter_head);
            txtSelected = itemView.findViewById(R.id.txt_aptitude_select_adapter_selected);
            llName = itemView.findViewById(R.id.ll_aptitude_select_adapter_name);
            llName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_aptitude_select_adapter_name:
                    Intent intent = new Intent(context, AptitudeRankSelectActivity.class);
                    intent.putExtra("aptitude_name", txtName.getText().toString().trim());
                    activity.startActivityForResult(intent, RequestCode.APTITUDE_SELECT_ACTIVITY_REQUEST);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
