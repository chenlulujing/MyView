package com.san.os.myview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.san.os.myview.R;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-17 14:08
 */

public class DayNightSwitchView extends RelativeLayout {


    public static final int MODE_DAY = 1;
    public static final int MODE_NIGHT = 2;
    private static final int SCREEN_WIDTH = 750;
    private static final int DURATION_MOVE = 400;
    private static final int DURATION_DISPEAR = 200;

    private static final int DEGRESS = 20;

    private int mCurrentMode;

    private ImageView mDayView;
    private ImageView mNightView;
    private TextView mDesc;
    private boolean mDone = true;

    public DayNightSwitchView(Context context) {
        super(context);
        init(context);
    }

    public DayNightSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DayNightSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(translate(60), translate(60));
    }

    private void init(Context context) {

        ONE = (getDisplayWidth((Activity) context) / ((float) SCREEN_WIDTH));

        mDayView = new ImageView(getContext());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.reader_daymode_icon);
        mDayView.setImageBitmap(bitmap);
        RelativeLayout.LayoutParams rlDay = new RelativeLayout.LayoutParams(translate(40), translate(40));
        addView(mDayView, rlDay);


        mNightView = new ImageView(getContext());
        Bitmap bitmapNight = BitmapFactory.decodeResource(getResources(), R.drawable.reader_nightmode_icon);
        mNightView.setImageBitmap(bitmapNight);
        RelativeLayout.LayoutParams rlNight = new RelativeLayout.LayoutParams(translate(40), translate(40));
        addView(mNightView, rlNight);


        mDesc = new TextView(context);
        RelativeLayout.LayoutParams rlDesc = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mDesc, rlDesc);

        setMode(MODE_DAY);

    }


    public void setMode(int mode) {
        mCurrentMode = mode;
        if (mCurrentMode == MODE_DAY) {
            //白天模式
            mDayView.setAlpha(1.0f);
            mNightView.setAlpha(0.0f);
            mDesc.setText("夜间");
        } else {
            //夜间模式
            mDayView.setAlpha(0.0f);
            mNightView.setAlpha(1.0f);
            mDesc.setText("日间");
        }
    }


    public void switchModel(Runnable runnable) {
        if (mDone) {
            mDone = false;
            if (mCurrentMode == MODE_DAY) {
                //白天切夜间
                moveRight(runnable, mDayView);
            } else {
                //夜间切白天
                moveRight(runnable, mNightView);
            }

        }
    }


    private void moveRight(final Runnable runnable, final View view) {
        AnimationSet set = new AnimationSet(false);
        RotateAnimation rotateAnimator = new RotateAnimation(0, DEGRESS, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        set.addAnimation(rotateAnimator);
        rotateAnimator.setDuration(DURATION_MOVE);
        rotateAnimator.setFillAfter(true);
        rotateAnimator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveLeft(runnable, view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(set);
    }

    private void disprear(final Runnable runnable, final View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
        alphaAnimation.setDuration(DURATION_DISPEAR);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setAlpha(0);
                appear(runnable, view == mDayView ? mNightView : mDayView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    private void appear(final Runnable runnable, final View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(DURATION_DISPEAR);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setAlpha(1.0f);
                mDone = true;
                mCurrentMode = view == mNightView ? MODE_NIGHT : MODE_DAY;
                runnable.run();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    private void moveLeft(final Runnable runnable, final View view) {
        AnimationSet set = new AnimationSet(false);
        RotateAnimation rotateAnimator = new RotateAnimation(DEGRESS, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        set.addAnimation(rotateAnimator);
        rotateAnimator.setDuration(DURATION_MOVE);
        rotateAnimator.setFillAfter(true);
        rotateAnimator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                disprear(runnable, view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(set);
    }

    private float ONE;

    private int translate(float ori) {
        return (int) (ONE * ori);
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
                (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
