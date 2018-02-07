package com.san.os.myview.tool;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.san.os.myview.MyViewApplication;

import java.lang.reflect.Field;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class ToolBox {


    /**
     * 获取屏幕宽度px
     *
     * @param activity
     */
    public static int getDisplayWith(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕宽度高度
     *
     * @param activity
     */
    public static int getDisplayHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     */
    public static int getDisplayStateHeight(Activity activity) {
        int statusHeight;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (Build.VERSION.SDK_INT < 15) {
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusHeight = frame.top;
        } else {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0, sbar = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                sbar = activity.getResources().getDimensionPixelSize(x);
                statusHeight = sbar;

            } catch (Exception e1) {
                Log.e("fail", "get status bar height fail");
                e1.printStackTrace();
                statusHeight = 0;
            }
        }
        return statusHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) MyViewApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }



    public static View inflate(int id, ViewGroup container, boolean attach) {
        return LayoutInflater.from(MyViewApplication.getInstance()).inflate(id, container, attach);
    }

    public static View inflate(Context context, int id, ViewGroup container, boolean attach) {
        return LayoutInflater.from(context).inflate(id, container, attach);
    }

    public static Resources getResources() {
        return MyViewApplication.getInstance().getResources();
    }


    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
