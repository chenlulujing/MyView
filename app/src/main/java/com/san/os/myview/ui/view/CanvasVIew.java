package com.san.os.myview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class CanvasVIew extends View {

    private Paint mPaint;
    private Path mPath;

    public CanvasVIew(Context context) {
        super(context);
        init();
    }

    public CanvasVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getContext().getResources().getColor(R.color.red_dd2727));
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.moveTo(0, 100);
        mPath.lineTo(1000, 100);
        mPath.lineTo(50, 0);
        mPath.close();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heighthMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("ulu_onSizeChanged", "w==" + w + ",h==" + h + ",oldw==" + oldw + ",oldh==" + oldh);
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
