package com.san.os.myview.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.san.os.myview.MyViewApplication;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-03-02 10:33
 */

public class SizeUtils {

    public static float getOneByWidth(float width) {
        return getDisplayWidth() / width;
    }

    public static float getOneByHeight(float height) {
        return getDisplayHeight() / height;
    }

    public static float getItemWidth(float leftMargin, float rightMargin, float count) {
        return (getDisplayWidth() - leftMargin - rightMargin) / count;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }

    public static int translate(float one){
        return (int)(getDisplayWidth()/750f*one);
    }


    public static int getDisplayHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) MyViewApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getDisplayWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) MyViewApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }


    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) MyViewApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
