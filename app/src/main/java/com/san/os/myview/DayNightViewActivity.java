package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.san.os.myview.view.DayNightSwitchView;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-03-17 14:06
 */

public class DayNightViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daynightview);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DayNightSwitchView) findViewById(R.id.daynight_view)).switchModel(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}
