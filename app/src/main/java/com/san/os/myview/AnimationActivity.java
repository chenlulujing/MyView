package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class AnimationActivity extends FragmentActivity {

    private TextView mView;
    private ListView mRest;
    private LinearLayout mRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
    }

    private void initView() {
        mView = (TextView) findViewById(R.id.recommend);
        mRest = (ListView) findViewById(R.id.rest);
        mRoot = (LinearLayout) findViewById(R.id.root);

        mRest.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(AnimationActivity.this);
                tv.setText(position+"");
                return tv;
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setVisibility(View.GONE);
            }
        });
//        mRest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Animation alphaAnimation = new AlphaAnimation(1f,0f);
////                alphaAnimation.setDuration(5000);
//
//
////                Animation rotateAnimation = new RotateAnimation(0f, 360f);
////                rotateAnimation.setDuration(3000);
//
//
////                Animation scaleAnimation = new ScaleAnimation(0.1f, 1.0f,0.1f,1.0f);
////                scaleAnimation.setDuration(3000);
//
//
////                Animation translateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
////                translateAnimation.setDuration(3000);
//
////                Animation animationSet = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.in_lefttoright);
////                animationSet.setDuration(3000);
////                mView.startAnimation(animationSet);
////                mRoot.removeView(mView);
//                mView.setVisibility(View.GONE);
//            }
//        });
    }


}
