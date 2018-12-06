package com.san.os.myview.ui.view.decorator;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-06-07 11:46
 */

public abstract class MyLayout {

//    private LinearLayout mLinearLayout;
//
//    public void MyLinearLayout(LinearLayout linearLayout) {
//        mLinearLayout = linearLayout;
//    }

    public abstract LinearLayout getLayoutParams(Context context, float width, float height);



}
