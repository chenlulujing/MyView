package com.san.os.myview.ui.view.decorator;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.san.os.myview.tool.ToolBox;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-06-07 11:23
 */

public class SanLinearLayout extends LinearLayout implements ISanView {

    private float ONE;

    public SanLinearLayout(Context context, float width) {
        super(context);
        init(width);
    }

    public SanLinearLayout(Context context, @Nullable AttributeSet attrs, float width) {
        super(context, attrs);
        init(width);
    }

    public SanLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, float width) {
        super(context, attrs, defStyleAttr);
        init(width);
    }

    private void init(float width) {
        ONE = ToolBox.getDisplayWith((Activity) getContext()) / width;
    }

    @Override
    public int getRealSize(float i) {
        return (int) (ONE * i);
    }

    @Override
    public ViewGroup.LayoutParams getLayouParams(float width, float height) {
        return new LinearLayout.LayoutParams(getRealSize(width), getRealSize(height));
    }
}
