package com.san.os.myview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.san.os.myview.MyViewApplication;
import com.san.os.myview.tool.ToolBox;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class HeaderText extends TextView {


    public HeaderText(Context context) {
        super(context);
        init();
    }

    public HeaderText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private final int leftPadding = ToolBox.dip2px(15);
    private final int rectWidth = ToolBox.dip2px(5);
    private int rectHeight;
    Rect rect = new Rect(0, 20, 10, 0);

    private void init() {
        setPadding(leftPadding, 0, 0, 0);
        setGravity(Gravity.CENTER_VERTICAL);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawRect(rect, getPaint());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect.bottom = h - 20;
    }
}
