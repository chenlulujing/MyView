package com.san.os.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class TextImageView extends ImageView {

    public static final int TOTALDAYS = 100; //总计天数


    private Context mContext;
    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private int mRadius = 150;
    private float mRadiusWidth = 5;

    private RectF rect;
    private float mCurrentAngle = 0;  //当前扫描的角度
    private float Angle;  //
    private int mCurrentDay;  //当前天数
    MyHandler handler;

    public TextImageView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
//        mWidth = getDisplayMetrics().widthPixels;
//        mHeight = getDisplayMetrics().heightPixels;
        Log.i("myview", "getWidth():" + mWidth);
        Log.i("myview", " getHeight():" + mHeight);
        handler = new MyHandler();
        setBackgroundResource(R.color.white);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(sp2px(mContext, 25));
        rect = new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius, mHeight / 2 + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        canvas2(mPaint, canvas);

    }

    //画笑脸
    private void canvas1(Paint p, Canvas canvas) {
        p.setColor(mContext.getResources().getColor(R.color.red));
        p.setStyle(Paint.Style.STROKE);//设置空心
        RectF oval1 = new RectF(150, 20, 180, 40);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(190, 20, 220, 40);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(160, 30, 210, 60);
        canvas.drawArc(oval1, 0, 180, false, p);//小弧形
    }

    //空心圆
    private void canvas2(Paint p, Canvas canvas) {
        Log.i("myview", "canvas2");


        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(sp2px(mContext, 25));
        mPaint.setColor(mContext.getResources().getColor(R.color.black));
        mPaint.setStyle(Paint.Style.FILL);
        Rect targetRect = new Rect(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius, mHeight / 2 + mRadius);
        Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        p.setTextAlign(Paint.Align.CENTER);
        float every = ((float) 360 / TOTALDAYS);
        canvas.drawText((int) (((int) mCurrentAngle) / every) + 1 + "/" + TOTALDAYS, targetRect.centerX(), baseline, p);


        //设置环的背景
        p.setStyle(Paint.Style.STROKE);//设置空心
        p.setStrokeWidth(dip2px(10));
        p.setColor(mContext.getResources().getColor(R.color.gray1));
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaint);


        //设置进度
        Log.i("myview", "canvas2 mCurrentAngle:" + mCurrentAngle);
        p.setStrokeWidth(dip2px(9));
        p.setColor(mContext.getResources().getColor(R.color.red));

        rect = new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius, mHeight / 2 + mRadius);
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                270,  //开始角度
                mCurrentAngle, //扫过的角度
                false, //是否使用中心
                mPaint);
    }


    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }

    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    //更新状态
    public void update(int day) {
        final float angle = 360 * ((float) day / TOTALDAYS);
        if (angle != mCurrentAngle) {
            final float start = mCurrentAngle;
            mCurrentAngle = angle;
            if (day < mCurrentDay) {
                //天数减少
                mCurrentDay = day;
                subDays(angle, (int) start);
            } else {
                //天数增加
                mCurrentDay = day;
                addDays(angle, (int) start);
            }
        }
    }

    //天数增加
    private void addDays(final float end, final int start) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = start; i <= end; i++) {
                    try {
                        Log.i("myview", "addDays sleep: i=" + i);
                        Message message = handler.obtainMessage();
                        message.obj = i;
                        handler.sendMessage(message); //发送消息
                        sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //天数减少
    private void subDays(final float end, final int start) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = start; i >= end; i--) {
                    try {
                        Log.i("myview", "subDays sleep: i=" + i);
                        Message message = handler.obtainMessage();
                        message.obj = i;
                        handler.sendMessage(message); //发送消息
                        sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    class MyHandler extends android.os.Handler {
        public MyHandler() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int angle = (Integer) msg.obj;
            mCurrentAngle = angle;
            invalidate();
        }
    }
}
