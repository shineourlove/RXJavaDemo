package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.AptitudeQueryItemEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeQueryItemAdapter extends RecyclerView.Adapter<AptitudeQueryItemAdapter.ArchitectureHolder> {

    private List<EnterpriseSearchEntity.DataBean> beanList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public AptitudeQueryItemAdapter(List<EnterpriseSearchEntity.DataBean> companyList, Context context) {
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
                LayoutInflater.from(context).inflate(R.layout.aptutide_query_item_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtName.setText(beanList.get(position).getName());
        holder.txtArea.setText(beanList.get(position).getRegistersite());
        if (true) {//是否认证
            holder.txtArea.setBackground(context.getDrawable(R.drawable.stroke_blue_1px));
            holder.txtArea.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.authentication_diamond),
                    null, null, null);
        } else {
            holder.txtArea.setBackground(context.getDrawable(R.drawable.stroke_white_1px));
            holder.txtArea.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if (position == beanList.size() - 1)
            holder.line.setVisibility(View.GONE);
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
        private TextView txtName;
        private TextView txtArea;
        private View line;

        public ArchitectureHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtName = itemView.findViewById(R.id.txt_aptitude_query_item_company);
            txtArea = itemView.findViewById(R.id.txt_aptitude_query_item_area);
            line = itemView.findViewById(R.id.include_aptitude_query_item_line);
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
