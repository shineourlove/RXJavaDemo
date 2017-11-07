package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectItemAdapter extends RecyclerView.Adapter<AptitudeSelectItemAdapter.ArchitectureHolder> {
    private List<AptitudeAllBean> beanList;
    private Context context;

    public AptitudeSelectItemAdapter(List<AptitudeAllBean> companyList, Context context) {
        this.beanList = companyList;
        this.context = context;
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

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtLeft = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_left);
            txtRight = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_right);
            txtLine = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_line);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_select_item_adapter_left:

                    break;
                case R.id.txt_aptitude_select_item_adapter_right:

                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
