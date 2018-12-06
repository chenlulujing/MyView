package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;
import com.san.os.myview.ui.view.HeaderText;
import com.san.os.myview.ui.view.RecomendBigVView;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class MyTextActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HeaderText text = new HeaderText(this);
//        text.setText("热门评论");
//        text.setPadding(50,20,0,20);
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//        setContentView(text,lp);
//        TextView textView = new TextView(this);
//        textView.setText(" FrameLayout.LayoutParams(F FrameLayout.LayoutParams(F FrameLayout.LayoutParams(F");
//        textView.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_id_card),null,null,null);
//        textView.setMaxLines(1);
//        textView.setEllipsize(TextUtils.TruncateAt.END);
//        setContentView(textView);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(57, 0, 0, 0);
        linearLayout.addView(new RecomendBigVView(this, LinearLayout.VERTICAL, 375, 101.4f, 172));
        linearLayout.addView(new RecomendBigVView(this, LinearLayout.VERTICAL, 375, 101.4f, 172));
        linearLayout.addView(new RecomendBigVView(this, LinearLayout.VERTICAL, 375, 101.4f, 172));
        linearLayout.addView(new RecomendBigVView(this, LinearLayout.VERTICAL, 375, 101.4f, 172));
//        linearLayout.addView(new RecomendBigVView(this));
//        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        llp.leftMargin = 57;

        setContentView(linearLayout);
    }


}
