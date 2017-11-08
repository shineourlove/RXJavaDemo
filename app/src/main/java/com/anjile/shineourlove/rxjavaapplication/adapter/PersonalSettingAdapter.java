package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/10/12.
 */

public class PersonalSettingAdapter extends RecyclerView.Adapter<PersonalSettingAdapter.ArchitectureHolder> {

    private List<PersonalRegisterBean> beanList;
    private Context context;
    private Activity activity;

    public PersonalSettingAdapter(List<PersonalRegisterBean> companyList, Context context, Activity activity) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.personal_setting_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
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
        LinearLayout llPersonalSettingAdapterName;
        LinearLayout llPersonalSettingAdapterGrade;
        LinearLayout llPersonalSettingAdapterNumber;
        TextView txtPersonalSettingAdapterName;
        TextView txtPersonalSettingAdapterGrade;
        TextView txtPersonalSettingAdapterNumber;
        TextView txtPersonalSettingAdapterDelete;
        TextView txtPersonalSettingAdapterClear;
        TextView txtLine;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            llPersonalSettingAdapterName = itemView.findViewById(R.id.ll_personal_setting_adapter_name);
            llPersonalSettingAdapterGrade = itemView.findViewById(R.id.ll_personal_setting_adapter_grade);
            llPersonalSettingAdapterNumber = itemView.findViewById(R.id.ll_personal_setting_adapter_number);
            txtPersonalSettingAdapterName = itemView.findViewById(R.id.txt_personal_setting_adapter_name);
            txtPersonalSettingAdapterGrade = itemView.findViewById(R.id.txt_personal_setting_adapter_grade);
            txtPersonalSettingAdapterNumber = itemView.findViewById(R.id.txt_personal_setting_adapter_number);
            txtPersonalSettingAdapterDelete = itemView.findViewById(R.id.txt_personal_setting_adapter_delete);
            txtPersonalSettingAdapterClear = itemView.findViewById(R.id.txt_personal_setting_adapter_clear);
            txtLine = itemView.findViewById(R.id.txt_personal_setting_adapter_line);

            llPersonalSettingAdapterName.setOnClickListener(this);
            llPersonalSettingAdapterGrade.setOnClickListener(this);
            llPersonalSettingAdapterNumber.setOnClickListener(this);
            txtPersonalSettingAdapterDelete.setOnClickListener(this);
            txtPersonalSettingAdapterClear.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_personal_setting_adapter_name:
                    break;
                case R.id.ll_personal_setting_adapter_grade:
                    break;
                case R.id.ll_personal_setting_adapter_number:
                    break;
                case R.id.txt_personal_setting_adapter_delete:
                    break;
                case R.id.txt_personal_setting_adapter_clear:
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
