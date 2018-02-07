package com.san.os.myview.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-07 15:00
 */

public class TabVideoButtonPathView extends View {


    private Paint mPaint;
    private Context mContext;

    private int Y = 0;
    private int height;
    private int one;
    private int r_big;
    private int r;
    private int dis;
    private int half;
    private int paddingBottom;

    public TabVideoButtonPathView(Context context) {
        super(context);
        init(context);
    }

    public TabVideoButtonPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabVideoButtonPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(R.color.skin_color_tx_9));
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(sp2px(context, 25));


        height = dip2px(50);
        one = dip2px(1);
        dis = one * 6;
        half = one * 23;
        Y = (int) (half * 0.5);
        paddingBottom = one * 4;

        r_big = (int) (1.41 * 23 * one);
        r = r_big - dis;


        width = getDisplayWidth((Activity) mContext) / 5;
        locationX = getDisplayWidth((Activity) mContext) / 2;
        locationY = Y + half;

    }

    private int width;

    private int locationX;
    private int locationY;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDisplayWidth((Activity) mContext), height + Y + paddingBottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawLine(0, Y, getDisplayWidth((Activity) mContext) / 2 - half, Y, mPaint);

        canvas.drawLine(getDisplayWidth((Activity) mContext) / 2 + half, Y, getDisplayWidth((Activity) mContext), Y, mPaint);


        mPaint.setColor(mContext.getResources().getColor(R.color.skin_color_tx_8));


        //小视频图标 真正的圆
        RectF rectf_head = new RectF(locationX - r, locationY - r, locationX + r, locationY + r);//确定外切矩形范围
        canvas.drawArc(rectf_head, 0, 360, false, mPaint);//绘制圆弧，不含圆心

        //大圆
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        RectF rectf_head_big = new RectF((locationX - r_big), locationY - r_big, locationX + r_big, locationY + r_big);//确定外切矩形范围
        canvas.drawArc(rectf_head_big, -45, -90, false, mPaint);//绘制圆弧，不含圆心


    }


    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }


    public static int getDisplayHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getDisplayWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

}
