package com.san.os.myview.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.san.os.myview.R;

import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-05-16 10:01
 */

public class BannerView extends RelativeLayout{

    private AutoNextViewPager mViewPager;
    private LinearLayout mIndicator;
    private List<Item> mData;

    public BannerView(Context context) {
        super(context);
        init(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);
        mIndicator = (LinearLayout) findViewById(R.id.indicator);
        mViewPager = (AutoNextViewPager) findViewById(R.id.viewpager);
    }

    public void setData(List<Item> list){
        if(list==null&&list.size()==0){
            return;
        }
        mData = list;

    }

    public class Item {
        public String title;
        public String picImg;

    }

}
