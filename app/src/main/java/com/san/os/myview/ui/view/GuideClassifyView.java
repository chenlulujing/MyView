package com.san.os.myview.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-23 10:28
 */

public class GuideClassifyView extends View {

    private Bitmap mPersonBitmap, mBitmap1;
    private Rect mPersonBitmapRect, mWholeRect, mBitmapRect1;
    private int mWholeWidth, mWholeHeight;
    private Paint mBitPaint;
    private int addWidth = 0;
    private int addHeight = 0;

    public GuideClassifyView(Context context) {
        super(context);
        init();
    }

    public GuideClassifyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuideClassifyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);

        mPersonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_person);
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_1);


        mWholeWidth = mPersonBitmap.getWidth() + addWidth;
        mWholeHeight = mPersonBitmap.getHeight() + addHeight;
        mWholeRect = new Rect(0, 0, mWholeWidth, mWholeHeight);

        mPersonBitmapRect = new Rect(addWidth / 2, addHeight / 2, mPersonBitmap.getWidth() + addWidth / 2, mPersonBitmap.getHeight() + addHeight / 2);
        mBitmapRect1 = new Rect((mWholeWidth - mBitmap1.getWidth()) / 2, mWholeHeight - mBitmap1.getHeight(), mBitmap1.getWidth() + (mWholeWidth - mBitmap1.getWidth()) / 2, mWholeHeight);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWholeWidth, mWholeHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mPersonBitmap, null, mPersonBitmapRect, mBitPaint);
        canvas.drawBitmap(mBitmap1, null, mBitmapRect1, mBitPaint);
    }
}
