package com.san.os.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class DashLine extends View {

    private Context mContext;
    Paint p;

    public DashLine(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public DashLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public DashLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

//        p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setStyle(Paint.Style.STROKE);
//        p.setColor(getResources().getColor(R.color.red));
//        p.setStrokeWidth(1);
//        PathEffect effects = new DashPathEffect(new float[]{4, 4, 4, 1}, 1);
//        p.setPathEffect(effects);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getContext().getResources().getColor(R.color.red_dd2727));
        Path path = new Path();
        path.moveTo(0, 10);
        path.lineTo(480,10);
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);


        super.onDraw(canvas);
    }


}
