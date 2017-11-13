package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.db.EnterpriseQueryDao;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.ProvinceEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ProvinceSelectedAdapter extends RecyclerView.Adapter<ProvinceSelectedAdapter.ArchitectureHolder> {
    private List<ProvinceEntity> beanList;
    private Context context;
    private Activity activity;

    public ProvinceSelectedAdapter(List<ProvinceEntity> companyList, Context context, Activity activity) {
        this.beanList = companyList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_selected_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        holder.txtLeft.setText(beanList.get(position).getName());
        holder.txtDelete.setVisibility(View.GONE);
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
            txtLeft.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_selected_adapter_left:
                    EnterpriseQueryDao queryDao = new EnterpriseQueryDao(context);
                    queryDao.updateOnly("province", beanList.get(getAdapterPosition()).getName().trim());
                    UserInfoDao infoDao = new UserInfoDao(context);
                    infoDao.updateOnly("city", beanList.get(getAdapterPosition()).getName().trim());
                    activity.setResult(ResultCode.ENTERPRISE_AREA_PROVINCE);
                    activity.finish();
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
