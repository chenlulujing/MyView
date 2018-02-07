package com.san.os.myview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class PopActivity extends FragmentActivity {

    // 声明PopupWindow对象的引用
    private PopupWindow popupWindow;
    private boolean isLeft;
    Button top;
    Button left;
    TextView mRecommend;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        // 点击按钮弹出菜单

        left = (Button) findViewById(R.id.btn1);

        top = (Button) findViewById(R.id.btn2);
        mRecommend = (TextView) findViewById(R.id.recommend);
        mRecommend.setVisibility(View.INVISIBLE);
        left.setOnClickListener(popClick_left);
        top.setOnClickListener(popClick1_top);
    }

    View.OnClickListener popClick_left = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            isLeft = true;
            getPopupWindow();
            // 这里是位置显示方式,在屏幕的左侧
            popupWindow.showAtLocation(v, Gravity.LEFT, 0, 0);
        }
    };

    View.OnClickListener popClick1_top = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mRecommend.setVisibility(View.VISIBLE);
            ObjectAnimator fadeAltAnim = ObjectAnimator.ofFloat(mRecommend, "alpha", 1.0f,0.0f);
            fadeAltAnim.setDuration(2000);
            fadeAltAnim.start();
        }
    };

    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow() {

//        TranslateAnimation translate =
//                new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
//                        Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1);
//        translate.setDuration(1000);
//        translate.setFillAfter(true);
//        left.startAnimation(translate);

        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getLayoutInflater().inflate(R.layout.view_pop, null,
                false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, LayoutParams.WRAP_CONTENT, dip2px(25), true);
        // 设置动画效果

//        popupWindow.setAnimationStyle(isLeft ? R.style.AnimationFade_left : R.style.AnimationFade_top);

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
    }

    /***
     * 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }


    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }

    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

}
