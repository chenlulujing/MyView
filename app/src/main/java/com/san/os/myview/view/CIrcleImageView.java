package com.san.os.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CIrcleImageView extends View {

    public CIrcleImageView(Context context) {
        super(context);
    }

    public CIrcleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CIrcleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int verticalCenter = getHeight() / 2;
        int horizontalCenter = getWidth() / 2;
        int circleRadius = 20;
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.RED);
        canvas.drawCircle(horizontalCenter, verticalCenter - 250, circleRadius, paint);
    }
}
