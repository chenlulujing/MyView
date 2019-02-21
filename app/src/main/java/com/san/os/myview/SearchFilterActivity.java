package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.san.os.myview.model.FilterItemModel;
import com.san.os.myview.model.SearchFilterBuilder;
import com.san.os.myview.view.FilterView;

import io.reactivex.functions.Consumer;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-18 16:46
 */

public class SearchFilterActivity extends FragmentActivity implements View.OnClickListener {


    Fragment mReslultFragment;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerContent;
    private FilterView mFilterView;

    private SearchFilterBuilder mSearchFilterBuilder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilter);
        initView();
        initData();
    }

    private void initData() {
        mSearchFilterBuilder = new SearchFilterBuilder();
    }

    private void initView() {
        mReslultFragment = new SearchReslultFragment();
        ((SearchReslultFragment) mReslultFragment).setObserver(observer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (RelativeLayout) findViewById(R.id.drawer_content);
        mFilterView = (FilterView) findViewById(R.id.filterview);

        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.enter).setOnClickListener(this);


        mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });


        mFilterView.setObserver(observer);
        mFilterView.initdata(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, mReslultFragment).commitAllowingStateLoss();
    }


    public void openFilterPage() {
        mDrawerLayout.openDrawer(mDrawerContent);
        if (mReslultFragment != null && mReslultFragment instanceof SearchReslultFragment) {
            ((SearchReslultFragment) mReslultFragment).closeSortView();
        }
    }


    private Consumer<FilterItemModel> observer = new Consumer<FilterItemModel>() {
        @Override
        public void accept(FilterItemModel item) throws Exception {
            switch (item.classification_id) {
                case FilterItemModel.CLASS_CHANEL_PRIMARY:
                    mFilterView.chanelFilterClick(item);
                    mSearchFilterBuilder.category_id = item.mIsSelected ? item.tagId : "";
                    break;
                case FilterItemModel.CLASS_CHANEL_SECOEND:
                    mFilterView.chanelSecondFilterClick(item);
                    mSearchFilterBuilder.category_id = item.mIsSelected ? item.tagId : "";
                    break;
                case FilterItemModel.CLASS_WORDSIZE:
                    mFilterView.wordSizeFilterClick(item);
                    mSearchFilterBuilder.wordCount = item.mIsSelected ? item.tagId : "";
                    break;
                case FilterItemModel.CLASS_STATUS:
                    mFilterView.statusFilterClick(item);
                    mSearchFilterBuilder.serialize_status = item.mIsSelected ? item.tagId : "";
                    break;
                case FilterItemModel.SORT:
                    ((SearchReslultFragment) mReslultFragment).dropDownMenuItemClick(item);
                    mSearchFilterBuilder.order = item.tagId;
                    break;
                default:
            }

            String params = mSearchFilterBuilder.toString();
            Log.i("llc_filter", params);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter:

                break;
            case R.id.reset:
                mFilterView.clearStatus();
                mSearchFilterBuilder.clearStatus();
                break;
            default:
        }
    }
}
