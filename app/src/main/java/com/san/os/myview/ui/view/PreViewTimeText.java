package com.san.os.myview.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;


/**
 * @author luluc@yiche.com
 * @Description
 */
public class PreViewTimeText extends TextView {

    private Bitmap mBitmap;
    private BitmapFactory.Options mOptions;

    public PreViewTimeText(Context context) {
        super(context);
        init();
    }

    public PreViewTimeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PreViewTimeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Rect rectLeft;
    int height = ToolBox.sp2px(getContext(), 15) + 40;

    private void init() {

        mOptions = new BitmapFactory.Options();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_zhibo_clock_nor, mOptions);
        setTextSize(3);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        height = getMeasuredHeight();
        Log.i("lulu_pre_text", "getMeasuredHeight() == " + getMeasuredHeight());
        height = mOptions.outHeight + 40;
        rectLeft = new Rect(20, 20, 20+ mOptions.outWidth, 20 + mOptions.outHeight);
        setPadding(mOptions.outWidth+40, 20, 0, 20);
        canvas.drawBitmap(mBitmap, null, rectLeft, new Paint());


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        rectLeft.bottom = h - rectPadding;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
