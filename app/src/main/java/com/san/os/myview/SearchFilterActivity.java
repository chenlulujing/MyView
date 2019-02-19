package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.RelativeLayout;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-18 16:46
 */

public class SearchFilterActivity extends FragmentActivity {


    Fragment mReslultFragment;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilter);
        initView();
    }

    private void initView() {
        mReslultFragment = new SearchReslultFragment();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (RelativeLayout) findViewById(R.id.drawer_content);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, mReslultFragment).commitAllowingStateLoss();
    }


    public void openFilterPage() {
        mDrawerLayout.openDrawer(mDrawerContent);
    }

}
