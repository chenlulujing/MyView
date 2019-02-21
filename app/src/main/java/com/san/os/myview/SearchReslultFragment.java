package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.model.FilterItemModel;
import com.san.os.myview.tool.ToolBox;
import com.san.os.myview.view.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-18 17:46
 */

public class SearchReslultFragment extends Fragment {

    private View mRootView;

    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private DropDownMenu mDropDownMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_resultview, null);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        mDrawerLayout = (DrawerLayout) mRootView.findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) mRootView.findViewById(R.id.drawer_content);
        mDropDownMenu = (DropDownMenu) mRootView.findViewById(R.id.dropdownmenu);
        mDropDownMenu.setObserver(observer);

        initDropDownMenu();

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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


        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commitAllowingStateLoss();

        mRootView.findViewById(R.id.sort).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mDropDownMenu.switchMenu();
            }
        });


        mRootView.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDrawerLayout.openDrawer(mDrawerContent);
                if (getActivity() instanceof SearchFilterActivity) {
                    ((SearchFilterActivity) getActivity()).openFilterPage();
                }
            }
        });
    }

    private String citys_des[] = {"按综合","按人气","按更新","按字数"};
    private String citys_id[] = {"metadata","word_count","chapter_last_online_time","read_book_uv"};
    private void initDropDownMenu() {

        LinearLayout sortRootView = new LinearLayout(getActivity());
        sortRootView.setBackgroundColor(ToolBox.getResources().getColor(R.color.white));
        sortRootView.setOrientation(LinearLayout.VERTICAL);
        for(int i=0,size=citys_des.length;i<size;i++){
            TextView tv = new TextView(getActivity());
            tv.setText(citys_des[i]);
            sortRootView.addView(tv);
        }
        //init dropdownview
        List<FilterItemModel> mdatas = new ArrayList<>();
        for(int i=0,size = citys_des.length;i<size;i++){
            FilterItemModel item = new FilterItemModel("排序",FilterItemModel.SORT,citys_des[i],citys_id[i]);
            mdatas.add(item);
        }
        mDropDownMenu.setDropDownMenu(mdatas);
    }

    private Consumer observer;
    public void setObserver(Consumer<FilterItemModel> consumer) {
        observer = consumer;
    }

    public void dropDownMenuItemClick(FilterItemModel item) {
        if(mDropDownMenu!=null){
            mDropDownMenu.refresh(item);
        }
    }
}
