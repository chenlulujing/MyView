package com.san.os.myview.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.san.os.myview.R;
import com.san.os.myview.model.RadioItem;
import com.san.os.myview.tool.ToolBox;
import com.san.os.myview.ui.view.RadioButtonView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2016-09-06 11:05
 */
public class RadioButtonActivity extends FragmentActivity implements RadioButtonView.OnCheckedChangeListener {

    private RadioButtonView mRa;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);
        mTextView = (TextView) findViewById(R.id.span_text_sample);
        mRa = (RadioButtonView) findViewById(R.id.radiobutton);
        mRa.setOnCheckedChangeListener(this);

        SpannableString spanText = new SpannableString("16.66万");
        spanText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.append(spanText);


       final List<RadioItem> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RadioItem item = new RadioItem();
            item.color = 0x2f67b3;
            item.content = i + "驼色驼色驼色驼色";
//            item.colorString = "#2f67b3";
            list.add(item);
        }

        mRa.setData(list,false,mRa.getWidth());

    }


    @Override
    public void onCheckedChanged(RadioItem item) {
        if (item != null) {
            Toast.makeText(RadioButtonActivity.this, item.content, Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
