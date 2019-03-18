package com.san.os.myview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.san.os.myview.R;
import com.san.os.myview.utils.SizeUtils;

import static com.san.os.myview.view.ScrollGuideView.TOP_MARGIN;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-18 14:57
 */

public class ScrollShadowView extends View {

    private static final int radius = SizeUtils.translate(64);

    public static int LEFT;
    public static int RIGHT;

    public static final int WIDTH = SizeUtils.translate(240);
    public static final int HEIGHT = SizeUtils.translate(120);

    private int edgeLeft = LEFT;
    private int edgeRight = RIGHT;
    private int mBottom = TOP_MARGIN + HEIGHT;


    public ScrollShadowView(Context context) {
        super(context);
        init(context);
    }

    public ScrollShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mPaint = new Paint();
        LEFT = getOriLeft();
        RIGHT = getOriRight();
    }

    private int getOriLeft() {
        return SizeUtils.getDisplayWidth() - WIDTH - radius;
    }

    private int getOriRight() {
        return SizeUtils.getDisplayWidth() + radius;
    }

    public int getEdgeLeft() {
        return edgeLeft;
    }

    public int getEdgeRight() {
        return edgeRight;
    }

    public void setEdgeLeft(int edgeLeft) {
        this.edgeLeft = edgeLeft;
        invalidate();
    }

    public void setEdgeRight(int edgeRight) {
        this.edgeRight = edgeRight;
        invalidate();
    }

    class MyHandler extends android.os.Handler {
        public MyHandler() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int angle = (Integer) msg.obj;
            invalidate();
        }
    }

    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);                       //设置画笔为无锯齿
        mPaint.setColor(getResources().getColor(R.color.gray1));                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        mPaint.setStrokeWidth((float) 3.0);              //线宽
        mPaint.setStyle(Paint.Style.FILL);                   //空心效果
        RectF r2 = new RectF();                           //RectF对象
        r2.left = edgeLeft;                                 //左边
        r2.top = TOP_MARGIN;                                 //上边
        r2.right = edgeRight;                                   //右边
        r2.bottom = mBottom;                              //下边
        canvas.drawRoundRect(r2, radius, radius, mPaint);        //绘制圆角矩形
    }

    public void headerMove() {
        setVisibility(VISIBLE);
        edgeLeft = getOriLeft();
        edgeRight = getOriRight();
        invalidate();


        PropertyValuesHolder p1, p2;
        p1 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f);
        p2 = PropertyValuesHolder.ofInt("edgeLeft", SizeUtils.getDisplayWidth() - radius, getOriLeft());
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(this, p2);
        animator2.setDuration(1000);
        animator2.setStartDelay(0);
        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                footerMove();

            }

        });
        animator2.start();

    }

    public void footerMove() {


        PropertyValuesHolder p1;
        p1 = PropertyValuesHolder.ofInt("edgeRight", getOriRight(), getOriLeft() + radius);
        final ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(this, p1);
        animator1.setDuration(1000);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

        });
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue("edgeRight");
                Log.i("lulu_", "value=" + value);
                if (value <= SizeUtils.getDisplayWidth() - (WIDTH - ScrollGuideView.RADUIS)) {
                    animator1.cancel();
                    setVisibility(INVISIBLE);
                }

            }
        });
        animator1.start();
    }
}
