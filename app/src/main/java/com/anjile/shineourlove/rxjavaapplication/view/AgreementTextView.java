package com.anjile.shineourlove.rxjavaapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/23.
 */

public class AgreementTextView extends TextView {
    private int height;
    private int width;

    public AgreementTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        Log.i("agreement_text_view", "onSizeChanged;  width= " + w + ";  height= " + height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("agreement_text_view", "onTouchEvent;  x:" + event.getX() + ";   y:" + event.getY());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
