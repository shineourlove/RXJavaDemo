package com.anjile.shineourlove.rxjavaapplication.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

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
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.test1)
                .error(R.drawable.test1)
                .priority(Priority.HIGH);
        Glide.with(context).load(data).apply(options).into(imageView);
    }
}
