package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.AptitudeRankSelectActivity;
import com.anjile.shineourlove.rxjavaapplication.activity.EnterpriseAptitudeActivity;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectItemAdapter extends RecyclerView.Adapter<AptitudeSelectItemAdapter.ArchitectureHolder> {
    private List<AptitudeAllBean> beanList;
    private Context context;
    private Activity activity;

    public AptitudeSelectItemAdapter(List<AptitudeAllBean> companyList, Context context, Activity activity) {
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
        holder.txtLeft.setText(beanList.get(position).getAptitude_Grade());
        if (position > 0) {
            holder.txtRight.setVisibility(View.VISIBLE);
            holder.txtRight.setText(beanList.get(position).getAptitude_Grade() + "及以上");
        } else {
            holder.txtRight.setVisibility(View.GONE);
        }
        if (position == beanList.size() - 1) {
            holder.txtLine.setVisibility(View.INVISIBLE);
        } else {
            holder.txtLine.setVisibility(View.VISIBLE);
        }
        holder.imgLeft.setVisibility(View.INVISIBLE);
        holder.imgRight.setVisibility(View.INVISIBLE);
        List<AptitudeSelectedBean> selectedList = new AptitudeSelectedDao(context).query();
        for (int i = 0; i < selectedList.size(); i++) {
            if (selectedList.get(i).getName().equals(beanList.get(position).getAptitude_name())) {
                if (holder.txtLeft.getText().equals(selectedList.get(i).getRank()))
                    holder.imgLeft.setVisibility(View.VISIBLE);
                if (holder.txtRight.getText().equals(selectedList.get(i).getRank()))
                    holder.imgRight.setVisibility(View.VISIBLE);
            }
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
                    saveAptitude(getAdapterPosition(), 0);
                    break;
                case R.id.txt_aptitude_select_item_adapter_right: //点击右边的级别
                    saveAptitude(getAdapterPosition(), 1);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }

        /**
         * 保存点击的资质信息到数据库
         *
         * @param position 点击位置
         * @param type     类型
         */
        public void saveAptitude(int position, int type) {
            AptitudeSelectedDao selectedDao = new AptitudeSelectedDao(context);
            AptitudeSelectedBean add = new AptitudeSelectedBean();
            add.setName(beanList.get(position).getAptitude_name());
            if (type == 0) {
                add.setDetails(beanList.get(position).getAll_id() + "");
                add.setRank(beanList.get(position).getAptitude_Grade());
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i <= position; i++) {
                    builder.append("," + beanList.get(i).getAll_id());
                }
                add.setRank(beanList.get(position).getAptitude_Grade() + "及以上");
                add.setDetails(builder.toString().replaceFirst(",", ""));
            }
            List<AptitudeSelectedBean> selectedList = selectedDao.query();
            for (int i = 0; i < selectedList.size(); i++) {
                if (add.getName().equals(selectedList.get(i).getName()))
                    selectedDao.deleteOnly(selectedList.get(i).getId());
            }
            selectedDao.add(add);
            Intent intent = new Intent(context, EnterpriseAptitudeActivity.class);
            context.startActivity(intent);
            activity.finish();
        }
    }
}
