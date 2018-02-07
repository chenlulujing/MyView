package com.san.os.myview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.san.os.myview.tool.ToolBox;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-06-27 09:51
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      int myWidthMeasureSpec  =  MeasureSpec.makeMeasureSpec(ToolBox.dip2px(200),MeasureSpec.EXACTLY);
        super.onMeasure(myWidthMeasureSpec, heightMeasureSpec);
        Log.i("lulu_onMeasure","MeasureSpec.getMode(widthMeasureSpec) == "+widthMode);
        Log.i("lulu_onMeasure","MeasureSpec.getMode(heightMeasureSpec) == "+heightMode);
        Log.i("lulu_onMeasure","MeasureSpec.getSize(widthMeasureSpec) == "+widthSize);
        Log.i("lulu_onMeasure","MeasureSpec.getSize(heightMeasureSpec) == "+heightSize);
        if(widthMode == MeasureSpec.EXACTLY){
            Log.i("lulu_onMeasure","widthMode == "+"EXACTLY");
        }
        if(widthMode == MeasureSpec.UNSPECIFIED){
            Log.i("lulu_onMeasure","widthMode == "+"UNSPECIFIED");
        }
        if(widthMode == MeasureSpec.AT_MOST){
            Log.i("lulu_onMeasure","widthMode == "+"AT_MOST");
        }
        if(heightMode == MeasureSpec.EXACTLY){
            Log.i("lulu_onMeasure","heightMode == "+"EXACTLY");
        }
        if(heightMode == MeasureSpec.UNSPECIFIED){
            Log.i("lulu_onMeasure","heightMode == "+"UNSPECIFIED");
        }
        if(heightMode == MeasureSpec.AT_MOST){
            Log.i("lulu_onMeasure","heightMode == "+"AT_MOST");
        }


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
