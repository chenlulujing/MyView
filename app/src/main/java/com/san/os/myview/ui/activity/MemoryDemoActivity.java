package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.san.os.myview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-04-25 14:35
 */

public class MemoryDemoActivity extends FragmentActivity {

    private List<Point> mData;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_activity);
        initList();
        sub(mData);
        printF();
        printC();
    }

    private void printF() {
        for(Point p:mData){
            Log.i("lulu_memory",p.x+"");
        }
    }

    private void printC() {
//        for(String str:mData){
//            Log.i("lulu_memory",str);
//        }
    }

    private void initList(){
        mData = new ArrayList<>();
        for(int i=0;i<5;i++){
            Point p = new Point();
            p.x = i;
            p.y = i;
            mData.add(p);
        }
    }

    private void sub(List<Point> list){
        list.get(0).x = 33;
        list.subList(0,2);
    }


    class Point {
        public int x;
        public int y;
    }


}
