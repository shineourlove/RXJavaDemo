package com.anjile.shineourlove.rxjavaapplication.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.BaseActivity;
import com.anjile.shineourlove.rxjavaapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/24.
 */

public class BudgetProgramActivity extends BaseActivity {
    @BindView(R.id.txt_budget_program_budget)
    TextView txtBudgetProgramBudget;
    @BindView(R.id.txt_budget_program_program)
    TextView txtBudgetProgramProgram;
    @BindView(R.id.txt_budget_program_budget_line)
    TextView txtBudgetProgramBudgetLine;
    @BindView(R.id.txt_budget_program_program_line)
    TextView txtBudgetProgramProgramLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void initBasic() {
        setContentView(R.layout.activity_budget_program_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void viewClick(View v) {

    }
}
