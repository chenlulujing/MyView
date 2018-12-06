package com.san.os.myview.ui.view.commen;

import android.content.Context;
import android.widget.RelativeLayout;

import com.san.os.myview.utils.SizeUtils;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-03-01 20:20
 */

public abstract class ISanRelativeLayout extends RelativeLayout {


    protected float ONE;
    protected int WIDTH;
    protected int HEIGHT;

    protected static final int MATCH_PARENT = LayoutParams.MATCH_PARENT;
    protected static final int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;


    public ISanRelativeLayout(Context context, int orientation, int screenWidthInDesign, float width, float height) {
        super(context);
        if (screenWidthInDesign == 0) {
            throw new RuntimeException("screenWidthInDesign cant be 0");
        }
        ONE = SizeUtils.getDisplayWidth() / (float) screenWidthInDesign;
        WIDTH = translate(width);
        HEIGHT = translate(height);
        initView(context);
    }

    protected int translate(float size) {
        return (int) (size * ONE);
    }


    protected abstract void initView(Context context);


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (WIDTH != 0 && HEIGHT != 0) {
            setMeasuredDimension(WIDTH, HEIGHT);
        }
    }


    protected LayoutParams getLayoutParams(int width, int height) {
        return new LayoutParams(translate(width), translate(height));
    }

}
