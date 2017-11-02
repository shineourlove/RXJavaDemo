package com.anjile.shineourlove.rxjavaapplication.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by shineourlove on 2017/3/9.
 * use to alter name;
 */
public class NameEditText extends EditText {
    int maxLen = 8;

    public NameEditText(Context context) {
        super(context);
    }

    public NameEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NameEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Log.i("new_commit_text", "++++++++    CharSequence: " + text);
        super.setText(text, type);
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                int dindex = 0;
                int count = 0;

                while (count <= maxLen && dindex < dest.length()) {
                    char c = dest.charAt(dindex++);
                    if (c < 128) {
                        count = count + 1;
                    } else {
                        count = count + 2;
                    }
                }

                if (count > maxLen) {
                    return dest.subSequence(0, dindex - 1);
                }

                int sindex = 0;
                while (count <= maxLen && sindex < source.length()) {
                    char c = source.charAt(sindex++);
                    if (c < 128) {
                        count = count + 1;
                    } else {
                        count = count + 2;
                    }
                }

                if (count > maxLen) {
                    sindex--;
                }

                return source.subSequence(0, sindex);
            }
        };
        InputFilter[] filters1 = new InputFilter[]{filter};
        super.setFilters(filters1);
    }

    /**
     * 输入法
     *
     * @param outAttrs
     * @return
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new mInputConnecttion(super.onCreateInputConnection(outAttrs),
                false);
    }
}

class mInputConnecttion extends InputConnectionWrapper implements InputConnection {

    public mInputConnecttion(InputConnection target, boolean mutable) {
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
        if (!text.toString().matches("[\u4e00-\u9fa5]+") && !text.toString().matches("[a-zA-Z /]+")) {
            return false;
        }
        Log.i("new_commit_text", "---------    CharSequence: " + text + "    newCursorPosition: " + newCursorPosition);
        /*if (!text.toString().matches("[\u4e00-\u9fa5]+")) {
            return false;
        }*/
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