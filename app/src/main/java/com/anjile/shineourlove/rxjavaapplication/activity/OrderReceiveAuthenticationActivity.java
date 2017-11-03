package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.WorkExperienceAdapter;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.WorkResumeBean;
import com.anjile.shineourlove.rxjavaapplication.db.WorkResumeDao;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.utils.SwiperRecycleViewUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderReceiveAuthenticationActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.edt_order_receive_authentication_name)
    EditText edtOrderReceiveAuthenticationName;
    @BindView(R.id.txt_order_receive_authentication_gender)
    TextView txtOrderReceiveAuthenticationGender;
    @BindView(R.id.ll_order_receive_authentication_gender)
    LinearLayout llOrderReceiveAuthenticationGender;
    @BindView(R.id.txt_order_receive_authentication_birth)
    TextView txtOrderReceiveAuthenticationBirth;
    @BindView(R.id.ll_order_receive_authentication_birth)
    LinearLayout llOrderReceiveAuthenticationBirth;
    @BindView(R.id.edt_order_receive_authentication_graduate)
    EditText edtOrderReceiveAuthenticationGraduate;
    @BindView(R.id.rb_order_receive_authentication_budget)
    RadioButton rbOrderReceiveAuthenticationBudget;
    @BindView(R.id.rb_order_receive_authentication_scheme)
    RadioButton rbOrderReceiveAuthenticationScheme;
    @BindView(R.id.txt_order_receive_authentication_skilled)
    TextView txtOrderReceiveAuthenticationSkilled;
    @BindView(R.id.ll_order_receive_authentication_skilled)
    LinearLayout llOrderReceiveAuthenticationSkilled;
    @BindView(R.id.txt_order_receive_authentication_add_work)
    TextView txtOrderReceiveAuthenticationAddWork;
    @BindView(R.id.sv_order_receive_authentication_add_work)
    SwipeMenuRecyclerView svOrderReceiveAuthenticationAddWork;
    @BindView(R.id.txt_order_receive_authentication_add_project)
    TextView txtOrderReceiveAuthenticationAddProject;
    @BindView(R.id.sv_order_receive_authentication_add_project)
    SwipeMenuRecyclerView svOrderReceiveAuthenticationAddProject;
    @BindView(R.id.edt_order_receive_authentication_description)
    EditText edtOrderReceiveAuthenticationDescription;

    WorkExperienceAdapter workAdapter;
    WorkExperienceAdapter projectAdapter;
    List<WorkResumeBean> workList, projectList;
    WorkResumeDao workResumeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_order_receive_authentication);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.order_receiving_authentication);
        txtTopStatusBarRight.setText(R.string.next_step);

        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);

        llOrderReceiveAuthenticationGender.setOnClickListener(this);
        llOrderReceiveAuthenticationBirth.setOnClickListener(this);
        llOrderReceiveAuthenticationSkilled.setOnClickListener(this);

        txtOrderReceiveAuthenticationAddWork.setOnClickListener(this);
        txtOrderReceiveAuthenticationAddProject.setOnClickListener(this);

        initWorkList();
        initProject();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_status_bar_back:
                finish();
                break;
            case R.id.txt_top_status_bar_left:
                finish();
                break;
            case R.id.txt_top_status_bar_right:
                Toast.makeText(this, "下一步", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_order_receive_authentication_gender:
                break;
            case R.id.ll_order_receive_authentication_birth:
                break;
            case R.id.ll_order_receive_authentication_skilled:
                break;
            case R.id.txt_order_receive_authentication_add_work:
                Intent intentWork = new Intent(this, JobResumeActivity.class);
                intentWork.putExtra("tag", 1);
                startActivityForResult(intentWork, RequestCode.AUTHENTICATION_ADD_RESUME);
                break;
            case R.id.txt_order_receive_authentication_add_project:
                workList.add(new WorkResumeBean("金陵兵工厂", "打铁", "金陵", "1490000000000", "1500800000000", "职业打铁，决不妥协"));
                workAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void initWorkList() {
        workResumeDao = new WorkResumeDao(this);
        workList = workResumeDao.query();
/*        for (int i = 0; i < 4; i++) {
            workList.add(new WorkResumeBean("洛城铁厂" + i, "打铁", "洛杉矶", "1490000000000", "1500800000000", "职业打铁，决不妥协"));
        }*/
        workAdapter = new WorkExperienceAdapter(workList, this);
        svOrderReceiveAuthenticationAddWork.setLayoutManager(new FullyLinearLayoutManager(this));
        svOrderReceiveAuthenticationAddWork.setSwipeMenuCreator(SwiperRecycleViewUtil.getSwipeMenuCreator(this, R.color.tint_red, R.color.white));
        svOrderReceiveAuthenticationAddWork.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i("order_receive", "onItemClick: " + position);
            }
        });
        svOrderReceiveAuthenticationAddWork.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                workResumeDao.deleteOnly(workList.get(menuBridge.getAdapterPosition()).getId());
                workList.remove(menuBridge.getAdapterPosition());
                workAdapter.notifyDataSetChanged();
            }
        });
        svOrderReceiveAuthenticationAddWork.setAdapter(workAdapter);
    }

    public void initProject() {
        workResumeDao = new WorkResumeDao(this);
        projectList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            projectList.add(new WorkResumeBean("金陵兵工厂" + i, "打铁", "金陵", "1490000000000", "1500800000000", "职业打铁，决不妥协"));
        }
        projectAdapter = new WorkExperienceAdapter(projectList, this);
        svOrderReceiveAuthenticationAddProject.setLayoutManager(new FullyLinearLayoutManager(this));
        svOrderReceiveAuthenticationAddProject.setSwipeMenuCreator(SwiperRecycleViewUtil.getSwipeMenuCreator(this, R.color.tint_red, R.color.white));
        svOrderReceiveAuthenticationAddProject.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i("order_receive", "onItemClick: " + position);
            }
        });
        svOrderReceiveAuthenticationAddProject.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                projectList.remove(menuBridge.getAdapterPosition());
                projectAdapter.notifyDataSetChanged();
            }
        });
        svOrderReceiveAuthenticationAddProject.setAdapter(projectAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("date_picker", "requestCode: " + requestCode + "   resultCode: " + resultCode);
        switch (requestCode) {
            case 10001:
                switch (resultCode) {
                    case 20000:
                        Log.i("date_picker", "onActivityResult: ");
                        handler.sendEmptyMessage(0);
                        break;
                }
                break;
        }
    }

    static class OrderHandler extends Handler {
        WeakReference<OrderReceiveAuthenticationActivity> activityWeakReference;

        OrderHandler(OrderReceiveAuthenticationActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final OrderReceiveAuthenticationActivity activity = activityWeakReference.get();
            switch (msg.what) {
                case 0:
                    final List<WorkResumeBean> queryList = activity.workResumeDao.query();
                    if (queryList != null) {
                        activity.handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                activity.workList.add(queryList.get(queryList.size()-1));
                                Log.i("date_picker", "workList: " + activity.workList.size());
                                activity.workAdapter.notifyDataSetChanged();
                            }
                        },200);
                    }
                    break;
            }
        }
    }

    OrderHandler handler = new OrderHandler(this);
}
