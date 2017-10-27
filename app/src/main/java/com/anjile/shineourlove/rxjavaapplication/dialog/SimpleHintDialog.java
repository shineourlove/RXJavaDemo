package com.anjile.shineourlove.rxjavaapplication.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anjile.shineourlove.rxjavaapplication.R;

/**
 * Created by zjz on 2017/2/7.
 */
public class SimpleHintDialog {
    Context context;
    Dialog dialog;
    Activity activity;

    public SimpleHintDialog(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        dialog = new Dialog(context, R.style.dialog);
    }

    public SimpleHintDialog() {
    }

    public void showSimpleHintDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_choose_dialog_layout, null);
        TextView txtUp = view.findViewById(R.id.txt_bottom_choose_dialog_up);
        TextView txtMid = view.findViewById(R.id.txt_bottom_choose_dialog_mid);
        TextView txtDown = view.findViewById(R.id.txt_bottom_choose_dialog_down);
        dialog.setContentView(view);
        txtDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
