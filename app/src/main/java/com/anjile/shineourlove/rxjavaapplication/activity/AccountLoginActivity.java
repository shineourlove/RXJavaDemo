package com.anjile.shineourlove.rxjavaapplication.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoBean;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.anjile.shineourlove.rxjavaapplication.manager.NetManager;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountLoginActivity extends BaseActivity {

    @BindView(R.id.txt_account_login_agreement_and_terms)
    TextView txtAccountLoginAgreementAndTerms;
    @BindView(R.id.edt_account_login_phone_number)
    EditText edtAccountLoginPhoneNumber;
    @BindView(R.id.edt_account_login_security_code)
    EditText edtAccountLoginSecurityCode;
    @BindView(R.id.txt_account_login_get_security_code)
    TextView txtAccountLoginGetSecurityCode;
    @BindView(R.id.txt_account_login_login)
    Button txtAccountLoginLogin;
    @BindView(R.id.cb_txt_account_login_agreement_and_terms)
    CheckBox cbTxtAccountLoginAgreementAndTerms;

    private String phoneNum;
    private int securityL = 0;
    private int phoneL = 0;
    private boolean check = false;
    private boolean security = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_account_login);
        ButterKnife.bind(this);

        txtAccountLoginGetSecurityCode.setTextColor(getResources().getColor(R.color.gray_word_hint));
        txtAccountLoginGetSecurityCode.setEnabled(false);

        txtAccountLoginLogin.setEnabled(false);
        txtAccountLoginLogin.setBackground(getDrawable(R.drawable.round_radius_50_blue_half));
        initAgreement();
        initLogin();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_account_login_get_security_code:
                phoneNum = edtAccountLoginPhoneNumber.getText().toString();
                sendOutPhone();
                showDialog();
                securityCodeDismiss();
                break;
            case R.id.txt_account_login_login:
                loginCheck(edtAccountLoginPhoneNumber.getText().toString().trim(), edtAccountLoginSecurityCode.getText().toString().trim());
                showDialog();
                break;
        }
    }

    private void initAgreement() {
        txtAccountLoginAgreementAndTerms.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "我已阅读并同意建讯之家《用户服务协议》";
        SpannableString styledText = new SpannableString(text);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_blue), 12, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 18, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_blue), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 25, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableStringBuilder builder = new SpannableStringBuilder(styledText);
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.i("agreement_text_view", "onClick: ");
                Toast.makeText(AccountLoginActivity.this, "click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.blue_line));
                ds.setUnderlineText(false);
            }
        }, 12, 18, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        txtAccountLoginAgreementAndTerms.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void initLogin() {
        txtAccountLoginGetSecurityCode.setOnClickListener(this);
        txtAccountLoginLogin.setOnClickListener(this);
        edtAccountLoginPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneL = editable.toString().length();
                checkSecurityCondition();
                checkCondition();
            }
        });
        edtAccountLoginSecurityCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                securityL = editable.toString().length();
                checkCondition();
            }
        });
        cbTxtAccountLoginAgreementAndTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                check = b;
                checkCondition();
            }
        });
    }

    public void checkCondition() {
        if (securityL == 6 && phoneL == 11 && check) {
            txtAccountLoginLogin.setBackground(getDrawable(R.drawable.round_radius_50_blue));
            txtAccountLoginLogin.setEnabled(true);
        } else {
            txtAccountLoginLogin.setBackground(getDrawable(R.drawable.round_radius_50_blue_half));
            txtAccountLoginLogin.setEnabled(false);
        }
    }

    public void checkSecurityCondition() {
        if (phoneL == 11 && security) {
            txtAccountLoginGetSecurityCode.setTextColor(getResources().getColor(R.color.blue_word));
            txtAccountLoginGetSecurityCode.setEnabled(true);
        } else {
            txtAccountLoginGetSecurityCode.setTextColor(getResources().getColor(R.color.gray_word_hint));
            txtAccountLoginGetSecurityCode.setEnabled(false);
        }
    }

    Timer timer;
    int timeCount = 60;

    public void securityCodeDismiss() {
        security = false;
        checkSecurityCondition();
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        timer = new Timer();
        timeCount = 60;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeCount--;
                handler.sendEmptyMessage(0);
            }
        }, 1000, 1000);
    }

    private static class AccountHandler extends Handler {
        WeakReference<AccountLoginActivity> mActivity;

        AccountHandler(AccountLoginActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final AccountLoginActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 0:
                    if (theActivity.timeCount <= 0) {
                        theActivity.timer.cancel();
                        theActivity.timer.purge();
                        theActivity.security = true;
                        theActivity.checkSecurityCondition();
                        theActivity.txtAccountLoginGetSecurityCode.setText("获取验证码");
                    } else {
                        theActivity.txtAccountLoginGetSecurityCode.setText(theActivity.timeCount + "秒");
                    }
                    break;
            }
        }
    }

    AccountHandler handler = new AccountHandler(this);

    /**
     * 发送验证码
     */
    private void sendOutPhone() {
        Api api = new NetManager().getApi();
        Log.i("ok_http_log", "phoneNum: " + phoneNum);
        api.loginCodeObservable(phoneNum + "").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckSecurityCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckSecurityCodeBean value) {
                        dialog.dismiss();
                        if (value.getStatus() == 1)
                            Toast.makeText(AccountLoginActivity.this, "验证码已发出", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AccountLoginActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        Toast.makeText(AccountLoginActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 校对验证码，登录
     *
     * @param phone 电话号码
     * @param code  验证码
     */
    private void loginCheck(String phone, String code) {
        Api api = new NetManager().getApi();
        api.loginPostObservable(phone, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckSecurityCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("retrofit_test", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(CheckSecurityCodeBean value) {
                        if (value.getStatus() == 1) {
                            saveUserInfo(value);
                            startActivity(new Intent(AccountLoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AccountLoginActivity.this, "手机号或验证码错误", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("retrofit_test", "onError: ");
                        dialog.dismiss();
                        Toast.makeText(AccountLoginActivity.this, "手机号或验证码错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("retrofit_test", "onComplete: ");
                    }
                });
    }

    public void saveUserInfo(CheckSecurityCodeBean value) {
        UserInfoDao infoDao = new UserInfoDao(this);
        infoDao.clearAll();
        int age = 18;
        if (!value.getData().get(0).getAge().equals(""))
            age = Integer.parseInt(value.getData().get(0).getAge());
        UserInfoBean infoBean = new UserInfoBean();
        infoBean.setToken(value.getToken());
        infoBean.setPhone(value.getData().get(0).getPhone());
        infoBean.setUserId(value.getData().get(0).getId() + "");
        infoBean.setName(value.getData().get(0).getName());
        infoBean.setGender(value.getData().get(0).getGender());
        infoBean.setAge(age);
        infoBean.setBirthday(value.getData().get(0).getBirthday());
        infoBean.setInterests(value.getData().get(0).getInterests());
        infoBean.setVip(value.getData().get(0).getVip());
        infoBean.setVipStartDate(value.getData().get(0).getVipStartDate());
        infoBean.setVipDateDue(value.getData().get(0).getVipDateDue());
        infoBean.setMail(value.getData().get(0).getMail());
        infoBean.setPhoto(value.getData().get(0).getPhoto());
        infoDao.add(infoBean);
    }

    Dialog dialog;

    public void showDialog() {
        dialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.rotation_hint_dialog, null);
        dialog.setContentView(view);
        dialog.show();
    }
}
