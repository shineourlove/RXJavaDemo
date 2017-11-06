package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectAdapter extends RecyclerView.Adapter<AptitudeSelectAdapter.ArchitectureHolder> {

    private List<AptitudeAllBean> beanList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public AptitudeSelectAdapter(List<AptitudeAllBean> companyList, Context context) {
        this.beanList = companyList;
        this.context = context;
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_select_adapter_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtName.setText(beanList.get(position).getAptitude_name());
        if (position > 0) {
            if (!beanList.get(position).getInitial().equals(beanList.get(position - 1).getInitial())) {
                holder.txtHead.setText(beanList.get(position).getInitial());
                holder.txtHead.setVisibility(View.VISIBLE);
            } else {
                holder.txtHead.setVisibility(View.GONE);
            }
        }else{
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

    class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        private TextView txtHead;
        private TextView txtName;
        private SwipeMenuRecyclerView svList;

        public ArchitectureHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtName = itemView.findViewById(R.id.txt_aptitude_select_adapter_name);
            svList = itemView.findViewById(R.id.sv_aptitude_select_adapter_item);
            txtHead = itemView.findViewById(R.id.txt_aptitude_select_adapter_head);
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
