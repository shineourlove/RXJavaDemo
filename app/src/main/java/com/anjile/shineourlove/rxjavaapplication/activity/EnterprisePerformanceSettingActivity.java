package com.anjile.shineourlove.rxjavaapplication.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingBean;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingDao;
import com.anjile.shineourlove.rxjavaapplication.popup.MyPopupWindow;
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;

import org.feezu.liuli.timeselector.view.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterprisePerformanceSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_enterprise_performance_setting_start)
    TextView txtEnterprisePerformanceSettingStart;
    @BindView(R.id.txt_enterprise_performance_setting_end)
    TextView txtEnterprisePerformanceSettingEnd;
    @BindView(R.id.txt_enterprise_performance_setting_use)
    TextView txtEnterprisePerformanceSettingUse;
    @BindView(R.id.ll_enterprise_performance_setting_use)
    LinearLayout llEnterprisePerformanceSettingUse;
    @BindView(R.id.edt_enterprise_performance_setting_scale)
    EditText edtEnterprisePerformanceSettingScale;
    @BindView(R.id.txt_enterprise_performance_setting_unit)
    TextView txtEnterprisePerformanceSettingUnit;
    @BindView(R.id.btn_enterprise_performance_setting_confirm)
    Button btnEnterprisePerformanceSettingConfirm;
    @BindView(R.id.rb_enterprise_performance_setting_number_1)
    RadioButton rbEnterprisePerformanceSettingNumber1;
    @BindView(R.id.rb_enterprise_performance_setting_number_2)
    RadioButton rbEnterprisePerformanceSettingNumber2;
    @BindView(R.id.rb_enterprise_performance_setting_number_3)
    RadioButton rbEnterprisePerformanceSettingNumber3;
    @BindView(R.id.edt_enterprise_performance_setting_number)
    EditText edtEnterprisePerformanceSettingNumber;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_enterprise_performance_setting);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText("企业业绩");
        txtTopStatusBarRight.setText("清空");
        txtTopStatusBarLeft.setOnClickListener(this);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);
        initInfo();
        loadLocalData();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_top_status_bar_left:
                backTo();
                break;
            case R.id.img_top_status_bar_back:
                backTo();
                break;
            case R.id.txt_top_status_bar_right:
                clearLocalData();
                break;
            case R.id.ll_enterprise_performance_setting_use:
                Intent intent = new Intent(this, ProjectSelectActivity.class);
                startActivityForResult(intent, RequestCode.PERFORMANCE_SETTING_PROJECT_SELECT);
                break;
            case R.id.btn_enterprise_performance_setting_confirm:
                onConfirm();
                break;
            case R.id.txt_enterprise_performance_setting_start:
                showSimpleHintDialog(0);
                break;
            case R.id.txt_enterprise_performance_setting_end:
                showSimpleHintDialog(1);
                break;
            case R.id.txt_enterprise_performance_setting_unit:
                showPop();
                break;
            case R.id.txt_popup_window_style_1:
                txtEnterprisePerformanceSettingUnit.setText("万元");
                popupWindow.dismiss();
                break;
            case R.id.txt_popup_window_style_2:
                txtEnterprisePerformanceSettingUnit.setText("平方米");
                popupWindow.dismiss();
                break;
            case R.id.txt_popup_window_style_3:
                txtEnterprisePerformanceSettingUnit.setText("立方米");
                popupWindow.dismiss();
                break;
            case R.id.txt_popup_window_style_4:
                txtEnterprisePerformanceSettingUnit.setText("米");
                popupWindow.dismiss();
                break;
            case R.id.edt_enterprise_performance_setting_number:
                rbEnterprisePerformanceSettingNumber1.setChecked(false);
                rbEnterprisePerformanceSettingNumber2.setChecked(false);
                rbEnterprisePerformanceSettingNumber3.setChecked(false);
                break;
        }
    }

    /**
     * 初始化基础信息
     */
    public void initInfo() {
        txtEnterprisePerformanceSettingStart.setText("开始时间");
        txtEnterprisePerformanceSettingEnd.setText("结束时间");
        txtEnterprisePerformanceSettingUse.setText("工程用途");
        txtEnterprisePerformanceSettingUnit.setText("选择单位");
        rbEnterprisePerformanceSettingNumber1.setChecked(false);

        llEnterprisePerformanceSettingUse.setOnClickListener(this);
        btnEnterprisePerformanceSettingConfirm.setOnClickListener(this);
        txtEnterprisePerformanceSettingStart.setOnClickListener(this);
        txtEnterprisePerformanceSettingEnd.setOnClickListener(this);
        txtEnterprisePerformanceSettingUnit.setOnClickListener(this);
        edtEnterprisePerformanceSettingNumber.setOnClickListener(this);
        rbEnterprisePerformanceSettingNumber1.setOnCheckedChangeListener(this);
        rbEnterprisePerformanceSettingNumber2.setOnCheckedChangeListener(this);
        rbEnterprisePerformanceSettingNumber3.setOnCheckedChangeListener(this);
        /**
         * 业绩规模的输入框监听
         */
        edtEnterprisePerformanceSettingScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    scale = 0;
                } else {
                    scale = Integer.parseInt(editable.toString().trim());
                }
            }
        });

        /**
         * 业绩个数输入框监听
         */
        edtEnterprisePerformanceSettingNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    rbEnterprisePerformanceSettingNumber1.setChecked(true);
                    check = 1;
                } else {
                    check = Integer.parseInt(editable.toString().trim());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCode.PERFORMANCE_SETTING_PROJECT_SELECT://此处刷新页面
                loadLocalData();
                break;
        }
    }

    /**
     * 加载本地数据
     */
    public void loadLocalData() {
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        List<EnterprisePerformanceSettingBean> queryList = settingDao.query();
        if (queryList != null && queryList.size() > 0) {
            if (queryList.get(0).getUse() != null) {
                if (queryList.get(0).getUse().equals("")) {
                    txtEnterprisePerformanceSettingUse.setText("");

                } else {
                    String[] uses = queryList.get(0).getUse().split("[,]");
                    txtEnterprisePerformanceSettingUse.setText("已选择" + uses.length + "项用途");
                }
            } else {
                txtEnterprisePerformanceSettingUse.setText("");
            }
            if (queryList.get(0).getStart() != null && !queryList.get(0).getStart().equals("") && !queryList.get(0).getStart().equals("0")) {
                txtEnterprisePerformanceSettingStart.setText(DateFormatTime.getTimeYearMonth(Long.parseLong(queryList.get(0).getStart())));
            } else {
                txtEnterprisePerformanceSettingStart.setText("");
            }
            if (queryList.get(0).getEnd() != null && !queryList.get(0).getEnd().equals("") && !queryList.get(0).getEnd().equals("0")) {
                txtEnterprisePerformanceSettingEnd.setText(DateFormatTime.getTimeYearMonth(Long.parseLong(queryList.get(0).getEnd())));
            } else {
                txtEnterprisePerformanceSettingEnd.setText("");
            }
            if (queryList.get(0).getNumber() > 0) {
                if (queryList.get(0).getNumber() < 2)
                    rbEnterprisePerformanceSettingNumber1.setChecked(true);
                else if (queryList.get(0).getNumber() == 2)
                    rbEnterprisePerformanceSettingNumber2.setChecked(true);
                else if (queryList.get(0).getNumber() == 3)
                    rbEnterprisePerformanceSettingNumber3.setChecked(true);
                else if (queryList.get(0).getNumber() > 3)
                    edtEnterprisePerformanceSettingNumber.setText(queryList.get(0).getNumber() + "");
            } else {
                rbEnterprisePerformanceSettingNumber1.setChecked(true);
            }
            if (queryList.get(0).getScale() > 0)
                edtEnterprisePerformanceSettingScale.setText(queryList.get(0).getScale() + "");
            else
                edtEnterprisePerformanceSettingScale.setText("0");
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("0"))
                txtEnterprisePerformanceSettingUnit.setText("万元");
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("1"))
                txtEnterprisePerformanceSettingUnit.setText("平方米");
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("2"))
                txtEnterprisePerformanceSettingUnit.setText("立方米");
            if (queryList.get(0).getUnit() != null && queryList.get(0).getUnit().equals("3"))
                txtEnterprisePerformanceSettingUnit.setText("米");
        } else {
            txtEnterprisePerformanceSettingUse.setText("");
            txtEnterprisePerformanceSettingStart.setText("");
            txtEnterprisePerformanceSettingEnd.setText("");
            edtEnterprisePerformanceSettingNumber.setText("");
            rbEnterprisePerformanceSettingNumber1.setChecked(true);
            txtEnterprisePerformanceSettingUnit.setText("万元");
        }
    }

    public void clearLocalData() {
        new EnterprisePerformanceSettingDao(this).clearAll();
        Toast.makeText(this, "已清空设置", Toast.LENGTH_SHORT).show();
        loadLocalData();
    }

    private String yearStart = "", monthStart = "", yearEnd = "", monthEnd = "";

    /**
     * 时间选择器
     *
     * @param type 类型 0开始 1结束
     */
    public void showSimpleHintDialog(final int type) {
        final Dialog dialog = new Dialog(this, R.style.date_dialog);

        View view = LayoutInflater.from(this).inflate(R.layout.date_picker_dialog_layout, null);
        PickerView pickerYear = view.findViewById(R.id.pv_date_picker_dialog_year);
        PickerView pickerMonth = view.findViewById(R.id.pv_date_picker_dialog_month);
        dialog.setContentView(view);
        List<String> listYear = new ArrayList<>();
        List<String> listMonth = new ArrayList<>();
        Log.i("date_picker", "showSimpleHintDialog: " + DateFormatTime.getYearFromCurrent(System.currentTimeMillis()));
        for (int i = 0; i < 60; i++) {
            listYear.add(DateFormatTime.getYearFromCurrent(System.currentTimeMillis()) - i + "年");
        }
        for (int i = 1; i <= 12; i++) {
            listMonth.add(i + "月");
        }

        pickerYear.setData(listYear);
        pickerMonth.setData(listMonth);

        pickerYear.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                if (type == 0)
                    yearStart = text;
                else
                    yearEnd = text;
            }
        });
        pickerMonth.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                if (type == 0)
                    monthStart = text;
                else
                    monthEnd = text;
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.getDecorView().setPadding(36, 0, 36, 18); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (type == 0) {
                    if (!monthStart.equals("") && !yearStart.equals("")) {
                        if (Integer.parseInt(monthStart.replace("月", "")) > 9)
                            txtEnterprisePerformanceSettingStart.setText(yearStart + " " + monthStart);
                        else {
                            txtEnterprisePerformanceSettingStart.setText(yearStart + " 0" + monthStart);
                        }
                    }
                } else {
                    if (!monthEnd.equals("") && !yearEnd.equals("")) {
                        if (Integer.parseInt(monthEnd.replace("月", "")) > 9)
                            txtEnterprisePerformanceSettingEnd.setText(yearEnd + " " + monthEnd);
                        else {
                            txtEnterprisePerformanceSettingEnd.setText(yearEnd + " 0" + monthEnd);
                        }
                    }
                }
            }
        });
    }

    private int check = 1;
    private int scale = 0;

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_enterprise_performance_setting_number_1:
                if (b) {
                    check = 1;
                    edtEnterprisePerformanceSettingNumber.setText("");
                }
                break;
            case R.id.rb_enterprise_performance_setting_number_2:
                if (b) {
                    check = 2;
                    edtEnterprisePerformanceSettingNumber.setText("");
                }
                break;
            case R.id.rb_enterprise_performance_setting_number_3:
                if (b) {
                    check = 3;
                    edtEnterprisePerformanceSettingNumber.setText("");
                }
                break;
        }
    }

    MyPopupWindow popupWindow;

    /**
     * show单位pop
     */
    public void showPop() {
        popupWindow = new MyPopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        TextView txtPopupWindowStyle1 = view.findViewById(R.id.txt_popup_window_style_1);
        TextView txtPopupWindowStyle2 = view.findViewById(R.id.txt_popup_window_style_2);
        TextView txtPopupWindowStyle3 = view.findViewById(R.id.txt_popup_window_style_3);
        TextView txtPopupWindowStyle4 = view.findViewById(R.id.txt_popup_window_style_4);
        txtPopupWindowStyle1.setOnClickListener(this);
        txtPopupWindowStyle2.setOnClickListener(this);
        txtPopupWindowStyle3.setOnClickListener(this);
        txtPopupWindowStyle4.setOnClickListener(this);
        popupWindow.showAsDropDown(txtEnterprisePerformanceSettingUnit);
    }

    /**
     * 重写返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                backTo();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 确定按钮点击事件
     */
    public void onConfirm() {
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        long startDate = 0l;
        long endDate = 0l;
        if (!txtEnterprisePerformanceSettingStart.getText().toString().trim().equals("")) {
            startDate = DateFormatTime.getForYearsMonthLong(txtEnterprisePerformanceSettingStart.getText().toString().replace("年", "-").replace("月", ""));
        }
        if (!txtEnterprisePerformanceSettingEnd.getText().toString().trim().equals("")) {
            endDate = DateFormatTime.getForYearsMonthLong(txtEnterprisePerformanceSettingEnd.getText().toString().replace("年", "-").replace("月", ""));
        }
        settingDao.updateOnly("start", startDate + "");
        settingDao.updateOnly("end", endDate + "");
        settingDao.updateOnly("number", check + "");
        settingDao.updateOnly("scale", scale + "");
        settingDao.updateOnly("save", 1 + "");
        if (txtEnterprisePerformanceSettingUnit.getText().toString().trim().equals("万元")) {
            settingDao.updateOnly("unit", "0");
        } else if (txtEnterprisePerformanceSettingUnit.getText().toString().trim().equals("平方米")) {
            settingDao.updateOnly("unit", "1");
        } else if (txtEnterprisePerformanceSettingUnit.getText().toString().trim().equals("立方米")) {
            settingDao.updateOnly("unit", "2");
        } else {
            settingDao.updateOnly("unit", "3");
        }
        setResult(ResultCode.ENTERPRISE_PERFORMANCE_SETTING_CONFIRM);
        finish();
    }

    public void backTo() {
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        if (settingDao.query().size() > 0 && settingDao.query().get(0).getSave() > 0) {
        } else {
            settingDao.clearAll();
        }
        setResult(ResultCode.ENTERPRISE_PERFORMANCE_SETTING_CANCEL);
        finish();
    }
}
