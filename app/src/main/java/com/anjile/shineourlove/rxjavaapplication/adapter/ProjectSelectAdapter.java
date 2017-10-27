package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.CompanyDetails;
import com.anjile.shineourlove.rxjavaapplication.entity.ProjectSelectEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ProjectSelectAdapter extends RecyclerView.Adapter<ProjectSelectAdapter.ArchitectureHolder> {
    private ArrayList<ProjectSelectEntity> selectList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public ProjectSelectAdapter(ArrayList<ProjectSelectEntity> selectList, Context context) {
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
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.project_select_item_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtName.setText(selectList.get(position).getItemName());
        if (position == selectList.size() - 1)
            holder.txtLine.setVisibility(View.GONE);
        else
            holder.txtLine.setVisibility(View.VISIBLE);
        if (selectList.get(position).isCheck())
            holder.imgSelect.setVisibility(View.VISIBLE);
        else
            holder.imgSelect.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return selectList.size();
    }

    class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        private TextView txtName;
        private TextView txtLine;
        private ImageView imgSelect;

        public ArchitectureHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtName = itemView.findViewById(R.id.txt_project_select_item_name);
            txtLine = itemView.findViewById(R.id.txt_project_select_item_line);
            imgSelect = itemView.findViewById(R.id.img_project_select_item_check);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectList.get(getAdapterPosition()).setCheck(!selectList.get(getAdapterPosition()).isCheck());
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
