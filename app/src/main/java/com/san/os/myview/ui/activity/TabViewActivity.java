package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.san.os.myview.R;
import com.san.os.myview.ui.fragment.TabFragment;
import com.san.os.myview.ui.fragment.TabFragmentPagerAdapter;
import com.san.os.myview.ui.view.TabView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-04-13 09:18
 */

public class TabViewActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, TabView.OnCheckedChangeListener {

    private ArrayList<Fragment> mFragments;
    private TabView mTabView;
    private ViewPager mViewpager;
    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabview);
        initView();
    }

    private void initView() {
        mTabView = (TabView) findViewById(R.id.tabview);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mFragments = new ArrayList<>();
        mViewpager.setOnPageChangeListener(this);
        List<String> tabs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tabs.add("说车");
            mFragments.add(TabFragment.newInstance("栏目" + (i + 1)));
        }
        mTabView.setDataRadioButton(tabs);
        mTabView.setOnCheckedChangeListener(this);
        mViewpager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragments));

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("lulu_tab", "onPageSelected_position==" + position);
        if (mIndex != position) {
            mTabView.setTabSeleted(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(int index) {
        Log.i("lulu_tab", "onCheckedChanged_index==" + index);
        mViewpager.setCurrentItem(index);
        mIndex = index;
    }
}
