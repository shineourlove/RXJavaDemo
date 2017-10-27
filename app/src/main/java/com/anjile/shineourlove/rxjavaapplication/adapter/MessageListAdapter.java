package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.MessageMainEntity;
import com.anjile.shineourlove.rxjavaapplication.entity.ProjectSelectEntity;
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;
import com.anjile.shineourlove.rxjavaapplication.view.RoundImagePointView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ArchitectureHolder> {
    private ArrayList<MessageMainEntity> selectList;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public MessageListAdapter(ArrayList<MessageMainEntity> selectList, Context context) {
        this.selectList = selectList;
        this.context = context;
    }

    public MessageListAdapter() {
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
                LayoutInflater.from(context).inflate(R.layout.item_message_list_layout, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtName.setText(selectList.get(position).getTitle());
        holder.txtContent.setText(selectList.get(position).getContent());
        holder.txtDate.setText(DateFormatTime.getTimeDataForYearsMonthDay(selectList.get(position).getDate()));
        holder.imgHead.setPoint(selectList.get(position).isPoint());
        holder.imgHead.invalidate();
        Glide.with(context)
                .load(selectList.get(position).getImgURL())
                .apply(new RequestOptions().placeholder(R.drawable.ssdk_oks_classic_tumblr))
                .into(holder.imgHead);
    }


    @Override
    public int getItemCount() {
        return selectList.size();
    }

    class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        private TextView txtName;
        private TextView txtContent;
        private TextView txtDate;
        private RoundImagePointView imgHead;

        public ArchitectureHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            txtName = itemView.findViewById(R.id.txt_item_message_list_title);
            txtContent = itemView.findViewById(R.id.txt_item_message_list_content);
            txtDate = itemView.findViewById(R.id.txt_item_message_list_date);
            imgHead = itemView.findViewById(R.id.img_item_message_list_head);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
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
