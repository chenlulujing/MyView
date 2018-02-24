package com.san.os.myview.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-07 15:00
 */

public class TabVideoButtonPathView extends View implements View.OnClickListener {


    private Paint mPaint;
    private Context mContext;


    //bitmap
    private Rect mSrcRect;  //bitmap源矩形
    private Rect mDestRect; //bitmap位置矩形
    private Bitmap mBitmap;


    private int Y = 0;
    private int height;
    private int one;
    private int r_big;
    private int r;
    private int dis;
    private int half;
    private int paddingBottom;


    /**
     * View的显示区域。
     */
    final Rect bounds = new Rect();

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
        mPaint.setStrokeWidth(5);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(sp2px(context, 25));


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_wodedingyue);
        mSrcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());


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

//        setOnClickListener(this);

    }

    private Paint mBitPaint;

    private void initPaint() {
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
    }


    private int width;

    private int locationX;
    private int locationY;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDisplayWidth((Activity) mContext), Y + height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.save();
        canvas.drawColor(Color.RED);
//        Paint paint = new Paint();
//
//        /**
//         * 设置画笔的锯齿效果，去锯齿
//         * 设置画笔颜色，蓝色
//         * 设置画笔风格，空心
//         * 设置空心画笔的宽度，3
//         */
//        paint.setAntiAlias(true);
//        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(3);
//
//

        canvas.save();
        Path path = new Path();
        path.moveTo(0, Y);
        path.lineTo(getDisplayWidth((Activity) mContext) / 2 - half, Y);
        RectF rectf_head_big = new RectF((locationX - r_big), locationY - r_big, locationX + r_big, locationY + r_big);//确定外切矩形范围
        path.addArc(rectf_head_big, -45, -90);
        path.lineTo(getDisplayWidth((Activity) mContext), Y);
        path.rLineTo(0, height);
        path.lineTo(0, height + Y);
        path.lineTo(0, Y);
        canvas.clipPath(path);
        drawScene(canvas);
        canvas.restore();


//        canvas.save();
//        canvas.translate(160, 160);
//        canvas.clipRect(0, 0, 60, 60);
//        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
//        drawScene(canvas);
//        canvas.restore();


//        getDrawingRect(bounds);
//
//        getDrawingRect(bounds);
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(bounds.centerX(), bounds.centerY(), (bounds.right - bounds.left) / 2, mPaint);
//            //绘制出局
//            drawOutState(canvas);


        canvas.drawLine(0, Y, getDisplayWidth((Activity) mContext) / 2 - half, Y, mPaint);

        canvas.drawLine(getDisplayWidth((Activity) mContext) / 2 + half, Y, getDisplayWidth((Activity) mContext), Y, mPaint);
//
//
//
//
        //小视频图标
        mDestRect = new Rect(locationX - r, locationY - r, locationX + r, locationY + r);
        canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mBitPaint);
//
//        //大圆
        mPaint.setStyle(Paint.Style.STROKE);//设置空心


//        RectF rectf_head_big = new RectF((locationX - r_big), locationY - r_big, locationX + r_big, locationY + r_big);//确定外切矩形范围
        canvas.drawArc(rectf_head_big, -45, -90, false, mPaint);//绘制圆弧，不含圆心


    }


    private void drawOutState(Canvas canvas) {
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), (bounds.right - bounds.left) / 2 - one * 5, mPaint);
    }


    private void drawScene(Canvas canvas) {

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getDisplayWidth((Activity) mContext), Y + height, mPaint);

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


    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "TabVideoButtonPathView", Toast.LENGTH_SHORT).show();
    }
}
