package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.EnterpriseAptitudeAddControl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectedAdapter extends RecyclerView.Adapter<AptitudeSelectedAdapter.ArchitectureHolder> {
    private List<AptitudeSelectedBean> beanList;
    private Context context;

    public AptitudeSelectedAdapter(List<AptitudeSelectedBean> companyList, Context context) {
        this.beanList = companyList;
        this.context = context;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_selected_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtLeft.setText(beanList.get(position).getName() + beanList.get(position).getRank());
        holder.txtDelete.setText("删除");
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
        private TextView txtDelete;
        private TextView txtLine;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtLeft = itemView.findViewById(R.id.txt_aptitude_selected_adapter_left);
            txtDelete = itemView.findViewById(R.id.txt_aptitude_selected_adapter_delete);
            txtLine = itemView.findViewById(R.id.txt_aptitude_select_item_adapter_line);
            txtDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_selected_adapter_delete:
                    AptitudeSelectedDao dao = new AptitudeSelectedDao(context);
                    dao.deleteOnly(beanList.get(getAdapterPosition()).getId());
                    beanList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    EventBus.getDefault().post(new EnterpriseAptitudeAddControl(beanList.size()));
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
