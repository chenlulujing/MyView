package com.san.os.myview.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.san.os.myview.R;
import com.san.os.myview.ui.view.GuideClassifyGroupView;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-23 10:22
 */

public class AnimationGuideActivity extends Activity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton10;

    private int FLAG1 = 1 << 0;
    private int FLAG2 = 1 << 1;
    private int FLAG3 = 1 << 2;
    private int FLAG4 = 1 << 3;
    private int FLAG5 = 1 << 4;
    private int FLAG6 = 1 << 5;
    private int FLAG7 = 1 << 6;
    private int FLAG8 = 1 << 7;
    private int FLAG9 = 1 << 8;
    private int FLAG10 = 1 << 9;

    private int mFlag;

    private int FLAG = 0000000000000000;

    private GuideClassifyGroupView mGuideView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_guide);


        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton9 = (Button) findViewById(R.id.button9);
        mButton10 = (Button) findViewById(R.id.button10);
        mGuideView = (GuideClassifyGroupView) findViewById(R.id.guide_groupview);
        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) mGuideView.getLayoutParams();
        llp.width = (int) (getDisplayWidth(this) * ((float) 330 / 375));
        llp.height = (int) (llp.width * ((float) 180 / 330));
        mGuideView.setLayoutParams(llp);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton10.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mFlag = mFlag | FLAG1;
                mGuideView.addView1(new Runnable() {
                    @Override
                    public void run() {
                        mButton1.setClickable(true);
                    }
                });
                mButton1.setClickable(false);
                break;
            case R.id.button2:
                mFlag = mFlag | FLAG2;
                mGuideView.addView2_1(new Runnable() {
                    @Override
                    public void run() {
                        mButton2.setClickable(true);
                    }
                });
                mButton2.setClickable(false);
                break;
            case R.id.button3:
                mFlag = mFlag | FLAG3;
                mGuideView.addView3(new Runnable() {
                    @Override
                    public void run() {
                        mButton3.setClickable(true);
                    }
                });
                mButton3.setClickable(false);
                break;
            case R.id.button4:
                mFlag = mFlag | FLAG4;
                mGuideView.addView4(new Runnable() {
                    @Override
                    public void run() {
                        mButton4.setClickable(true);
                    }
                });
                mButton4.setClickable(false);
                break;
            case R.id.button5:
                mGuideView.addView5(new Runnable() {
                    @Override
                    public void run() {
                        mButton5.setClickable(true);
                    }
                });
                mButton5.setClickable(false);
                break;
            case R.id.button6:
                mGuideView.addView6(new Runnable() {
                    @Override
                    public void run() {
                        mButton6.setClickable(true);
                    }
                });
                mButton6.setClickable(false);
                break;
            case R.id.button7:
                mGuideView.addView7(new Runnable() {
                    @Override
                    public void run() {
                        mButton7.setClickable(true);
                    }
                });
                mButton7.setClickable(false);
                break;
            case R.id.button8:
                mGuideView.addView8(new Runnable() {
                    @Override
                    public void run() {
                        mButton8.setClickable(true);
                    }
                });
                mButton8.setClickable(false);

                break;
            case R.id.button9:
                mGuideView.addView9(new Runnable() {
                    @Override
                    public void run() {
                        mButton9.setClickable(true);
                    }
                });
                mButton9.setClickable(false);
                break;
            case R.id.button10:
                mGuideView.addView10(new Runnable() {
                    @Override
                    public void run() {
                        mButton10.setClickable(true);
                    }
                });
                mButton10.setClickable(false);
                break;
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
                (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

}
