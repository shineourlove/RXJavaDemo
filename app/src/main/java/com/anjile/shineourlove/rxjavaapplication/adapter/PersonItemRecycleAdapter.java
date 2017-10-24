package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;


/**
 * Created by Administrator on 2017/10/17.
 */

public class PersonItemRecycleAdapter extends Adapter<PersonItemRecycleAdapter.PersonHolder> {

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private ImageView icon;
        private TextView name;
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;

        public PersonHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
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
