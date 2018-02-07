package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-06-27 09:36
 */

public class MeasureActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        TextView tv = (TextView) findViewById(R.id.recommend);

    }



}
