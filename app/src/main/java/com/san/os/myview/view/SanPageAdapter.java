package com.san.os.myview.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 综述排行 viewpagerAdapter
 */
public class SanPageAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> fragments = new ArrayList<>();

    public SanPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setData(List<T> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


    @Override
    public void destroyItem(ViewGroup viewGroup, int i, Object o) {
//        super.destroyItem(viewGroup, i, o);
    }

}
