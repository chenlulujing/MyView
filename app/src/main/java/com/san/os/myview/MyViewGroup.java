package com.san.os.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class MyViewGroup extends ViewGroup {
    private Context mContext;

    public MyViewGroup(Context context) {
        super(context);
        mContext = context;
        init();

    }


    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    private void init() {
        Button btn1 = new Button(mContext);
        btn1.setText("btn1");
        this.addView(btn1);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
