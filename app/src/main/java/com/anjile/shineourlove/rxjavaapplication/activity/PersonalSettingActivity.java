package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.PersonalManagerAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.PersonalSettingAdapter;
import com.anjile.shineourlove.rxjavaapplication.adapter.PersonalTitleAdapter;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalAllDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleBean;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.PersonalMajorControl;
import com.anjile.shineourlove.rxjavaapplication.manager.FullyLinearLayoutManager;
import com.anjile.shineourlove.rxjavaapplication.popup.MyPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalSettingActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.rcv_personal_setting_list)
    RecyclerView rcvPersonalSettingList;
    @BindView(R.id.btn_personal_setting_add)
    Button btnPersonalSettingAdd;

    int allType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_personal_setting);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        txtTopStatusBarTitle.setText(getIntent().getStringExtra("title"));
        txtTopStatusBarRight.setText(R.string.confirm);

        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);

        btnPersonalSettingAdd.setOnClickListener(this);
        if (getIntent().getStringExtra("title").equals("注册类人员")) {
            initRegisterList();
            allType = 1;
        } else if (getIntent().getStringExtra("title").equals("职称类人员")) {
            initTitleList();
            allType = 2;
        } else {
            initManagerList();
            allType = 3;
        }
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_top_status_bar_left:
                finish();
                break;
            case R.id.img_top_status_bar_back:
                finish();
                break;
            case R.id.txt_top_status_bar_right:
                if (getIntent().getStringExtra("title").equals("注册类人员")) {
                    confirm(1);
                } else if (getIntent().getStringExtra("title").equals("职称类人员")) {
                    confirm(2);
                } else {
                    confirm(3);
                }
                break;
            case R.id.btn_personal_setting_add:
                if (getIntent().getStringExtra("title").equals("注册类人员")) {
                    addList(1);
                } else if (getIntent().getStringExtra("title").equals("职称类人员")) {
                    addList(2);
                } else {
                    addList(3);
                }
                break;
            case R.id.txt_popup_window_style_1:
                setNumber(1);
                break;
            case R.id.txt_popup_window_style_2:
                setNumber(2);
                break;
            case R.id.txt_popup_window_style_3:
                setNumber(3);
                break;
            case R.id.txt_popup_window_style_4:
                setNumber(4);
                break;
            case R.id.txt_popup_window_style_5:
                setNumber(5);
                break;
            case R.id.txt_popup_window_style_6:
                setNumber(6);
                break;
            case R.id.txt_popup_window_style_7:
                setNumber(7);
                break;
            case R.id.txt_popup_window_style_8:
                setNumber(8);
                break;
        }
    }

    public void setNumber(int i) {
        switch (allType) {
            case 1:
                registerBeanList.get(position).setNumber(i + "个及以上");
                registerAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
                break;
            case 2:
                titleBeanList.get(position).setNumber(i + "个及以上");
                titleAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
                break;
            case 3:
                managerBeanList.get(position).setNumber(i + "个及以上");
                managerAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
                break;
        }
    }

    PersonalSettingAdapter registerAdapter;
    PersonalTitleAdapter titleAdapter;
    PersonalManagerAdapter managerAdapter;
    List<PersonalRegisterBean> registerBeanList;
    List<PersonalTitleBean> titleBeanList;
    List<PersonalManagerBean> managerBeanList;

    int position = 0;

    public void initRegisterList() {
        PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
        registerBeanList = registerDao.query();
        if (registerBeanList.size() == 0) {
            registerBeanList.add(new PersonalRegisterBean());
        }
        registerAdapter = new PersonalSettingAdapter(registerBeanList, this, this, "注册类");
        rcvPersonalSettingList.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvPersonalSettingList.setAdapter(registerAdapter);
    }

    public void initTitleList() {
        PersonalTitleDao titleDao = new PersonalTitleDao(this);
        titleBeanList = titleDao.query();
        if (titleBeanList.size() == 0) {
            titleBeanList.add(new PersonalTitleBean());
        }
        titleAdapter = new PersonalTitleAdapter(titleBeanList, this, this, "职称类");
        rcvPersonalSettingList.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvPersonalSettingList.setAdapter(titleAdapter);
    }

    public void initManagerList() {
        PersonalManagerDao managerDao = new PersonalManagerDao(this);
        managerBeanList = managerDao.query();
        if (managerBeanList.size() == 0) {
            managerBeanList.add(new PersonalManagerBean());
        }
        managerAdapter = new PersonalManagerAdapter(managerBeanList, this, this, "管理类");
        rcvPersonalSettingList.setLayoutManager(new FullyLinearLayoutManager(this));
        rcvPersonalSettingList.setAdapter(managerAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resitMajorName(PersonalMajorControl control) {
        switch (control.getType()) {
            case 0:
                PersonalAllDao allDao = new PersonalAllDao(this);
                switch (allType) {
                    case 1:
                        registerBeanList.get(control.getPosition()).setName(control.getAllBean().getMajor_name());
                        List<PersonalAllBean> rankList1 = allDao.queryWhere("major_name", registerBeanList.get(control.getPosition()).getName());
                        if (rankList1.size() <= 1) {
                            registerBeanList.get(control.getPosition()).setRank("此专业不分等级");
                            registerBeanList.get(control.getPosition()).setDetails(allDao.queryFirst("major_name", control.getAllBean().getMajor_name()).getId() + "");
                        } else {
                            registerBeanList.get(control.getPosition()).setRank("");
                        }
                        break;
                    case 2:
                        titleBeanList.get(control.getPosition()).setName(control.getAllBean().getMajor_name());
                        List<PersonalAllBean> rankList2 = allDao.queryWhere("major_name", titleBeanList.get(control.getPosition()).getName());
                        if (rankList2.size() <= 1) {
                            titleBeanList.get(control.getPosition()).setRank("此专业不分等级");
                            titleBeanList.get(control.getPosition()).setDetails(allDao.queryFirst("major_name", control.getAllBean().getMajor_name()).getId() + "");
                        } else {
                            titleBeanList.get(control.getPosition()).setRank("");
                        }
                        break;
                    case 3:
                        managerBeanList.get(control.getPosition()).setName(control.getAllBean().getMajor_name());
                        List<PersonalAllBean> rankList3 = allDao.queryWhere("major_name", managerBeanList.get(control.getPosition()).getName());
                        if (rankList3.size() <= 1) {
                            managerBeanList.get(control.getPosition()).setRank("此专业不分等级");
                            managerBeanList.get(control.getPosition()).setDetails(allDao.queryFirst("major_name", control.getAllBean().getMajor_name()).getId() + "");
                        } else {
                            managerBeanList.get(control.getPosition()).setRank("");
                        }
                        break;
                }
                break;
            case 1:
                switch (allType) {
                    case 1:
                        registerBeanList.get(control.getPosition()).setRank(control.getAllBean().getMajor_grade());
                        registerBeanList.get(control.getPosition()).setDetails(control.getAllBean().getMajor_name());
                        break;
                    case 2:
                        titleBeanList.get(control.getPosition()).setRank(control.getAllBean().getMajor_grade());
                        titleBeanList.get(control.getPosition()).setDetails(control.getAllBean().getMajor_name());
                        break;
                    case 3:
                        managerBeanList.get(control.getPosition()).setRank(control.getAllBean().getMajor_grade());
                        managerBeanList.get(control.getPosition()).setDetails(control.getAllBean().getMajor_name());
                        break;
                }
                break;
            case 2:
                showPop();
                position = control.getPosition();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCode.PERSONAL_SETTING_MAJOR:
                if (registerAdapter != null)
                    registerAdapter.notifyDataSetChanged();
                if (titleAdapter != null)
                    titleAdapter.notifyDataSetChanged();
                if (managerAdapter != null)
                    managerAdapter.notifyDataSetChanged();
                break;
            case RequestCode.PERSONAL_SETTING_MAJOR_RANK:
                if (registerAdapter != null)
                    registerAdapter.notifyDataSetChanged();
                if (titleAdapter != null)
                    titleAdapter.notifyDataSetChanged();
                if (managerAdapter != null)
                    managerAdapter.notifyDataSetChanged();
                break;
        }
    }

    PopupWindow popupWindow;

    /**
     * show单位pop
     */
    public void showPop() {
        popupWindow = new MyPopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style02, null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        TextView txtPopupWindowStyle1 = view.findViewById(R.id.txt_popup_window_style_1);
        TextView txtPopupWindowStyle2 = view.findViewById(R.id.txt_popup_window_style_2);
        TextView txtPopupWindowStyle3 = view.findViewById(R.id.txt_popup_window_style_3);
        TextView txtPopupWindowStyle4 = view.findViewById(R.id.txt_popup_window_style_4);
        TextView txtPopupWindowStyle5 = view.findViewById(R.id.txt_popup_window_style_5);
        TextView txtPopupWindowStyle6 = view.findViewById(R.id.txt_popup_window_style_6);
        TextView txtPopupWindowStyle7 = view.findViewById(R.id.txt_popup_window_style_7);
        TextView txtPopupWindowStyle8 = view.findViewById(R.id.txt_popup_window_style_8);

        txtPopupWindowStyle1.setOnClickListener(this);
        txtPopupWindowStyle2.setOnClickListener(this);
        txtPopupWindowStyle3.setOnClickListener(this);
        txtPopupWindowStyle4.setOnClickListener(this);
        txtPopupWindowStyle5.setOnClickListener(this);
        txtPopupWindowStyle6.setOnClickListener(this);
        txtPopupWindowStyle7.setOnClickListener(this);
        txtPopupWindowStyle8.setOnClickListener(this);
        popupWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_personal_setting, null), Gravity.BOTTOM, 0, 0);
    }

    public void addList(int type) {
        switch (type) {
            case 1:
                if (registerBeanList.size() < 5) {
                    registerBeanList.add(new PersonalRegisterBean());
                    registerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "最多添加五条数据！", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (titleBeanList.size() < 5) {
                    titleBeanList.add(new PersonalTitleBean());
                    titleAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "最多添加五条数据！", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (managerBeanList.size() < 5) {
                    managerBeanList.add(new PersonalManagerBean());
                    managerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "最多添加五条数据！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void confirm(int type) {
        switch (type) {
            case 1:
                for (int i = 0; i < registerBeanList.size(); i++) {
                    if (registerBeanList.get(i).getName() == null || registerBeanList.get(i).getName().equals("")) {
                        Toast.makeText(this, "请先补完缺失内容，或删除该项要求", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
                registerDao.clearAll();
                registerDao.addAll(registerBeanList);
                finish();
                Toast.makeText(this, "已保存设置", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                for (int i = 0; i < titleBeanList.size(); i++) {
                    if (titleBeanList.get(i).getName() == null || titleBeanList.get(i).getName().equals("")) {
                        Toast.makeText(this, "请先补完缺失内容，或删除该项要求", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                PersonalTitleDao titleDao = new PersonalTitleDao(this);
                titleDao.clearAll();
                titleDao.addAll(titleBeanList);
                Toast.makeText(this, "已保存设置", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case 3:
                for (int i = 0; i < managerBeanList.size(); i++) {
                    if (managerBeanList.get(i).getName() == null || managerBeanList.get(i).getName().equals("")) {
                        Toast.makeText(this, "请先补完缺失内容，或删除该项要求", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                PersonalManagerDao managerDao = new PersonalManagerDao(this);
                managerDao.clearAll();
                managerDao.addAll(managerBeanList);
                Toast.makeText(this, "已保存设置", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
