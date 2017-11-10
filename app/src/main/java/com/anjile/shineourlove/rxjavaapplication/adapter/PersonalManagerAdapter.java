package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.activity.MajorSelectActivity;
import com.anjile.shineourlove.rxjavaapplication.activity.PersonalMajorRankActivity;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleBean;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.PersonalMajorControl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class PersonalManagerAdapter extends RecyclerView.Adapter<PersonalManagerAdapter.ArchitectureHolder> {

    private List<PersonalManagerBean> beanList;
    private Context context;
    private Activity activity;
    private String type;

    public PersonalManagerAdapter(List<PersonalManagerBean> companyList, Context context, Activity activity, String type) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
        this.type = type;
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
        if (beanList.get(position).getName() != null)
            holder.txtPersonalSettingAdapterName.setText(beanList.get(position).getName());
        else
            holder.txtPersonalSettingAdapterName.setText("");

        if (beanList.get(position).getNumber() != null)
            holder.txtPersonalSettingAdapterNumber.setText(beanList.get(position).getNumber());
        else
            holder.txtPersonalSettingAdapterNumber.setText("");

        if (beanList.get(position).getRank() != null)
            holder.txtPersonalSettingAdapterGrade.setText(beanList.get(position).getRank());
        else
            holder.txtPersonalSettingAdapterGrade.setText("");
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
                    Intent intentMajor = new Intent(context, MajorSelectActivity.class);
                    intentMajor.putExtra("type", type);
                    intentMajor.putExtra("position", getAdapterPosition());
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < beanList.size(); i++) {
                        if (beanList.get(i).getName() != null && i != getAdapterPosition())
                            builder.append(beanList.get(i).getName() + ",");
                    }
                    intentMajor.putExtra("checked", builder.toString());
                    activity.startActivityForResult(intentMajor, RequestCode.PERSONAL_SETTING_MAJOR);
                    break;
                case R.id.ll_personal_setting_adapter_grade:
                    if (beanList.get(getAdapterPosition()).getName() != null) {
                        if (beanList.get(getAdapterPosition()).getName().equals("")) {
                            Toast.makeText(context, "请先选择专业!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (beanList.get(getAdapterPosition()).getRank().equals("此专业不分等级")) {
                                Toast.makeText(context, "此专业不分等级!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intentRank = new Intent(context, PersonalMajorRankActivity.class);
                                intentRank.putExtra("type", beanList.get(getAdapterPosition()).getName());
                                intentRank.putExtra("position", getAdapterPosition());
                                activity.startActivityForResult(intentRank, RequestCode.PERSONAL_SETTING_MAJOR_RANK);
                            }
                        }
                    } else {
                        Toast.makeText(context, "请先选择专业!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ll_personal_setting_adapter_number:
                    if (beanList.get(getAdapterPosition()).getName() != null && beanList.get(getAdapterPosition()).getRank() != null) {
                        if (beanList.get(getAdapterPosition()).getName().equals("") || beanList.get(getAdapterPosition()).getRank().equals("")) {
                            Toast.makeText(context, "请先选择专业和等级!", Toast.LENGTH_SHORT).show();
                        } else {
                            EventBus.getDefault().post(new PersonalMajorControl(new PersonalAllBean(), 2, getAdapterPosition()));
                        }
                    } else {
                        Toast.makeText(context, "请先选择专业和等级!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.txt_personal_setting_adapter_delete:
                    beanList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
                case R.id.txt_personal_setting_adapter_clear:
                    beanList.get(getAdapterPosition()).setName("");
                    beanList.get(getAdapterPosition()).setRank("");
                    beanList.get(getAdapterPosition()).setDetails("");
                    beanList.get(getAdapterPosition()).setNumber("");
                    notifyDataSetChanged();
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
