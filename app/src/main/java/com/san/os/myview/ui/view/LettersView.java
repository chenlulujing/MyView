package com.san.os.myview.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class LettersView extends View {

    private static final int TOTALCOUNT = 26;
    private static float TEXT_SIZE;

    private Context mContext;
    private Paint mPaint;

    private OnSelectedLetterListener mOnSelectedLetterListener;

    private String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    public LettersView(Context context) {
        super(context);
        init(context);
    }

    public LettersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LettersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public interface OnSelectedLetterListener {
        void onSelectedLetter(int index, String letter);
    }

    public void setOnSelectedLetterListener(OnSelectedLetterListener listener) {
        mOnSelectedLetterListener = listener;
    }

    private void init(Context context) {
        mContext = context;
        Log.i("lulu_letter","(int)screen_height=="+(ToolBox.getDisplayHeight((Activity) mContext) - ToolBox.getDisplayStateHeight((Activity) mContext)));
        Log.i("lulu_letter","(float)screen_height=="+(float)(ToolBox.getDisplayHeight((Activity) mContext) - ToolBox.getDisplayStateHeight((Activity) mContext)));

        TEXT_SIZE = ((ToolBox.getDisplayHeight((Activity) mContext) - ToolBox.getDisplayStateHeight((Activity) mContext)) / ((float) TOTALCOUNT));
        Log.i("lulu_letter","(float)text_size=="+((ToolBox.getDisplayHeight((Activity) mContext) - ToolBox.getDisplayStateHeight((Activity) mContext)) / ((float) TOTALCOUNT)));
        Log.i("lulu_letter","(int)text_size=="+(int)((ToolBox.getDisplayHeight((Activity) mContext) - ToolBox.getDisplayStateHeight((Activity) mContext)) / ((float) TOTALCOUNT)));
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(R.color.blue_2467D5));
        mPaint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int OriWidth = MeasureSpec.getSize(widthMeasureSpec);
        int OriHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("lulu_letter", "OriWidth==" + OriWidth + ";OriHeight==" + OriHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < TOTALCOUNT; i++) {
            canvas.drawText(mLetters[i], ((float) getWidth() -  TEXT_SIZE) / 2, (i + 1) * TEXT_SIZE, mPaint);
            canvas.drawLine(0, (i + 1) * TEXT_SIZE, getWidth(), TEXT_SIZE * (i + 1), mPaint);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
        float y = event.getY();
        int index = (int) (Math.ceil(y / TEXT_SIZE)) - 1;
        if (mOnSelectedLetterListener != null) {
            mOnSelectedLetterListener.onSelectedLetter(index, mLetters[index]);
        }
        return true;
    }
}
