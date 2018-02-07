package com.san.os.myview;

import android.app.Application;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class MyViewApplication extends Application {

    private static MyViewApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static MyViewApplication getInstance() {
        return instance;
    }
}
