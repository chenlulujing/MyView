package com.san.os.myview;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.san.os.myview.tool.ToolBox;
import com.san.os.myview.view.BannerAdapter;
import com.san.os.myview.view.SanPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-10 10:20
 */

public class BottomPopWActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottompop);
        initPop();
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1();
            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
            }
        });
    }

    PopupWindow pop1;
    PopupWindow pop2;

    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_layout, null);
        initPopView(view);
        //设置屏幕的高度和宽度
        pop1 = new PopupWindow(view, ToolBox.getDisplayWith(this), ToolBox.getDisplayHeight(this) * 3 / 10);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop1.setBackgroundDrawable(new ColorDrawable());
        pop1.setOutsideTouchable(true);
        pop1.setAnimationStyle(R.style.MyPopupWindow_anim_style);

        View view2 = LayoutInflater.from(this).inflate(R.layout.popwindow_layout, null);
        initPopView(view2);
        //设置屏幕的高度和宽度
        pop2 = new PopupWindow(view2, ToolBox.getDisplayWith(this), ToolBox.getDisplayHeight(this) * 3 / 10);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop2.setBackgroundDrawable(new ColorDrawable());
        pop2.setOutsideTouchable(true);

    }

    private void initPopView(View rootView) {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        BannerAdapter bannerAdapter = new BannerAdapter();
        viewPager.setAdapter(bannerAdapter);
    }

    /**
     * 设置popwindow的弹出的位置. *
     * 1：首先要判断是否有navigation bar。如果有的的话，要把他们的高度给加起来。 * *
     * 2：showAtLocation（）；是pop相对于屏幕而言的。 * *
     * 3：如果是 pop.showAsDropDown();则是相对于你要点击的view的位置。设置的坐标。
     */


    private void show1() {
        pop1.showAtLocation(findViewById(R.id.bt1), Gravity.BOTTOM, 0, 0);
    }

    private void show2() {
        pop2.showAtLocation(findViewById(R.id.bt2), Gravity.TOP, 0, 0);
    }
}
