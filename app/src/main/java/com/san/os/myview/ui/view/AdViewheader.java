package com.san.os.myview.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2016-08-10 14:34
 */
public class AdViewheader extends View {

    private final static String TAG = AdViewheader.class.getSimpleName();
    private static final int DEFAULT_BORDER_COLOR = R.color.grey_b7b7b7;
    private static final int DEFAULT_TEXT_SIZE = 40;


    private String mTitle;
    private int mHeight;
    private int mWidth;
    private int mTextSize = 40;
    private int mPadding = 20;
    private int mColor = DEFAULT_BORDER_COLOR;


    private Paint mPaint;

    public AdViewheader(Context context) {
        super(context);
        init(context);
    }

    public AdViewheader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context) {
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getContext().getResources().getColor(mColor));
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Adheaderview);
        mTitle = a.getString(R.styleable.Adheaderview_remind);
        mTextSize = a.getInt(R.styleable.Adheaderview_titleSize,DEFAULT_TEXT_SIZE);
        mColor = a.getInt(R.styleable.Adheaderview_adColor,DEFAULT_BORDER_COLOR);
        a.recycle();
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        Log.i(TAG, "mWidth == " + mWidth);
        Log.i(TAG, "mHeight == " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, mHeight / 2, mWidth / 2 - (mTextSize+mPadding), mHeight / 2, mPaint);
        canvas.drawLine(mWidth / 2 +( mTextSize+mPadding), mHeight / 2, mWidth, mHeight / 2, mPaint);
        mPaint.setTextSize(mTextSize);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        Rect targetRect = new Rect(0, 0, mWidth, mHeight);
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mTitle, targetRect.centerX(), baseline, mPaint);
//        canvas.drawText(mTitle,mWidth/2,mHeight/2,mPaint);
    }
}
