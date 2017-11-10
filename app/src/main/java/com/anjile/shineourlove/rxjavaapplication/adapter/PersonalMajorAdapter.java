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
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.AptitudeRankSelectActivity;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.PersonalMajorControl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class PersonalMajorAdapter extends RecyclerView.Adapter<PersonalMajorAdapter.ArchitectureHolder> {
    private List<PersonalAllBean> beanList;
    private Context context;
    private Activity activity;

    public PersonalMajorAdapter(List<PersonalAllBean> companyList, Context context, Activity activity) {
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
        holder.txtName.setText(beanList.get(position).getMajor_name().trim());
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
                    if (activity.getIntent().getStringExtra("checked") != null && activity.getIntent().getStringExtra("checked").contains(beanList.get(getAdapterPosition()).getMajor_name())) {
                        Toast.makeText(context, "该项工程已选择！", Toast.LENGTH_SHORT).show();
                    } else {
                        activity.setResult(ResultCode.PERSONAL_MAJOR_RESULT);
                        EventBus.getDefault().post(new PersonalMajorControl(beanList.get(getAdapterPosition()), 0, activity.getIntent().getIntExtra("position", 0)));
                        activity.finish();
                    }
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
