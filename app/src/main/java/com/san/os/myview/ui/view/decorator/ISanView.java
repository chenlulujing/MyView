package com.san.os.myview.ui.view.decorator;

import android.view.ViewGroup;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-06-07 11:38
 */

public interface ISanView {

    int getRealSize(float i);

    ViewGroup.LayoutParams getLayouParams(float width, float height);
}
