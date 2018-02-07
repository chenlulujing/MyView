package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.san.os.myview.R;
import com.san.os.myview.ui.fragment.Fragment_nav;
import com.san.os.myview.ui.view.NavView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class NavActivity extends FragmentActivity {

    private NavView mNav;
    private ViewPager mVP;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private String[] mTitles;
    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        initView();
    }

    private void initView() {
        mNav = (NavView) findViewById(R.id.nav);
        mVP = (ViewPager) findViewById(R.id.viewpager);
//        mTitles = new String[]{"要闻", "说车", "视频","图片"};
        mTitles = new String[]{"要闻", "说车", "视频","图片","新车","导购","评测"};
        mFragments = new ArrayList<Fragment>();
        mNav.init(mTitles);
        for (int size = mTitles.length, i = 0; i < size; i++) {
            mFragments.add(Fragment_nav.newInstance(mTitles[i]));
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mVP.setAdapter(mAdapter);
        mVP.setCurrentItem(mIndex);
        mNav.setSelectPosition(mIndex,0);
        mVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("lulu_nav","onPageScrolled:"+"position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);
                mNav.setSelectPosition(position, positionOffset);

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
