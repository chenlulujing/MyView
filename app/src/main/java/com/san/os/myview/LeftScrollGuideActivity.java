package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.san.os.myview.view.ScrollGuideView;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-18 14:55
 */

public class LeftScrollGuideActivity extends AppCompatActivity {

    private ScrollGuideView  mView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leftscrollguide);
        mView = (ScrollGuideView ) findViewById(R.id.roundedrectangleview);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.start();
            }
        });


    }
}
