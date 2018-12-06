package com.san.os.myview.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author chenlulu@qiyi.com
 * @Description 固定宽度的TextView
 * @date 2018-10-10 12:02
 */

public class ExaclyWidthTextView extends TextView {

    public static final int MARGIN_LEFT = 0;

    public int mWidth;

    public ExaclyWidthTextView(Context context, int width) {
        super(context);
        mWidth = width;
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public ExaclyWidthTextView(Context context) {
        super(context);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public ExaclyWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    public ExaclyWidthTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine();
        setMaxLines(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mywidthMeasureSpec = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY);
        super.onMeasure(mywidthMeasureSpec, heightMeasureSpec);
    }
}
