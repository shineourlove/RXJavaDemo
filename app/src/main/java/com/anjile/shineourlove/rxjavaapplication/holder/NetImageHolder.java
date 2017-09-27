package com.anjile.shineourlove.rxjavaapplication.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/9/26.
 */

public class NetImageHolder implements Holder {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Object data) {
        Glide.with(context).load(data).into(imageView);
    }
}
