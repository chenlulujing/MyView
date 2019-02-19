package com.san.os.myview;

import android.os.HandlerThread;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-01-09 19:45
 */

public class MyHandlerThread extends HandlerThread {
    public MyHandlerThread(String name) {
        super(name);
    }

    public MyHandlerThread(String name, int priority) {
        super(name, priority);
    }

    @Override
    public void run() {
        try {
            super.run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
