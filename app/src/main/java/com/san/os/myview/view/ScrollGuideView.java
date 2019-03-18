package com.san.os.myview.view;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.san.os.myview.R;
import com.san.os.myview.utils.SizeUtils;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-18 17:25
 */

public class ScrollGuideView extends RelativeLayout {

    public static final int RADUIS = SizeUtils.translate(64);
    public static final int DURATIONTIME = 1000;
    public static final int WIDTH = SizeUtils.translate(252);
    public static final int TOP_MARGIN = SizeUtils.translate(180);


    private ScrollShadowView mRoundedRectangleView;
    private ImageView mCircleView;


    public ScrollGuideView(Context context) {
        super(context);
        init(context);
    }

    public ScrollGuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mRoundedRectangleView = new ScrollShadowView(context);
        addView(mRoundedRectangleView);

        mCircleView = new ImageView(context);
        mCircleView.setImageDrawable(context.getResources().getDrawable(R.drawable.img_0));
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RADUIS * 2, RADUIS * 2);
        rlp.topMargin = TOP_MARGIN;
        rlp.leftMargin = SizeUtils.getDisplayWidth() - RADUIS;
        rlp.rightMargin = -RADUIS;
        addView(mCircleView, rlp);


    }

    private int getCircleMoveLenth() {
        return ScrollShadowView.WIDTH;
    }


    public void start() {
        if (mCircleView.getTranslationX() != 0) {
            mCircleView.animate()
                    .setDuration(0)
                    .translationX(getCircleMoveLenth()).start();
        }


        mRoundedRectangleView.headerMove();
        mCircleView.animate()
                .translationX(-getCircleMoveLenth())
                .setDuration(DURATIONTIME)
                .setListener(new Animator.AnimatorListener() {
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
                })
                .start();
    }
}
