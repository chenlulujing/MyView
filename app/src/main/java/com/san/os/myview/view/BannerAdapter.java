package com.san.os.myview.view;

import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;

/**
 * @author chenlulu@yiche.com
 * @ClassName BannerAdapter
 * @Description
 * @date 2014-12-2 上午11:20:08
 */
public class BannerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout ll = new LinearLayout(container.getContext());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if(position==0){
            ll.setBackgroundColor(ToolBox.getResources().getColor(R.color.red));
        }else {
            ll.setBackgroundColor(ToolBox.getResources().getColor(R.color.blue));
        }

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(container.getContext());
        horizontalScrollView.addView(ll);

        for(int i=0,size=10;i<size;i++){
            TextView tv = new TextView(container.getContext());
            tv.setText(" 背景"+i+" ");
            tv.setGravity(Gravity.CENTER);
            ll.addView(tv,llp);
        }
        container.addView(horizontalScrollView,new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 30));
        return horizontalScrollView;

    }


}
