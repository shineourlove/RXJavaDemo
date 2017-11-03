package com.anjile.shineourlove.rxjavaapplication.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.WorkResumeBean;
import com.anjile.shineourlove.rxjavaapplication.db.WorkResumeDao;
import com.anjile.shineourlove.rxjavaapplication.utils.DateFormatTime;
import com.anjile.shineourlove.rxjavaapplication.view.FeedbackEditText;
import com.anjile.shineourlove.rxjavaapplication.view.NameEditText;

import org.feezu.liuli.timeselector.view.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobResumeActivity extends BaseActivity {

    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_right)
    TextView txtTopStatusBarRight;
    @BindView(R.id.edt_job_resume_company)
    NameEditText edtJobResumeCompany;
    @BindView(R.id.edt_job_resume_department)
    NameEditText edtJobResumeDepartment;
    @BindView(R.id.edt_job_resume_position)
    FeedbackEditText edtJobResumePosition;
    @BindView(R.id.edt_job_resume_start)
    TextView edtJobResumeStart;
    @BindView(R.id.edt_job_resume_end)
    TextView edtJobResumeEnd;
    @BindView(R.id.edt_job_resume_content)
    FeedbackEditText edtJobResumeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_job_resume);
        ButterKnife.bind(this);

        txtTopStatusBarTitle.setText(R.string.work_resume);
        txtTopStatusBarRight.setText(R.string.confirm);
        imgTopStatusBarBack.setOnClickListener(this);
        txtTopStatusBarLeft.setOnClickListener(this);
        txtTopStatusBarRight.setOnClickListener(this);

        edtJobResumeStart.setOnClickListener(this);
        edtJobResumeEnd.setOnClickListener(this);

        edtJobResumeCompany.setMaxLen(40);
        edtJobResumeDepartment.setMaxLen(30);
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
                storeUserResume();
                break;
            case R.id.edt_job_resume_start:
                showSimpleHintDialog(0);
                break;
            case R.id.edt_job_resume_end:
                showSimpleHintDialog(1);
                break;
        }
    }

    public void storeUserResume() {

        if (edtJobResumeCompany.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入公司名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtJobResumeDepartment.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入所在部门", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtJobResumePosition.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入职位名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtJobResumeContent.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请简述工作内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtJobResumeStart.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入正确的开始时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtJobResumeEnd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入正确的结束时间", Toast.LENGTH_SHORT).show();
            return;
        }
        WorkResumeDao resumeDao = new WorkResumeDao(this);
        if (getIntent().getIntExtra("tag", 0) == 1) {
            WorkResumeBean bean = new WorkResumeBean();
            bean.setCompany(edtJobResumeCompany.getText().toString());
            bean.setDepartment(edtJobResumeDepartment.getText().toString());
            bean.setPosition(edtJobResumePosition.getText().toString());
            bean.setContent(edtJobResumeContent.getText().toString());
            String startDay = edtJobResumeStart.getText().toString().trim().replace("年", "-").replace("月", "-") + "01";
            String endDay = edtJobResumeEnd.getText().toString().trim().replace("年", "-").replace("月", "-") + "01";
            long start = DateFormatTime.getTimeDataForYearsMonthDayLong(startDay);
            long end = DateFormatTime.getTimeDataForYearsMonthDayLong(endDay);
            Log.i("date_picker", "storeUserResume: " + start + "/// " + end);
            bean.setStart(start + "");
            bean.setEnd(end + "");
            resumeDao.add(bean);
            setResult(ResultCode.AUTHENTICATION_ADD_RESUME);
            finish();
        } else {

        }
    }

    private String yearStart = "", monthStart = "", yearEnd = "", monthEnd = "";

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
                    if (!monthStart.equals("")&&!yearStart.equals("")) {
                        if (Integer.parseInt(monthStart.replace("月", "")) > 9)
                            edtJobResumeStart.setText(yearStart + " " + monthStart);
                        else {
                            edtJobResumeStart.setText(yearStart + " 0" + monthStart);
                        }
                    }
                } else {
                    if (!monthEnd.equals("")&&!yearEnd.equals("")) {
                        if (Integer.parseInt(monthEnd.replace("月", "")) > 9)
                            edtJobResumeEnd.setText(yearEnd + " " + monthEnd);
                        else {
                            edtJobResumeEnd.setText(yearEnd + " 0" + monthEnd);
                        }
                    }
                }
            }
        });
    }
}
