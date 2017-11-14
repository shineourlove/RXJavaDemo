package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterprisePersonDetailsEntity;

import java.util.List;


/**
 * Created by Administrator on 2017/10/12.
 */

public class EnterprisePersonDetailsAdapter extends RecyclerView.Adapter<EnterprisePersonDetailsAdapter.ArchitectureHolder> {
    private List<EnterprisePersonDetailsEntity.DataBean> beanList;
    private Context context;
    private Activity activity;

    public EnterprisePersonDetailsAdapter(List<EnterprisePersonDetailsEntity.DataBean> companyList, Context context, Activity activity) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.enterprise_person_details_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtCertificate.setText(beanList.get(position).getMajor_name());
        holder.txtName.setText(beanList.get(position).getName());
        holder.txtNumber.setText(beanList.get(position).getCode());
        holder.txtDate.setText(beanList.get(position).getJoin_date());
        if (position == beanList.size() - 1) {
            holder.txtLine.setVisibility(View.GONE);
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

    public class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtCertificate;
        private TextView txtName;
        private TextView txtNumber;
        private TextView txtDate;
        private TextView txtLine;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtCertificate = itemView.findViewById(R.id.txt_enterprise_person_details_adapter_certificate);
            txtName = itemView.findViewById(R.id.txt_enterprise_person_details_adapter_name);
            txtNumber = itemView.findViewById(R.id.txt_enterprise_person_details_adapter_number);
            txtDate = itemView.findViewById(R.id.txt_enterprise_person_details_adapter_date);
            txtLine = itemView.findViewById(R.id.txt_enterprise_person_details_adapter_line);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_aptitude_select_adapter_name:

                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
