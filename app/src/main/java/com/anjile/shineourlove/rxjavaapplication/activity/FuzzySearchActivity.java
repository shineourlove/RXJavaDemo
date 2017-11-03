package com.anjile.shineourlove.rxjavaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.api.Api;
import com.anjile.shineourlove.rxjavaapplication.db.UserInfoDao;
import com.anjile.shineourlove.rxjavaapplication.entity.CheckSecurityCodeBean;
import com.anjile.shineourlove.rxjavaapplication.entity.EnterpriseSearchEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuzzySearchActivity extends BaseActivity {

    @BindView(R.id.img_fuzzy_search_back)
    ImageView imgFuzzySearchBack;
    @BindView(R.id.edt_fuzzy_search_all)
    EditText edtFuzzySearchAll;
    @BindView(R.id.rl_fragment_home_search)
    LinearLayout rlFragmentHomeSearch;
    @BindView(R.id.txt_fuzzy_search_search)
    TextView txtFuzzySearchSearch;
    @BindView(R.id.txt_fuzzy_search_clear)
    TextView txtFuzzySearchClear;
    @BindView(R.id.sv_fuzzy_search_history)
    SwipeMenuRecyclerView svFuzzySearchHistory;
    @BindView(R.id.rl_fuzzy_search_history)
    LinearLayout rlFuzzySearchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_fuzzy_search);
        ButterKnife.bind(this);

        imgFuzzySearchBack.setOnClickListener(this);
        txtFuzzySearchSearch.setOnClickListener(this);
        txtFuzzySearchClear.setOnClickListener(this);
    }

    @Override
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.img_fuzzy_search_back:
                finish();
                break;
            case R.id.txt_fuzzy_search_search://搜索
                onSearch();
                break;
            case R.id.txt_fuzzy_search_clear://清空搜索记录

                break;
        }
    }

    String searchText = "";

    public void onSearch() {
        searchText = edtFuzzySearchAll.getText().toString();
        if (searchText.equals(""))
            return;
        String[] listSearch = searchText.split(" ");
        StringBuilder searchBuilder = new StringBuilder();
        for (int i = 0; i < listSearch.length; i++) {
            if (!listSearch[i].equals("")) {
                searchBuilder.append(listSearch[i]);
                if (i != listSearch.length - 1)
                    searchBuilder.append(" ");
            }
        }
        //跳转到查询结果的界面
        Intent intent = new Intent(this, AptitudeQueryActivity.class);
        intent.putExtra("parameter", searchBuilder.toString());
        startActivity(intent);
    }
}
