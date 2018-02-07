package com.san.os.myview.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class NavView extends HorizontalScrollView {

    private String[] mTitles;
    private LinearLayout mLinearLayout;

    private int DURATION = 500;
    private int itemWidth;
    private float mTranslationX;
    private int center;

    private Paint mPaint;
    private int mIndicatorColor = COLOR_INDICATOR_COLOR;
    private static final int COLOR_INDICATOR_COLOR = Color.GREEN;

    public NavView(Context context) {
        super(context);
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("lulu_nav", "widthMeasureSpec:" + widthMeasureSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
//        canvas.translate(mTranslationX, getHeight());
//        canvas.drawLine(0, 0, itemWidth, 0, mPaint);

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

//    /**
//     * 初始化三角形指示器
//     */
//    private void initTriangle() {
//        Path mPath = new Path();
//        mTriangleHeight = (int) (mTriangleWidth / 2 / Math.sqrt(2));
//        mPath.moveTo(0, 0);
//        mPath.lineTo(mTriangleWidth, 0);
//        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
//        mPath.close();
//    }

    public void init(String[] titles) {
        mTitles = titles;
        if (mTitles == null) {
            return;
        }
        if (mTitles.length == 0) {
            return;
        }
        initPaint();
        setHorizontalScrollBarEnabled(false);

        itemWidth = dip2px(getContext(), 80);
        center = (1080 - itemWidth) / 2;

        mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int size = mTitles.length, i = 0; i < size; i++) {
            Button button = new Button(getContext());
            button.setText(mTitles[i]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            button.setBackgroundResource(R.drawable.vav_backgroud);
            button.setLayoutParams(lp);
            mLinearLayout.addView(button);
        }
        addView(mLinearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setStrokeWidth(9.0F);
    }

    public void setSelectPosition(final int position, float offset) {
        if (mLinearLayout.getChildCount() == 0) {
            return;
        }
        for (int size = mLinearLayout.getChildCount(), i = 0; i < size; i++) {
            mLinearLayout.getChildAt(i).setSelected(i == position);
        }

        mTranslationX = itemWidth * (position + offset);
        Log.i("lulu_translate", "mTranslationX:" + mTranslationX);
        post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofInt(NavView.this, "scrollX", (itemWidth * position - center));
                animator.setDuration(DURATION);
                animator.start();
            }
        });
        invalidate();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }
}
