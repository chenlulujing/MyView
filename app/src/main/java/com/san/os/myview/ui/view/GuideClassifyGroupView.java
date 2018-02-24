package com.san.os.myview.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-23 10:28
 */

public class GuideClassifyGroupView extends RelativeLayout {


    private ImageView mPersonView, mView1, mView2, mView4, mView7_1, mView7_2, mView7_3, mView8_1;

    private int mWholeWidth, mWholeHeight;

    public GuideClassifyGroupView(Context context) {
        super(context);
        init();
    }

    public GuideClassifyGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuideClassifyGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {


        mPersonView = new ImageView(getContext());
        Bitmap Personbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_person);
        mPersonView.setImageBitmap(Personbitmap);
        mWholeWidth = Personbitmap.getWidth();
        mWholeHeight = Personbitmap.getHeight();
//        RelativeLayout.LayoutParams rlPerson = new RelativeLayout.LayoutParams(Personbitmap.getWidth(),Personbitmap.getHeight());
//        rlPerson.addRule(RelativeLayout.CENTER_IN_PARENT);
//        addView(mPersonView, rlPerson);
        addView(mPersonView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWholeWidth, mWholeHeight);
    }


    private boolean mDone1 = true;

    public void addView1() {
        if (mDone1) {
            mDone1 = false;
            if (mView1 == null) {
                mView1 = new ImageView(getContext());
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_1);
                mView1.setImageBitmap(bitmap1);
                mView1.setAlpha(0.0f);

                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl1.topMargin = mWholeHeight - bitmap1.getHeight();
                rl1.leftMargin = (mWholeWidth - bitmap1.getWidth()) / 2;
                addView(mView1, rl1);

                mView1.animate().alpha(1.0f)
                        .rotation(360)
                        .setDuration(1000)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mDone1 = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            } else {
                ViewPropertyAnimator animator = mView1.animate();
                animator.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (getContext() instanceof Activity) {
                            if (!((Activity) getContext()).isFinishing()) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView1);
                                        mView1 = null;
                                        mDone1 = true;
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.alpha(0.0f)
                        .rotation(0)
                        .setDuration(500)
                        .start();
            }
        }
    }

    private boolean mDone2 = true;

    public void addView2() {
        if (mDone2) {
            mDone2 = false;
            if (mView2 == null) {
                mView2 = new ImageView(getContext());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img2);
                mView2.setImageBitmap(bitmap);
                mView2.setAlpha(0.0f);

                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl1.topMargin = mWholeHeight - bitmap.getHeight();
                rl1.leftMargin = (mWholeWidth) / 2 + 90;
                addView(mView2, rl1);

                mView2.animate().alpha(1.0f)
                        .setDuration(1000)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mDone2 = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            } else {
                ViewPropertyAnimator animator = mView2.animate();
                animator.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (getContext() instanceof Activity) {
                            if (!((Activity) getContext()).isFinishing()) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView2);
                                        mView2 = null;
                                        mDone2 = true;
                                    }
                                });

                            }
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.alpha(0.0f)
                        .setDuration(500)
                        .start();
            }
        }

    }


    private boolean mDone4 = true;

    public void addView4() {
        if (mDone4) {
            mDone4 = false;
            if (mView4 == null) {
                mView4 = new ImageView(getContext());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img4);
                mView4.setImageBitmap(bitmap);

                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl1.topMargin = mWholeHeight - bitmap.getHeight();
                rl1.leftMargin = (mWholeWidth) / 2 - 140;
                addView(mView4, rl1);

                AnimationSet set = new AnimationSet(false);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                RotateAnimation rotateAnimator = new RotateAnimation(-90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
                set.addAnimation(alphaAnimation);
                set.addAnimation(rotateAnimator);
                rotateAnimator.setDuration(500);
                rotateAnimator.setFillAfter(true);
                rotateAnimator.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mDone4 = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mView4.startAnimation(set);
            } else {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500);
                RotateAnimation rotateAnimator = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
                rotateAnimator.setDuration(500);
                rotateAnimator.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (getContext() instanceof Activity) {
                            if (!((Activity) getContext()).isFinishing()) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView4);
                                        mView4 = null;
                                        mDone4 = true;
                                    }
                                });

                            }
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                AnimationSet set = new AnimationSet(false);
                set.addAnimation(rotateAnimator);
                set.addAnimation(alphaAnimation);
                mView4.startAnimation(set);
            }
        }

    }

    private boolean mDone7 = true;

    public void addView7() {
        if (mDone7) {
            mDone7 = false;
            if (mView7_1 == null) {
                mView7_1 = new ImageView(getContext());
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img7);
                final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img7_3);

                mView7_1.setImageBitmap(bitmap);


                mView7_2 = new ImageView(getContext());
                mView7_2.setImageBitmap(bitmap);

                mView7_3 = new ImageView(getContext());
                mView7_3.setImageBitmap(bitmap1);


                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams((int) (bitmap.getWidth() * 0.5), (int) (bitmap.getHeight() * 0.5));
                rl1.topMargin = 90;
                rl1.leftMargin = (mWholeWidth) / 2 + 70;
                addView(mView7_1, rl1);


                ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 0.8f, 0.5f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(300);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams((int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8));
                        rl.topMargin = 70;
                        rl.leftMargin = (mWholeWidth) / 2 + 90;
                        addView(mView7_2, rl);
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scaleAnimation.setDuration(300);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img7_3);
                                RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams((int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8));
                                rl.topMargin = 10;
                                rl.leftMargin = (mWholeWidth) / 2 + 120;
                                addView(mView7_3, rl);
                                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1, 0.8f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                                scaleAnimation.setDuration(300);
                                scaleAnimation.setFillAfter(true);
                                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        mDone7 = true;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                mView7_3.startAnimation(scaleAnimation);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mView7_2.startAnimation(scaleAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mView7_1.startAnimation(scaleAnimation);
            } else {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(300);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                removeView(mView7_3);
                                mView7_3 = null;
                            }
                        });
                        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scaleAnimation.setDuration(300);
                        scaleAnimation.setFillAfter(true);
                        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView7_2);
                                        mView7_2 = null;
                                    }
                                });

                                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 0.5f, 0.8f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                                scaleAnimation.setDuration(300);
                                scaleAnimation.setFillAfter(true);
                                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        post(new Runnable() {
                                            @Override
                                            public void run() {
                                                removeView(mView7_1);
                                                mView7_1 = null;
                                                mDone7 = true;
                                            }
                                        });


                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                mView7_1.startAnimation(scaleAnimation);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mView7_2.startAnimation(scaleAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mView7_3.startAnimation(scaleAnimation);
            }
        }

    }


    private boolean mDone8 = true;

    public void addView8() {
        if (mDone8) {
            mDone8 = false;
            if (mView8_1 == null) {
                mView8_1 = new ImageView(getContext());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img_8_1);
                mView8_1.setImageBitmap(bitmap);

                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl1.topMargin = mWholeHeight - bitmap.getHeight();
                rl1.leftMargin = mWholeWidth;
                addView(mView8_1, rl1);

                mView8_1.animate()
                        .translationX(-150)
                        .setDuration(300)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mDone8 = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            } else {
                ViewPropertyAnimator animator = mView8_1.animate();
                animator.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (getContext() instanceof Activity) {
                            if (!((Activity) getContext()).isFinishing()) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView8_1);
                                        mView8_1 = null;
                                        mDone8 = true;
                                    }
                                });

                            }
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.translationX(150)
                        .setDuration(300)
                        .start();
            }
        }

    }

    private Path path;//声明动画集合
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];

    public void addView2_1() {
        int distance = 20;
        if (mDone2) {
            mDone2 = false;
            if (mView2 == null) {

                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img2);
                mView2 = new ImageView(getContext());
                mView2.setImageBitmap(bitmap);
                final RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl.topMargin = mWholeHeight;
                rl.leftMargin = (mWholeWidth) / 2 + 90;
                addView(mView2, rl);


                path = new Path();
                path.moveTo((mWholeWidth) / 2 + 90, mWholeHeight);
                path.lineTo((mWholeWidth) / 2 + 90, mWholeHeight - bitmap.getHeight() - distance);
                path.lineTo((mWholeWidth) / 2 + 90, mWholeHeight - bitmap.getHeight());
                mPathMeasure = new PathMeasure(path, false);
                mCurrentPosition = new float[2];


                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                valueAnimator.setDuration(800);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mDone2 = true;
                            }
                        });
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        // 获取当前点坐标封装到mCurrentPosition
                        mPathMeasure.getPosTan(value, mCurrentPosition, null);
                        rl.topMargin = (int) mCurrentPosition[1];
                        Log.i("lulu_animation", "rl1.topMargin=" + ((int) mCurrentPosition[1] - bitmap.getHeight()));
                        mView2.setLayoutParams(rl);
                    }
                });
                valueAnimator.start();
            } else {
                path = new Path();
                path.moveTo((mWholeWidth) / 2 + 90, mWholeHeight - mView2.getHeight());
                path.lineTo((mWholeWidth) / 2 + 90, mWholeHeight - mView2.getHeight() - distance);
                path.lineTo((mWholeWidth) / 2 + 90, mWholeHeight);
                mPathMeasure.setPath(path, false);

                final RelativeLayout.LayoutParams rl = (LayoutParams) mView2.getLayoutParams();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                valueAnimator.setDuration(800);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                removeView(mView2);
                                mView2 = null;
                                mDone2 = true;
                            }
                        });
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        mPathMeasure.getPosTan(value, mCurrentPosition, null);
                        rl.topMargin = (int) mCurrentPosition[1];
                        mView2.setLayoutParams(rl);
                    }
                });
                valueAnimator.start();
            }
        }


    }


}
