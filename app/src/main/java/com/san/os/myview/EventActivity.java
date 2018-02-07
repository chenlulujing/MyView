package com.san.os.myview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class EventActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout mLayout;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);
        initView();
    }


    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void initView() {

        mLayout = (LinearLayout) this.findViewById(R.id.mylayout);
        mButton = (Button) this.findViewById(R.id.my_btn);

        mLayout.setOnTouchListener(this);
        mButton.setOnTouchListener(this);

        mLayout.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Log.i("evnet_dispath", "OnClickListener--onClick--" + v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("evnet_dispath", "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return super.onTouchEvent(event);
    }
}
