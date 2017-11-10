package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.common.RequestCode;
import com.anjile.shineourlove.rxjavaapplication.common.ResultCode;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedBean;
import com.anjile.shineourlove.rxjavaapplication.db.AptitudeSelectedDao;
import com.anjile.shineourlove.rxjavaapplication.db.EnterprisePerformanceSettingDao;
import com.anjile.shineourlove.rxjavaapplication.db.EnterpriseQueryDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalManagerDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalRegisterDao;
import com.anjile.shineourlove.rxjavaapplication.db.PersonalTitleDao;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.AptitudeBackControl;
import com.anjile.shineourlove.rxjavaapplication.eventbuscontrol.BackstageDownloadControl;
import com.anjile.shineourlove.rxjavaapplication.service.BackstageDownloadService;
import com.anjile.shineourlove.rxjavaapplication.view.DeleteEditTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.anjile.shineourlove.rxjavaapplication.common.RequestCode.QUERY_ENTERPRISE_PERFORMANCE_SETTING;
import static com.anjile.shineourlove.rxjavaapplication.common.RequestCode.QUERY_ENTERPRISE_PERSONAL;
import static com.anjile.shineourlove.rxjavaapplication.common.RequestCode.QUERY_ENTERPRISE_RESULT;

public class QueryActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.ll_query_company_query)
    LinearLayout llQueryCompanyQuery;
    @BindView(R.id.ll_query_performance_query)
    LinearLayout llQueryPerformanceQuery;
    @BindView(R.id.txt_query_company_query)
    TextView txtQueryCompanyQuery;
    @BindView(R.id.txt_query_performance_query)
    TextView txtQueryPerformanceQuery;
    @BindView(R.id.txt_query_company_query_line)
    TextView txtQueryCompanyQueryLine;
    @BindView(R.id.txt_query_performance_query_line)
    TextView txtQueryPerformanceQueryLine;
    @BindView(R.id.img_top_status_bar_back)
    ImageView imgTopStatusBarBack;
    @BindView(R.id.txt_top_status_bar_title)
    TextView txtTopStatusBarTitle;
    @BindView(R.id.txt_top_status_bar_left)
    TextView txtTopStatusBarLeft;

    //企业查询
    @BindView(R.id.edt_query_enterprise_enterprise_name)
    EditText edtQueryEnterpriseEnterpriseName;
    @BindView(R.id.rl_query_enterprise_area)
    RelativeLayout rlQueryEnterpriseArea;
    @BindView(R.id.txt_query_area_province)
    TextView txtQueryAreaProvince;
    @BindView(R.id.rb_query_enterprise_unlimited)
    RadioButton rbQueryEnterpriseUnlimited;
    @BindView(R.id.rb_query_enterprise_ecdemic)
    RadioButton rbQueryEnterpriseEcdemic;
    @BindView(R.id.rb_query_enterprise_local)
    RadioButton rbQueryEnterpriseLocal;
    @BindView(R.id.edt_query_enterprise_legal_person)
    EditText edtQueryEnterpriseLegalPerson;
    @BindView(R.id.rl_query_enterprise_qualification)
    RelativeLayout rlQueryEnterpriseQualification;
    @BindView(R.id.txt_query_enterprise_aptitude)
    TextView txtQueryEnterpriseAptitude;
    @BindView(R.id.rl_query_enterprise_performance)
    RelativeLayout rlQueryEnterprisePerformance;
    @BindView(R.id.rl_query_enterprise_person)
    RelativeLayout rlQueryEnterprisePerson;
    @BindView(R.id.btn_query_company_query_now)
    Button btnQueryCompanyQueryNow;
    @BindView(R.id.txt_query_enterprise_performance)
    TextView txtQueryEnterprisePerformance;
    @BindView(R.id.txt_query_enterprise_person)
    TextView txtQueryEnterprisePerson;

    //业绩查询
    @BindView(R.id.edt_query_performance_project_name)
    EditText edtQueryPerformanceProjectName;
    @BindView(R.id.edt_query_performance_enterprise_name)
    EditText edtQueryPerformanceEnterpriseName;
    @BindView(R.id.edt_query_performance_builder_name)
    EditText edtQueryPerformanceBuilderName;
    @BindView(R.id.rl_query_performance_area)
    RelativeLayout rlQueryPerformanceArea;
    @BindView(R.id.txt_query_finish_time)
    TextView txtQueryFinishTime;
    @BindView(R.id.txt_query_start_time)
    TextView txtQueryStartTime;
    @BindView(R.id.txt_query_check_unit)
    TextView txtQueryCheckUnit;
    @BindView(R.id.txt_query_input_scale)
    TextView txtQueryInputScale;
    @BindView(R.id.btn_query_performance_query_now)
    Button btnQueryPerformanceQueryNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        imgTopStatusBarBack.setImageResource(R.drawable.back);
        txtTopStatusBarTitle.setText(R.string.search_hint);
        txtTopStatusBarLeft.setOnClickListener(this);

        txtQueryCompanyQuery.setOnClickListener(this);
        txtQueryPerformanceQuery.setOnClickListener(this);
        llQueryCompanyQuery.setVisibility(View.VISIBLE);
        llQueryPerformanceQuery.setVisibility(View.GONE);
        txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
        txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
        Intent intentBack = new Intent(this, BackstageDownloadService.class);
        startService(intentBack);

        setEnterpriseQuery();
        initEditListener();
        loadLocalData();
        loadEnterpriseName();
        loadLegalPerson();
        loadRequire();
        loadAptitude();
        loadPerformance();
        loadPersonal();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_query_company_query:
                llQueryCompanyQuery.setVisibility(View.VISIBLE);
                llQueryPerformanceQuery.setVisibility(View.GONE);
                txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
                txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.txt_query_performance_query:
                llQueryCompanyQuery.setVisibility(View.GONE);
                llQueryPerformanceQuery.setVisibility(View.VISIBLE);
                txtQueryCompanyQueryLine.setBackgroundColor(getResources().getColor(R.color.white));
                txtQueryPerformanceQueryLine.setBackgroundColor(getResources().getColor(R.color.blue_line));
                break;
            case R.id.rl_query_enterprise_qualification://企业查询-资质选择
                AptitudeSelectedDao selectedDao = new AptitudeSelectedDao(this);
                if (selectedDao.query().size() > 0) {
                    Intent intent = new Intent(this, EnterpriseAptitudeActivity.class);
                    intent.putExtra("type", 0);
                    startActivityForResult(intent, RequestCode.QUERY_ENTERPRISE_APTITUDE_ACTIVITY);
                } else {
                    Intent intent = new Intent(this, AptitudeSelectActivity.class);
                    startActivityForResult(intent, RequestCode.QUERY_ENTERPRISE_APTITUDE_SELECT);
                }
                break;
            case R.id.rl_query_enterprise_performance://企业查询-业绩选择
                Intent intentPerformance = new Intent(this, EnterprisePerformanceSettingActivity.class);
                startActivityForResult(intentPerformance, QUERY_ENTERPRISE_PERFORMANCE_SETTING);
                break;
            case R.id.rl_query_enterprise_area://企业查询-省市地区
                Intent intentArea = new Intent(this, ProvinceSelectActivity.class);
                startActivityForResult(intentArea, RequestCode.QUERY_ENTERPRISE_AREA_PROVINCE);
                break;
            case R.id.rl_query_enterprise_person:
                Intent intentPersonal = new Intent(this, PersonalSearchConditionActivity.class);
                startActivityForResult(intentPersonal, QUERY_ENTERPRISE_PERSONAL);
                break;
            case R.id.btn_query_company_query_now://企业查询-立即查询
                queryNow();
                break;
        }
    }

    //企业查询的基础操作
    public void setEnterpriseQuery() {
        rbQueryEnterpriseUnlimited.setOnCheckedChangeListener(this);
        rbQueryEnterpriseEcdemic.setOnCheckedChangeListener(this);
        rbQueryEnterpriseLocal.setOnCheckedChangeListener(this);

        rlQueryEnterpriseQualification.setOnClickListener(this);
        rlQueryEnterprisePerformance.setOnClickListener(this);
        rlQueryEnterpriseArea.setOnClickListener(this);
        btnQueryCompanyQueryNow.setOnClickListener(this);
        rlQueryEnterprisePerson.setOnClickListener(this);

        initAptitudeIndex();
    }


    /**
     * 选择变化 监听
     *
     * @param compoundButton radioButton
     * @param b              改变后的选择状态
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        switch (compoundButton.getId()) {
            case R.id.rb_query_enterprise_unlimited://不限
                if (b)
                    queryDao.updateOnly("require", "2");
                break;
            case R.id.rb_query_enterprise_ecdemic://外地
                if (b)
                    queryDao.updateOnly("require", "0");
                break;
            case R.id.rb_query_enterprise_local://本地
                if (b)
                    queryDao.updateOnly("require", "1");
                break;
        }
    }

    public void initAptitudeIndex() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new BackstageDownloadControl(0));
                EventBus.getDefault().post(new BackstageDownloadControl(1));
                EventBus.getDefault().post(new BackstageDownloadControl(4));
            }
        }, 50);
    }

    /**
     * 加载省市地区
     */
    private void loadLocalData() {
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        if (queryDao.query().size() > 0)
            txtQueryAreaProvince.setText(queryDao.query().get(0).getProvince());
    }

    private void loadRequire() {
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        if (queryDao.query().size() > 0)
            if (queryDao.query().get(0).getRequire() != null) {
                if (queryDao.query().get(0).getRequire().equals("0")) {
                    rbQueryEnterpriseUnlimited.setChecked(true);
                } else if (queryDao.query().get(0).getRequire().equals("1")) {
                    rbQueryEnterpriseEcdemic.setChecked(true);
                } else {
                    rbQueryEnterpriseLocal.setChecked(true);
                }
            } else {
                rbQueryEnterpriseUnlimited.setChecked(true);
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultCode.ENTERPRISE_AREA_PROVINCE:
                loadLocalData();
                break;
            case ResultCode.ENTERPRISE_PERFORMANCE_SETTING_CONFIRM:
                loadPerformance();
                break;
            case ResultCode.ENTERPRISE_PERFORMANCE_SETTING_CANCEL:
                loadPerformance();
                break;
            case QUERY_ENTERPRISE_RESULT:
                clearQueryDataBase();
                loadLocalData();
                loadEnterpriseName();
                loadLegalPerson();
                loadRequire();
                loadAptitude();
                loadPerformance();
                break;
            case QUERY_ENTERPRISE_PERSONAL:
                loadPersonal();
                break;
        }
        switch (requestCode) {
            case RequestCode.QUERY_ENTERPRISE_APTITUDE_SELECT:
                loadAptitude();
                break;
            case RequestCode.QUERY_ENTERPRISE_APTITUDE_ACTIVITY:
                loadAptitude();
                break;
        }
    }

    private void initEditListener() {
        edtQueryEnterpriseEnterpriseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                EnterpriseQueryDao queryDao = new EnterpriseQueryDao(QueryActivity.this);
                queryDao.updateOnly("name", editable.toString().trim());
            }
        });
        edtQueryEnterpriseLegalPerson.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                EnterpriseQueryDao queryDao = new EnterpriseQueryDao(QueryActivity.this);
                queryDao.updateOnly("legal_person", editable.toString().trim());
            }
        });
    }

    /**
     * 加载企业名称
     */
    private void loadEnterpriseName() {
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        if (queryDao.query().size() > 0)
            edtQueryEnterpriseEnterpriseName.setText(queryDao.query().get(0).getName());
    }

    /**
     * 加载法人名称
     */
    private void loadLegalPerson() {
        EnterpriseQueryDao queryDao = new EnterpriseQueryDao(this);
        if (queryDao.query().size() > 0)
            edtQueryEnterpriseLegalPerson.setText(queryDao.query().get(0).getLegal_person());
    }

    private void loadAptitude() {
        AptitudeSelectedDao selectedDao = new AptitudeSelectedDao(this);
        List<AptitudeSelectedBean> selectedBeanList = selectedDao.query();
        if (selectedBeanList.size() > 0) {
            txtQueryEnterpriseAptitude.setText("您已选择" + selectedBeanList.size() + "项资质");
        } else {
            txtQueryEnterpriseAptitude.setText(R.string.click_setting);
        }
    }

    /**
     * 刷新页面
     *
     * @param c 数据传输对象
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addTextControl(AptitudeBackControl c) {
        if (c.getType() == 0) {
            loadAptitude();
        }
    }

    /**
     * 加载业绩
     */
    private void loadPerformance() {
        EnterprisePerformanceSettingDao settingDao = new EnterprisePerformanceSettingDao(this);
        if (settingDao.query() != null && settingDao.query().size() > 0) {
            txtQueryEnterprisePerformance.setText("已保存业绩设置");
        } else {
            txtQueryEnterprisePerformance.setText("点击设置");
        }
    }

    /**
     * 加载人员
     */
    public void loadPersonal() {
        PersonalRegisterDao registerDao = new PersonalRegisterDao(this);
        PersonalTitleDao titleDao = new PersonalTitleDao(this);
        PersonalManagerDao managerDao = new PersonalManagerDao(this);
        txtQueryEnterprisePerson.setText("");
        if (registerDao.query() != null && registerDao.query().size() > 0) {
            txtQueryEnterprisePerson.setText("已保存人员设置");
        }
        if (titleDao.query() != null && titleDao.query().size() > 0) {
            txtQueryEnterprisePerson.setText("已保存人员设置");
        }
        if (managerDao.query() != null && managerDao.query().size() > 0) {
            txtQueryEnterprisePerson.setText("已保存人员设置");
        }
    }

    /**
     * 立即查询
     */
    public void queryNow() {
        if (!txtQueryAreaProvince.getText().toString().trim().equals("")) {
            EventBus.getDefault().post(new BackstageDownloadControl(2));
            Intent intent = new Intent(this, EnterpriseQueryResultActivity.class);
            startActivityForResult(intent, RequestCode.QUERY_ENTERPRISE_RESULT);
        } else {
            Toast.makeText(this, "请先选择省市！", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearQueryDataBase() {
        new AptitudeSelectedDao(this).clearAll();
        new EnterprisePerformanceSettingDao(this).clearAll();
        new EnterpriseQueryDao(this).clearAll();
    }
}
