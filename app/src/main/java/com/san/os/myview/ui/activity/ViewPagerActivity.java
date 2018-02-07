package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.san.os.myview.R;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-05-16 09:50
 */

public class ViewPagerActivity extends FragmentActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_activity);
    }


}
