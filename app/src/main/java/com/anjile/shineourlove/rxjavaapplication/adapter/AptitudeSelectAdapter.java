package com.anjile.shineourlove.rxjavaapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeAllDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.AptitudeRankControl;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AptitudeSelectAdapter extends RecyclerView.Adapter<AptitudeSelectAdapter.ArchitectureHolder> {
    private List<AptitudeAllBean> beanList;
    private List<AptitudeRankControl> controls;
    private Context context;

    public AptitudeSelectAdapter(List<AptitudeAllBean> companyList, Context context, List<AptitudeRankControl> booleanList) {
        this.beanList = companyList;
        this.context = context;
        this.controls = booleanList;
    }

    @Override
    public ArchitectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArchitectureHolder holder = new ArchitectureHolder(
                LayoutInflater.from(context).inflate(R.layout.aptitude_select_adapter_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ArchitectureHolder holder, int position) {
        Log.i("aptitude_select_adapter", "onBindViewHolder: ");
        if (controls.get(position).getPosition() == 1) {
            holder.svList.setVisibility(View.VISIBLE);
        } else {
            holder.svList.setVisibility(View.GONE);
        }
        holder.txtName.setText(beanList.get(position).getAptitude_name());
        if (position > 0) {
            if (!beanList.get(position).getInitial().equals(beanList.get(position - 1).getInitial())) {
                holder.txtHead.setText(beanList.get(position).getInitial());
                holder.txtHead.setVisibility(View.VISIBLE);
            } else {
                holder.txtHead.setVisibility(View.GONE);
            }
        } else {
            holder.txtHead.setText(beanList.get(position).getInitial());
            holder.txtHead.setVisibility(View.VISIBLE);
        }
        loadRank(beanList.get(position).getAptitude_name(), holder.svList);
    }

    @Override
    public int getItemCount() {
        if (beanList == null)
            return 0;
        else
            return beanList.size();
    }

    public class ArchitectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtHead;
        private TextView txtName;
        private RecyclerView svList;
        private ImageView imgArrow;

        public ArchitectureHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_aptitude_select_adapter_name);
            svList = itemView.findViewById(R.id.sv_aptitude_select_adapter_item);
            txtHead = itemView.findViewById(R.id.txt_aptitude_select_adapter_head);
            imgArrow = itemView.findViewById(R.id.img_aptitude_select_adapter_arrow);
            txtName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_aptitude_select_adapter_name:

                    controlRank(new AptitudeRankControl(getAdapterPosition()));
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }


        public void controlRank(AptitudeRankControl c) {
            Log.i("aptitude_select_adapter", "AptitudeRankControl: " + c.getPosition());
            for (int i = 0; i < controls.size(); i++) {
                controls.get(i).setPosition(0);
            }
            controls.get(c.getPosition()).setPosition(1);
            notifyDataSetChanged();
        }
    }

    int clickCount = 0;

    public void loadRank(String name, RecyclerView svList) {
        Log.i("aptitude_select_adapter", "name: " + name);
        AptitudeAllDao dao = new AptitudeAllDao(context);
        List<AptitudeAllBean> list = dao.queryWhere("aptitude_name", name);
        AptitudeSelectItemAdapter selectItemAdapter = new AptitudeSelectItemAdapter(list, context);
        svList.setLayoutManager(new FullyLinearLayoutManager(context));
        svList.setAdapter(selectItemAdapter);
    }

    public void clearRank(RecyclerView view) {
        view.removeAllViews();
    }


    public void startAlphaAnimation(ImageView img, float start, float finish) {
        RotateAnimation animation = new RotateAnimation(start, finish, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续时长
        animation.setDuration(300);
        //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
        animation.setFillAfter(true);
        //设置动画结束之后的状态是否是动画开始时的状态，true，表示是保持动画开始时的状态
        animation.setFillBefore(true);
        //设置动画的重复模式：反转REVERSE和重新开始RESTART
        animation.setRepeatMode(AlphaAnimation.REVERSE);
        //设置动画播放次数
        animation.setRepeatCount(-1);
        //开始动画
        img.setAnimation(animation);
        animation.startNow();
        //清除动画
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                animation.cancel();
            }
        });
    }
}
