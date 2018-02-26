package com.san.os.myview.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
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


    private ImageView mPersonView, mBackgroudCircleView, mBackgroudStars1View, mBackgroudStars2View, mView1, mView2, mView4, mView6, mView6_2, mView6_3, mView6_4, mView3, mView5, mView9, mView7_1, mView7_2, mView7_3, mView8_1;
    private View mView2_cover;

    private int mWholeWidth, mWholeHeight;
    private float ONE;


    private static final int VIEW_WIDTH = 330;
    private static final int VIEW_HEIGHT = 180;

    private static final int SCREEN_WIDTH = 375;

    private static final int PERSON_HEIGHT = 145;
    private static final int PERSON_WIDTH = 175;

    private static final int BACKGROUD_CIRCLE_WIDTH = 170;

    private static final int STARS1_HEIGHT = 78;
    private static final int STARS1_WIDTH = 162;

    private static final int STARS2_HEIGHT = 58;
    private static final int STARS2_WIDTH = 147;


    public GuideClassifyGroupView(Context context) {
        super(context);
        init(context);
    }

    public GuideClassifyGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GuideClassifyGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Bitmap bitmap6_2;

    private void init(Context context) {
        setBackgroundColor(getResources().getColor(R.color.skin_color_bg_1));
        ONE = (getDisplayWidth((Activity) context) / ((float) SCREEN_WIDTH));


        mWholeWidth = (int) (ONE * VIEW_WIDTH);
        mWholeHeight = (int) (ONE * VIEW_HEIGHT);
        Log.i("lulu_size", "mWholeWidth=" + mWholeWidth);
        Log.i("lulu_size", "mWholeHeight=" + mWholeHeight);


        //添加元素顺序
        //1、女司机头发
        //2、人物基础图
        //3、中国头巾
        //4、头花

        //女司机头发
        mView6_2 = new ImageView(getContext());
        mView6_2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bitmap6_2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img6_2);
        mView6_2.setImageBitmap(bitmap6_2);

        //背景圆圈
        mBackgroudCircleView = new ImageView(getContext());
        Bitmap bitmapCircle = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_circle);
        mBackgroudCircleView.setImageBitmap(bitmapCircle);
        RelativeLayout.LayoutParams rlCircle = new RelativeLayout.LayoutParams(translate(BACKGROUD_CIRCLE_WIDTH), translate(BACKGROUD_CIRCLE_WIDTH));
        rlCircle.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mBackgroudCircleView, rlCircle);

        //背景星星1
        mBackgroudStars1View = new ImageView(getContext());
        Bitmap bitmapStar1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_stars1);
        mBackgroudStars1View.setImageBitmap(bitmapStar1);
        RelativeLayout.LayoutParams rlStars1 = new RelativeLayout.LayoutParams(translate(STARS1_WIDTH), translate(STARS1_HEIGHT));
        rlStars1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlStars1.leftMargin = translate(73);
        rlStars1.topMargin = translate(41);
        addView(mBackgroudStars1View, rlStars1);

        //背景星星2
        mBackgroudStars2View = new ImageView(getContext());
        Bitmap bitmapStar2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_stars2);
        mBackgroudStars2View.setImageBitmap(bitmapStar2);
        RelativeLayout.LayoutParams rlStars2 = new RelativeLayout.LayoutParams(translate(STARS2_WIDTH), translate(STARS2_HEIGHT));
        rlStars2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlStars2.rightMargin = translate(64);
        rlStars2.topMargin = translate(57);
        addView(mBackgroudStars2View, rlStars2);


        //基础人物图
        mPersonView = new ImageView(getContext());
        Bitmap bitmapPerson = BitmapFactory.decodeResource(getResources(), R.drawable.ic_person);
        mPersonView.setScaleType(ImageView.ScaleType.FIT_XY);
        mPersonView.setImageBitmap(bitmapPerson);
        RelativeLayout.LayoutParams rlPerson = new RelativeLayout.LayoutParams(translate(PERSON_WIDTH), translate(PERSON_HEIGHT));
        Log.i("lulu_size", "pserson_width=" + rlPerson.width);
        Log.i("lulu_size", "pserson_height=" + rlPerson.height);
        rlPerson.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rlPerson.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlPerson.bottomMargin = translate(15);
        addView(mPersonView, rlPerson);


        //女司机头发
//        final RelativeLayout.LayoutParams rl6_2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        rl6_2.topMargin = 100;
//        rl6_2.leftMargin = (mWholeWidth) / 2 - 102;
//        rl6_2.height = 0;
//        addView(mView6_2, rl6_2);


        //新能源衣服
        mView3 = new ImageView(getContext());
        mView3.setAlpha(0.0f);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img3);
        mView3.setImageBitmap(bitmap3);
        RelativeLayout.LayoutParams rl3 = new RelativeLayout.LayoutParams(translate(83), translate(47));
        rl3.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rl3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rl3.bottomMargin = translate(16);
        addView(mView3, rl3);
//
//
//        //头巾
//        mView5 = new ImageView(getContext());
//        mView5.setAlpha(0.0f);
//        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img5);
//        mView5.setImageBitmap(bitmap5);
//        RelativeLayout.LayoutParams rl5 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        rl5.topMargin = 70;
//        rl5.leftMargin = mWholeWidth / 2 - bitmap5.getWidth() / 2 - 16;
//        addView(mView5, rl5);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWholeWidth, mWholeHeight);
    }

    private int translate(int ori) {
        return (int) (ONE * ori);
    }


    private boolean mDone1 = true;

    public void addView1(final Runnable action) {
        if (mDone1) {
            mDone1 = false;
            if (mView1 == null) {
                mView1 = new ImageView(getContext());
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_1);
                mView1.setImageBitmap(bitmap1);
                mView1.setAlpha(0.0f);

                RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl1.addRule(RelativeLayout.CENTER_HORIZONTAL);
                rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                rl1.bottomMargin = translate(4);
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
                                action.run();
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
                                        action.run();
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
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img8);
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

    private Path mPath;//声明动画集合
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];

    public void addView2_1() {
        int distance = 20;
        final int standardLine = mWholeHeight - translate(16);
        if (mDone2) {
            mDone2 = false;
            if (mView2 == null) {

                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img2);
                mView2 = new ImageView(getContext());
                mView2.setImageBitmap(bitmap);


                //奶瓶
                final RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(translate(26), translate(44));
                rl.leftMargin = translate(207);
                rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                addView(mView2, rl);

                //奶瓶遮挡
                mView2_cover = new View(getContext());
                mView2_cover.setBackgroundColor(getResources().getColor(R.color.skin_color_bg_1));
                RelativeLayout.LayoutParams rl2_cover = new RelativeLayout.LayoutParams(translate(26), translate(15));
                rl2_cover.leftMargin = rl.leftMargin;
                rl2_cover.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                addView(mView2_cover, rl2_cover);


                mPath = new Path();
                mPath.moveTo(rl.leftMargin, standardLine);
                mPath.lineTo(rl.leftMargin, standardLine - translate(44) - distance);
                mPath.lineTo(rl.leftMargin, standardLine - translate(44));
                mPathMeasure = new PathMeasure(mPath, false);
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
                        rl.bottomMargin = standardLine - (int) mCurrentPosition[1] - translate(44) + translate(16);
                        Log.i("lulu_animation", "rl.bottomMargin=" + (standardLine - (int) mCurrentPosition[1] - translate(44)));
                        mView2.setLayoutParams(rl);
                    }
                });
                valueAnimator.start();
            } else {
                mPath = new Path();
                mPath.moveTo((mWholeWidth) / 2 + 90, standardLine - mView2.getHeight());
                mPath.lineTo((mWholeWidth) / 2 + 90, standardLine - mView2.getHeight() - distance);
                mPath.lineTo((mWholeWidth) / 2 + 90, standardLine);
                mPathMeasure.setPath(mPath, false);

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
                        rl.bottomMargin = standardLine - (int) mCurrentPosition[1] - translate(44) + translate(16);
                        mView2.setLayoutParams(rl);
                    }
                });
                valueAnimator.start();
            }
        }
    }


    private boolean mDone6 = true;

    public void addView6() {
        if (mDone6) {
            mDone6 = false;
            final RelativeLayout.LayoutParams rl6_2 = (LayoutParams) mView6_2.getLayoutParams();
            if (mView6 == null) {

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img6_1);
                mView6 = new ImageView(getContext());
                mView6.setImageBitmap(bitmap);

                //左边脸
                mView6_3 = new ImageView(getContext());
                mView6_3.setAlpha(0.0f);
                Bitmap bitmap6_3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img6_3);
                mView6_3.setImageBitmap(bitmap6_3);
                RelativeLayout.LayoutParams rl6_3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl6_3.leftMargin = mWholeWidth / 2 - 28 - 50;
                rl6_3.topMargin = 155;
                addView(mView6_3, rl6_3);

                //右边脸
                mView6_4 = new ImageView(getContext());
                mView6_4.setAlpha(0.0f);
                mView6_4.setImageBitmap(bitmap6_3);
                RelativeLayout.LayoutParams rl6_4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl6_4.leftMargin = mWholeWidth / 2 - 28 + 50;
                rl6_4.topMargin = 155;
                addView(mView6_4, rl6_4);


                final RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                addView(mView6, rl);

                mPath = new Path();
                mPath.moveTo(mWholeWidth / 2 - 100, 0);
                RectF rectF = new RectF(mWholeWidth / 2 - 100, -100, mWholeWidth / 2 + 100, 100);
                mPath.addArc(rectF, 180, -25);

                //头花、头发
                mPathMeasure = new PathMeasure(mPath, false);
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                valueAnimator.setDuration(900);
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mDone6 = true;
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
                        float value = (float) animation.getAnimatedValue();
                        mPathMeasure.getPosTan(value, mCurrentPosition, null);
                        rl.leftMargin = (int) mCurrentPosition[0];
                        rl.topMargin = (int) mCurrentPosition[1];
                        rl6_2.height = (int) (bitmap6_2.getHeight() * value / mPathMeasure.getLength());
                        mView6_2.setLayoutParams(rl6_2);
                        mView6.setLayoutParams(rl);
                    }
                });

                valueAnimator.start();

                //脸蛋
                mView6_3.animate().alpha(1.0f).setDuration(450).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mView6_4.animate().alpha(1.0f).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();


            } else {
                final RelativeLayout.LayoutParams rl = (LayoutParams) mView6.getLayoutParams();
                mPath.rewind();

                RectF rectF = new RectF(mWholeWidth / 2 - 100, -100, mWholeWidth / 2 + 100, 100);
                mPath.addArc(rectF, 155, 25);

                //头花、头发
                mPathMeasure = new PathMeasure(mPath, false);
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                valueAnimator.setDuration(900);
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                removeView(mView6);
                                mView6 = null;
                                mDone6 = true;
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
                        float value = (float) animation.getAnimatedValue();
                        mPathMeasure.getPosTan(value, mCurrentPosition, null);
                        rl.leftMargin = (int) mCurrentPosition[0];
                        rl.topMargin = (int) mCurrentPosition[1];
                        mView6.setLayoutParams(rl);

                        rl6_2.height = (int) ((1 - value / mPathMeasure.getLength()) * bitmap6_2.getHeight());
                        mView6_2.setLayoutParams(rl6_2);
                    }
                });
                valueAnimator.start();


                //脸蛋
                mView6_4.animate().alpha(0.0f).setDuration(450).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                removeView(mView6_4);
                                mView6_3.animate().alpha(0.0f).setDuration(450).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        post(new Runnable() {
                                            @Override
                                            public void run() {
                                                removeView(mView6_3);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                }).start();
                            }
                        });

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();

            }
        }
    }

    private boolean mDone5 = true;

    public void addView5() {
        if (mDone5) {
            mDone5 = false;

            if (mView5.getAlpha() == 0) {

                mView5.animate()
                        .alpha(1.0f)
                        .translationY(-10)
                        .setDuration(400)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mDone5 = true;
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

                mView5.animate()
                        .alpha(0.0f)
                        .translationY(10)
                        .setDuration(400)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDone5 = true;
                                    }
                                });
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }
    }

    private boolean mDone3 = true;

    public void addView3() {
        if (mDone3) {
            mDone3 = false;
            if (mView3.getAlpha() == 0) {
                mView3.animate().alpha(1.0f).setDuration(450).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mDone3 = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            } else {
                mView3.animate().alpha(0.0f).setDuration(450).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mDone3 = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        }
    }


    private boolean mDone9 = true;

    public void addView9() {
        if (mDone9) {
            mDone9 = false;

            if (mView9 == null) {
                mView9 = new ImageView(getContext());
                mView9.setAlpha(0.0f);
                Bitmap bitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_guide_img9);
                mView9.setImageBitmap(bitmap9);

                RelativeLayout.LayoutParams rl9 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rl9.topMargin = 120;
                rl9.leftMargin = mWholeWidth / 2 - bitmap9.getWidth() / 2 - 16;
                addView(mView9, rl9);

                mView9.animate()
                        .alpha(1.0f)
                        .translationY(-10)
                        .setDuration(400)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mDone9 = true;
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

                mView9.animate()
                        .alpha(0.0f)
                        .translationY(10)
                        .setDuration(400)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeView(mView9);
                                        mView9 = null;
                                        mDone9 = true;
                                    }
                                });
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }
    }

    public void addView10() {
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
