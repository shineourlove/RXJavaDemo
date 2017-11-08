package com.anjile.shineourlove.rxjavaapplication.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.anjile.shineourlove.rxjavaapplication.R;


/**
 * Created by shineourlove on 2016/9/26.
 * 自定义带删除按钮的EditText
 */
public class DeleteEditTextView extends EditText implements View.OnFocusChangeListener{
    Drawable mClearDrawable;
    private boolean mHasfocs;
    public DeleteEditTextView(Context context) {
        super(context);
        init();
    }

    public DeleteEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeleteEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        mClearDrawable=getCompoundDrawables()[2];
        if (mClearDrawable==null)
        {
            mClearDrawable=getResources().getDrawable(R.drawable.delete);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight()); //getIntrinsicHeight 返回的宽高为dp
        setClearIconVisible(false); //默认隐藏图标
        setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setClearIconVisible(s.length() > 0);  //文字长度大于0  显示删除图标
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setClearIconVisible(s.length() > 0);  //文字长度大于0  显示删除图标
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height)/2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y <(distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);

    }

    /**
     * 是否显示删除图标
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.mHasfocs=hasFocus;
        if (mHasfocs) {
            setClearIconVisible(getText().length() > 0);
            Log.i("hasfocus",">>>>>>>>>>true");
        } else {
            setClearIconVisible(false);
            Log.i("hasfocus", ">>>>>>>>>>false");
        }
    }
}
