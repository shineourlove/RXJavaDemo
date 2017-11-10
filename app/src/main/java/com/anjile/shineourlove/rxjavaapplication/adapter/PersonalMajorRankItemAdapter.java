package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.EnterpriseAptitudeActivity;
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

public class PersonalMajorRankItemAdapter extends RecyclerView.Adapter<PersonalMajorRankItemAdapter.ArchitectureHolder> {
    private List<PersonalAllBean> beanList;
    private Context context;
    private Activity activity;

    public PersonalMajorRankItemAdapter(List<PersonalAllBean> companyList, Context context, Activity activity) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_select_item_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtLeft.setText(beanList.get(position).getMajor_grade());
        if (position > 0) {
            holder.txtRight.setVisibility(View.VISIBLE);
            holder.txtRight.setText(beanList.get(position).getMajor_grade() + "及以上");
        } else {
            holder.txtRight.setVisibility(View.GONE);
        }
        holder.imgLeft.setVisibility(View.INVISIBLE);
        holder.imgRight.setVisibility(View.INVISIBLE);
        if (position == beanList.size() - 1) {
            holder.txtLine.setVisibility(View.GONE);
        } else {
            holder.txtLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (beanList == null)
            return 0;
        else
            return beanList.size();
    }

    class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtLeft;
        private TextView txtRight;
        private TextView txtLine;
        private ImageView imgLeft;
        private ImageView imgRight;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtLeft = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_left);
            txtRight = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_right);
            txtLine = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_line);
            imgLeft = itemView.findViewById(R.id.img_aptitude_select_item_adapter_left);
            imgRight = itemView.findViewById(R.id.img_aptitude_select_item_adapter_right);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            txtLeft.setOnClickListener(this);
            txtRight.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_select_item_adapter_left: //点击左边级别
                    saveRank(activity.getIntent().getIntExtra("position", 0), getAdapterPosition(), 1);
                    break;
                case R.id.txt_aptitude_select_item_adapter_right: //点击右边的级别
                    saveRank(activity.getIntent().getIntExtra("position", 0), getAdapterPosition(), 0);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public void saveRank(int position, int adapterPosition, int type) {
        PersonalAllBean allBean = new PersonalAllBean();
        switch (type) {
            case 0:
                allBean.setMajor_grade(beanList.get(adapterPosition).getMajor_grade() + "及以上");
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i <= adapterPosition; i++) {
                    builder.append("," + beanList.get(i).getId());
                }
                Log.i("personal_setting", "saveRank: " + builder.toString());
                allBean.setMajor_name(builder.toString().replaceFirst(",", ""));
                EventBus.getDefault().post(new PersonalMajorControl(allBean, 1, position));
                activity.finish();
                break;
            case 1:
                allBean.setMajor_grade(beanList.get(adapterPosition).getMajor_grade());
                allBean.setMajor_name(beanList.get(adapterPosition).getId() + "");
                EventBus.getDefault().post(new PersonalMajorControl(allBean, 1, position));
                activity.finish();
                break;
        }
    }
}
