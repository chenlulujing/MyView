package com.san.os.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2018-02-07 14:33
 */

public class TabViewBottomActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabview_bottom);
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.tab_news);
        radioButton1.setOnClickListener(this);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.tab_cheyou);
        radioButton2.setOnClickListener(this);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.tab_price);
        radioButton3.setOnClickListener(this);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.tab_wode);
        radioButton4.setOnClickListener(this);
        View view = findViewById(R.id.placeholder_view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_news:
                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_cheyou:
                Toast.makeText(this, "选车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_price:
                Toast.makeText(this, "小视频", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_wode:
                Toast.makeText(this, "爱车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.placeholder_view:
                Toast.makeText(this, "发布小视频", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
