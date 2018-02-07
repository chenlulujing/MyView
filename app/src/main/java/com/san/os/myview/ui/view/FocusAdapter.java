package com.san.os.myview.ui.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.san.os.myview.tool.CollectionsWrapper;

import java.util.List;

/**
 * 头条焦点图的Adapter
 *
 * @author chenlulu@yiche.com
 * @ClassName FocusAdapter
 * @Description
 * @date 2014-12-2 上午11:20:08
 */
public class FocusAdapter extends PagerAdapter {


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
