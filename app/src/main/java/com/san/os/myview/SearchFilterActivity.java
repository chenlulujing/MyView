package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
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

public class SearchFilterActivity extends FragmentActivity {


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

        mFilterView.setObserver(observer);
        mFilterView.initdata(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, mReslultFragment).commitAllowingStateLoss();
    }


    public void openFilterPage() {
        mDrawerLayout.openDrawer(mDrawerContent);
    }


    private Consumer<FilterItemModel> observer = new Consumer<FilterItemModel>() {
        @Override
        public void accept(FilterItemModel item) throws Exception {
            switch (item.classification_id) {
                case FilterItemModel.CLASS_CHANEL_PRIMARY:
                    mFilterView.chanelFilterClick(item);
                    break;
                case FilterItemModel.CLASS_CHANEL_SECOEND:
                    mFilterView.chanelSecondFilterClick(item);
                    break;
                case FilterItemModel.CLASS_WORDSIZE:
                    mFilterView.wordSizeFilterClick(item);
                    break;
                case FilterItemModel.CLASS_STATUS:
                    mFilterView.statusFilterClick(item);
                    break;
                case FilterItemModel.SORT:
                    ((SearchReslultFragment) mReslultFragment).dropDownMenuItemClick(item);
                    break;
                default:
            }
        }
    };

}
