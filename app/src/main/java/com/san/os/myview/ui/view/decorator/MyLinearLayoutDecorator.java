package com.san.os.myview.ui.view.decorator;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-06-07 11:58
 */

public class MyLinearLayoutDecorator extends Decorator{





    @Override
    public LinearLayout getLayoutParams(Context context,float width, float height) {
        return new LinearLayout(context);
    }
}
