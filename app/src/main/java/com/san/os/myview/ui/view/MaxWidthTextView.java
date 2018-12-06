package com.san.os.myview.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2018-10-10 12:02
 */

public class MaxWidthTextView extends TextView {


    public int mWidth;

    public MaxWidthTextView(Context context, int width) {
        super(context);
        mWidth = width;
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public MaxWidthTextView(Context context) {
        super(context);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public MaxWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public MaxWidthTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mywidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.AT_MOST);
        super.onMeasure(mywidthMeasureSpec, heightMeasureSpec);
    }
}
