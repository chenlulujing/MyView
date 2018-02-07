package com.san.os.myview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class HandlerActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mTv;
    private Button mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mTv = (TextView) findViewById(R.id.tv);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Looper looper = Looper.getMainLooper(); //主线程的Looper对象
        //这里以主线程的Looper对象创建了handler，
        //所以，这个handler发送的Message会被传递给主线程的MessageQueue。
        MyHandler handler = new MyHandler(looper);
        handler.removeMessages(0);
        //构建Message对象
        //第一个参数：是自己指定的message代号，方便在handler选择性地接收
        //第二三个参数没有什么意义
        //第四个参数需要封装的对象
        Message msg = handler.obtainMessage(1, 1, 1, "主线程发消息了");
        handler.sendMessage(msg); //发送消息
    }

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTv.setText("我是主线程的Handler，收到了消息：" + (String) msg.obj);
        }
    }


}
