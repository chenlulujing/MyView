package com.san.os.myview.ui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * @author zhangwenjia@yiche.com
 * @ClassName AutoNextViewPager
 * @Description 每隔5秒自动轮播的viewpager控件
 * @date Dec 1, 2014 3:42:39 PM
 */
public class AutoNextViewPager extends ScrollViewPager {

    private final String TAG = AutoNextViewPager.class.getSimpleName();

    private static int DURATION = 5000;
    private final int MESSAGE_REMOVE = 1;

    private boolean mIsTouching;

    private OnAsClickListener mOnAsClickListener;

    public interface OnAsClickListener {
        void click();
    }

    public void setOnAsLongClickListener(OnAsLongClickListener listener) {
        this.mOnAsLongClickListener = listener;
    }

    private OnAsLongClickListener mOnAsLongClickListener;

    public interface OnAsLongClickListener {
        void longClick();
    }

    public void setOnAsClickListener(OnAsClickListener listener) {
        this.mOnAsClickListener = listener;
    }

    public AutoNextViewPager(Context context) {
        super(context);
        init();
    }

    public AutoNextViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        AutoNextViewPager.this.setOnTouchListener(new MyOnTouchListener());
        startNextPage();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mIsTouching) {
                int currentIndex = AutoNextViewPager.this.getCurrentItem();
                AutoNextViewPager.this.setCurrentItem(currentIndex + 1);
                startNextPage();
            }
        }
    };

    public void startNextPage() {
        if (handler != null) {
            Message message = new Message();
            message.what = MESSAGE_REMOVE;
            stopNextPage();
            handler.sendMessageDelayed(message, DURATION);
        }

    }

    public void startNextPage(boolean isRefresh) {
        Message message = new Message();
        message.what = MESSAGE_REMOVE;
        mIsTouching = false;
        handler.sendMessageDelayed(message, DURATION);
    }

    public void stopNextPage() {
        if (handler != null) {
            handler.removeMessages(MESSAGE_REMOVE);
        }
    }

    private class MyOnTouchListener implements View.OnTouchListener {
        private long startTimeMillis;
        private float onTouchDownX;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 记录按下去的X轴
                    onTouchDownX = event.getX();
                    // 记录下按下去的时间
                    startTimeMillis = System.currentTimeMillis();
                    mIsTouching = true;
                    stopNextPage();
                    break;

                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    // 当手指抬起的时候记录下抬起的时间，然后在减去开始的时间，如果时间差小于500 可以判断是点击事件
                    long duration = System.currentTimeMillis() - startTimeMillis;
                    float onTouchUpX = event.getX();
                    float abs = Math.abs(onTouchDownX - onTouchUpX);
                    if (duration < 500 && abs < 100) {
                        v.performClick();
                    }
                    if (duration < 500 && mOnAsClickListener != null && abs < 100) {
                        Log.i(TAG, "click");
                        mOnAsClickListener.click();
                    }
                    // 模拟长按事件
                    if (duration > 500 && onTouchUpX == onTouchDownX && mOnAsClickListener != null) {
                        Log.i(TAG, "long click");
                        if (mOnAsLongClickListener != null) {
                            mOnAsLongClickListener.longClick();
                        }
                    }
                    mIsTouching = false;
                    startNextPage();
                    break;
            }
            return false;

        }
    }

    private float donwX, donwY, moveX, moveY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                donwX = ev.getX();
                donwY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getX();
                moveY = ev.getY();
                if (Math.abs(moveX - donwX) < Math.abs(moveY - donwY)) {
                    // 上下滑动
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    // 左右滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
