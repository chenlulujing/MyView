package com.san.os.myview.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class JumpLoadingView extends LinearLayout {


    public JumpLoadingView(Context context) {
        super(context);
        initView(context);
    }

    public JumpLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public JumpLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        addView(new CIrcleImageView(context));
        addView(new CIrcleImageView(context));
        addView(new CIrcleImageView(context));


    }

    private void up(final Runnable runnable) {
//        TranslateAnimation translateAnimation = new TranslateAnimation();
        getChildAt(0).animate().translationY(-50).setDuration(500).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (runnable != null) {
                    runnable.run();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private void down(final Runnable runnable) {
        getChildAt(0).animate().translationY(50).setDuration(500).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (runnable != null) {
                    runnable.run();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }


    private Path mPath;//声明动画集合
    private PathMeasure mPathMeasure;
    private void upByPath(){


        //头花路径
        mPath = new Path();
        mPath.moveTo(100, 200);
        mPath.lineTo(100,0);
        mPath.lineTo(100,100);




        //头花、头发
        mPathMeasure = new PathMeasure(mPath, false);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


}
