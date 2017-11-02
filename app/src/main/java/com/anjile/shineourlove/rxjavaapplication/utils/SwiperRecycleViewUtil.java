package com.anjile.shineourlove.rxjavaapplication.utils;

import android.content.Context;
import android.view.ViewGroup;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;

/**
 * Created by Administrator on 2017/11/1.
 */

public class SwiperRecycleViewUtil {
    /**
     * 菜单创建器
     */
    public static SwipeMenuCreator getSwipeMenuCreator(final Context context, final int BackColor, final int textColor) {
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                int width = context.getResources().getDimensionPixelSize(R.dimen.dp_70);
                // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
                // 2. 指定具体的高，比如80;
                // 3. WRAP_CONTENT，自身高度，不推荐;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                // 添加右侧的，如果不添加，则右侧不会出现菜单。
                SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                        .setBackground(BackColor)
                        .setText("删除")
                        .setTextColor(context.getResources().getColor(textColor))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            }
        };
        return mSwipeMenuCreator;
    }
}
