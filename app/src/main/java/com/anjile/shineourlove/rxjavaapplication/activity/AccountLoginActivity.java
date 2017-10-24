package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    TextView txtAccountLoginLogin;

    private int phoneNum;
    private int securityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_account_login);
        ButterKnife.bind(this);
        initAgreement();
        initLogin();
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_account_login_get_security_code:
                if (edtAccountLoginPhoneNumber.getText().length() == 13) {
                    phoneNum = Integer.parseInt(String.valueOf(edtAccountLoginPhoneNumber.getText()));
                }
                sendOutPhone();
                break;
            case R.id.txt_account_login_login:
                loginCheck(edtAccountLoginPhoneNumber.getText().toString().trim(), edtAccountLoginSecurityCode.getText().toString().trim());
                //checkSecurity("15000000000", "110022");
                break;
        }
    }

    private void initAgreement() {
        txtAccountLoginAgreementAndTerms.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "我已阅读并同意建讯之家《用户服务协议》和《隐私条款》";
        SpannableString styledText = new SpannableString(text);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_blue), 12, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 18, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_blue), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(this, R.style.style_login_gray), 25, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

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
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.i("agreement_text_view", "onClick: ");
                Toast.makeText(AccountLoginActivity.this, "touch", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.blue_line));
                ds.setUnderlineText(false);
            }
        }, 21, 25, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        txtAccountLoginAgreementAndTerms.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void initLogin() {
        txtAccountLoginGetSecurityCode.setOnClickListener(this);
        txtAccountLoginLogin.setOnClickListener(this);
    }

    /**
     * 发送验证码
     */
    private void sendOutPhone() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.251:8080/")
                .build();

        Api api = retrofit.create(Api.class);
        Call call = api.securityCodePostCall("13637897256");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("retrofit_test", "发送验证码: " + response.body().string());
                    Toast.makeText(AccountLoginActivity.this, "验证码已发出", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void checkSecurity(String phone, String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.251:8080/")
                .build();

        Api api = retrofit.create(Api.class);
        Call call = api.checkSecurityCodePostCall(phone, code);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("retrofit_test", "校对验证码: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.251:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
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
                        if (value.getStatus().equals("1")) {
                            startActivity(new Intent(AccountLoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AccountLoginActivity.this, "手机号或验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("retrofit_test", "onError: ");

                    }

                    @Override
                    public void onComplete() {
                        Log.i("retrofit_test", "onComplete: ");
                    }
                });
    }
}
