package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class IntegralDetailsAdapter extends RecyclerView.Adapter<IntegralDetailsAdapter.IntegralHolder> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
    private ArrayList<String> list;
    private Context context;
    private MyItemClickListener clickListener;
    private MyItemLongClickListener longClickListener;

    public IntegralDetailsAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public IntegralHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new IntegralHolder(mHeaderView, clickListener, longClickListener);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new IntegralHolder(mFooterView, clickListener, longClickListener);
        }
        IntegralHolder holder = new IntegralHolder(
                LayoutInflater.from(context).inflate(R.layout.integral_gain_details_item, parent, false),
                clickListener, longClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(IntegralHolder holder, int position) {

        if (getItemViewType(position) == TYPE_NORMAL) {
            //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
            holder.txtIntegralGainDetailsItemDate.setText("2017-10-20");
            holder.txtIntegralGainDetailsItemNumber.setText(list.get(position-1));
            holder.txtIntegralGainDetailsItemType.setText("每日签到");
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        //这里确定需不需要header和footer
/*        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }*/
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return list.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return list.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
    }


    public class IntegralHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;
        TextView txtIntegralGainDetailsItemType;
        TextView txtIntegralGainDetailsItemDate;
        TextView txtIntegralGainDetailsItemNumber;
        TextView txtIntegralGainDetailsItemLine;

        public IntegralHolder(View itemView, MyItemClickListener mListener, MyItemLongClickListener mLongClickListener) {
            super(itemView);
            this.mListener = mListener;
            this.mLongClickListener = mLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            txtIntegralGainDetailsItemType = itemView.findViewById(R.id.txt_integral_gain_details_item_type);
            txtIntegralGainDetailsItemDate = itemView.findViewById(R.id.txt_integral_gain_details_item_date);
            txtIntegralGainDetailsItemNumber = itemView.findViewById(R.id.txt_integral_gain_details_item_number);
            txtIntegralGainDetailsItemLine = itemView.findViewById(R.id.txt_integral_gain_details_item_line);
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
