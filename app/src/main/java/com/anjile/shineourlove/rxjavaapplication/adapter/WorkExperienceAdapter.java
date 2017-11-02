package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.WorkResumeBean;
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class WorkExperienceAdapter extends RecyclerView.Adapter<WorkExperienceAdapter.HotSpotHolder> {
    private List<WorkResumeBean> selectList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public WorkExperienceAdapter(List<WorkResumeBean> selectList, Context context) {
        this.selectList = selectList;
        this.context = context;
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @Override
    public HotSpotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HotSpotHolder holder = new HotSpotHolder(
                LayoutInflater.from(context).inflate(R.layout.item_simple_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(HotSpotHolder holder, int position) {
        holder.txtLeft.setText(selectList.get(position).getCompany());
        holder.txtRight.setText(DateFormatTime.getTimeYearMonth(Long.parseLong(selectList.get(position).getStart()))
                + " 到 "
                + DateFormatTime.getTimeYearMonth(Long.parseLong(selectList.get(position).getEnd())));
    }


    @Override
    public int getItemCount() {
        return selectList.size();
    }

    class HotSpotHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        private TextView txtLeft;
        private TextView txtRight;

        public HotSpotHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtLeft = itemView.findViewById(R.id.txt_item_simple_left);
            txtRight = itemView.findViewById(R.id.txt_item_simple_right);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickListener != null) {
                mLongClickListener.onItemLongClick(view, getAdapterPosition());
            }
            return true;
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface MyItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
