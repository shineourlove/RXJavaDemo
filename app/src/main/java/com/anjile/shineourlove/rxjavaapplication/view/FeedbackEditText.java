package com.anjile.shineourlove.rxjavaapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by shineourlove on 2017/3/9.
 * use to input Chinese
 */
public class FeedbackEditText extends EditText {

    public FeedbackEditText(Context context) {
        super(context);
    }

    public FeedbackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedbackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 输入法
     *
     * @param outAttrs
     * @return
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new feedInputConnecttion(super.onCreateInputConnection(outAttrs),
                false);
    }
}

class feedInputConnecttion extends InputConnectionWrapper implements
        InputConnection {

    public feedInputConnecttion(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    /**
     * 对输入的内容进行拦截
     *
     * @param text
     * @param newCursorPosition
     * @return
     */
    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        // 只能输入汉字或者英文
        if (!text.toString().matches("[\u4e00-\u9fa5]+") && !text.toString().matches("[a-zA-Z /]+") && !text.toString().matches("[123456789]+")
                && !text.toString().matches("[~!@#$%^&*()_+|/-=<>?:',.。，？“”；！…]+")) {
            return false;
        }
        return super.commitText(text, newCursorPosition);
    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        return super.sendKeyEvent(event);
    }

    @Override
    public boolean setSelection(int start, int end) {
        return super.setSelection(start, end);
    }
}


