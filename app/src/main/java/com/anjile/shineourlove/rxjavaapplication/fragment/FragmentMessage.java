package com.anjile.shineourlove.rxjavaapplication.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.adapter.MessageListAdapter;
import com.anjile.shineourlove.rxjavaapplication.entity.MessageMainEntity;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/22.
 */

public class FragmentMessage extends Fragment implements View.OnClickListener {

    @BindView(R.id.txt_fragment_message_login)
    TextView txtFragmentMessageLogin;
    @BindView(R.id.rl_fragment_message_login)
    RelativeLayout rlFragmentMessageLogin;
    @BindView(R.id.ll_fragment_message_notification_bar)
    RelativeLayout llFragmentMessageNotificationBar;
    @BindView(R.id.rcv_fragment_message_list)
    SwipeMenuRecyclerView rcvFragmentMessageList;
    @BindView(R.id.sv_fragment_message_notification)
    ScrollView svFragmentMessageNotification;
    Unbinder unbinder;
    @BindView(R.id.img_fragment_message_more)
    ImageView imgFragmentMessageMore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBasic();
        initNotification();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initBasic() {
        imgFragmentMessageMore.setOnClickListener(this);
    }

    public void showSimpleHintDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.dialog);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_choose_dialog_layout, null);
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
        txtUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "上方点击", Toast.LENGTH_SHORT).show();
            }
        });
        txtMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "中间点击", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.getDecorView().setPadding(36, 0, 36, 18); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.ItemDecoration mItemDecoration;

    public void initNotification() {
        ArrayList<MessageMainEntity> list = new ArrayList<>();
        MessageListAdapter selectAdapter = new MessageListAdapter(list, getContext());
        for (int i = 0; i < 6; i++) {
            list.add(new MessageMainEntity(true
                    , "http://fb.topitme.com/b/3b/24/1132872205c05243bbl.jpg"
                    , "索尼大法好"
                    , "守护姨夫的微笑"
                    , 1501100000000l));
        }

        mLayoutManager = createLayoutManager();
        mItemDecoration = createItemDecoration();

        rcvFragmentMessageList.setSwipeMenuItemClickListener(mMenuItemClickListener); // Item的Menu点击。
        rcvFragmentMessageList.setSwipeMenuCreator(mSwipeMenuCreator); // 菜单创建器。
        rcvFragmentMessageList.setLayoutManager(mLayoutManager);
        rcvFragmentMessageList.addItemDecoration(mItemDecoration);
        rcvFragmentMessageList.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

            }
        });
        rcvFragmentMessageList.setAdapter(selectAdapter);
        selectAdapter.notifyDataSetChanged();
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(getContext(), R.color.gray_line));
    }

    /**
     * 菜单创建器
     */
    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                    .setBackground(R.color.blue)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_fragment_message_more:
                showSimpleHintDialog();
                break;
        }
    }
}
