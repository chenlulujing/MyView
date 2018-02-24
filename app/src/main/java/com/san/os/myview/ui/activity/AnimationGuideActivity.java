package com.san.os.myview.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
        mGuideView = (GuideClassifyGroupView) findViewById(R.id.guide_groupview);
//        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) mGuideView.getLayoutParams();
//        llp.width = 465;
//        llp.height = 363;
//        mGuideView.setLayoutParams(llp);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mGuideView.addView1();
                break;
            case R.id.button2:
                mGuideView.addView2_1();
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                mGuideView.addView4();
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                mGuideView.addView6();
                break;
            case R.id.button7:
                mGuideView.addView7();
                break;
            case R.id.button8:
                mGuideView.addView8();
                break;
            case R.id.button9:
                mGuideView.addView7();
                break;
        }
    }

}
