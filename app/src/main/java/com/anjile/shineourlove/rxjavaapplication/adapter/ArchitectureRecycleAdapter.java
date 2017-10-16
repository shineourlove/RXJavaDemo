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
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ArchitectureRecycleAdapter extends RecyclerView.Adapter<ArchitectureRecycleAdapter.ArchitectureHolder> {
    private ArrayList<CompanyDetails> companyList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public ArchitectureRecycleAdapter(ArrayList<CompanyDetails> companyList, Context context) {
        this.companyList = companyList;
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
                LayoutInflater.from(context).inflate(R.layout.architecture_item_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtName.setText(companyList.get(position).getName());
        holder.txtDate.setText("最近中标: " + DateFormatTime.getTimeDataForYearsToDate(companyList.get(position).getDate()));
        holder.imgHint.setImageResource(R.drawable.ssdk_oks_classic_email);
        holder.txtAptitude.setText(companyList.get(position).getAptitude() + "");
        holder.txtManage.setText(companyList.get(position).getManage() + "");
        holder.txtHonor.setText(companyList.get(position).getHonor() + "");
    }


    @Override
    public int getItemCount() {
        return companyList.size();
    }

    class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        private TextView txtName;
        private TextView txtDate;
        private ImageView imgHint;
        private TextView txtAptitude, txtManage, txtHonor;

        public ArchitectureHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtName = itemView.findViewById(R.id.txt_architecture_item_company);
            txtDate = itemView.findViewById(R.id.txt_architecture_item_date);
            imgHint = itemView.findViewById(R.id.img_architecture_item_hint);
            txtAptitude = itemView.findViewById(R.id.txt_architecture_item_aptitude);
            txtManage = itemView.findViewById(R.id.txt_architecture_item_manage);
            txtHonor = itemView.findViewById(R.id.txt_architecture_item_honor);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickListener != null) {
                mLongClickListener.onItemLongClick(view, getPosition());
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
