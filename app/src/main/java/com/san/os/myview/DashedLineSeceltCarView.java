package com.san.os.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 虚线
 */
public class DashedLineSeceltCarView extends View {
    private static final int DEFAULT_BORDER_COLOR = R.color.blue_2467D5;
    private int mBackgroundColor = DEFAULT_BORDER_COLOR;
    private int mDefaultWidth = 800;
    private int mDefaultHeight = 2;
    private Context mContext;

    public DashedLineSeceltCarView(Context context) {
        super(context);
        mContext = context;
    }

    public DashedLineSeceltCarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }


    public DashedLineSeceltCarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DashedLineView, defStyle, 0);
        mBackgroundColor = a.getColor(R.styleable.DashedLineView_background_color,
                mContext.getResources().getColor(R.color.grey_b7b7b7));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width;
        int height;

        // Get measureSpec mode and size values.
        final int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (measureWidthMode == MeasureSpec.AT_MOST) {
            width = measureWidth;
        } else if (measureWidthMode == MeasureSpec.EXACTLY) {
            width = measureWidth;
        } else {
            width = mDefaultWidth;
        }

        if (measureHeightMode == MeasureSpec.AT_MOST) {
            height = Math.min(mDefaultHeight, measureHeight);
        } else if (measureHeightMode == MeasureSpec.EXACTLY) {
            height = measureHeight;
        } else {
            height = mDefaultHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mBackgroundColor);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, getHeight());
        PathEffect effects = new DashPathEffect(new float[]{4, 4, 4, 4}, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}

