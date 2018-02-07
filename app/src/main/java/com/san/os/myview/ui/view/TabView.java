package com.san.os.myview.ui.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-04-13 09:21
 */

public class TabView extends HorizontalScrollView implements RadioGroup.OnCheckedChangeListener {

    private static final int DURATION = 600;
    private List<String> mData;
    private RadioGroup mRadioGroup;
    private int ITEM_WIDTH ;

    public TabView(Context context, List<String> tabs) {
        super(context);
        mData = tabs;
        init(context);
    }

    public TabView(Context context) {
        super(context);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setData(List<String> data) {
        mData = data;
        if (mData != null && mData.size() > 0) {
            LinearLayout ll = new LinearLayout(getContext());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0, size = mData.size(); i < size; i++) {
                TextView tab = new TextView(getContext());
                tab.setTextSize(20);
                int padding = ToolBox.dip2px(3);
                tab.setPadding(padding, padding, padding, padding);
                LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lllp.leftMargin = ToolBox.dip2px(5);
                tab.setText(mData.get(i));
                ll.addView(tab);
            }
            for (int i = 0, size = mData.size(); i < size; i++) {

            }
            addView(ll);
        }
    }

    private void init(Context context) {
        setBackgroundColor(getResources().getColor(R.color.skin_color_cs_1));
        ITEM_WIDTH = (int)((ToolBox.getDisplayWith((Activity) getContext())-ToolBox.dip2px(50))/3);
        setPadding(0,0,ToolBox.dip2px(50),0);
        setHorizontalScrollBarEnabled(false);
        setData(mData);

    }

    public void setDataRadioButton(List<String> data) {
        mData = data;
        if (mData != null && mData.size() > 0) {
            mRadioGroup = new RadioGroup(getContext());
            mRadioGroup.setOnCheckedChangeListener(this);
            mRadioGroup.setOrientation(RadioGroup.HORIZONTAL);
            for (int i = 0, size = mData.size(); i < size; i++) {
                RadioButton    tab= (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.myradiobutton,null);
//                tab.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
//                tab.setCompoundDrawables(null,null,null,null);
//                tab.setButtonDrawable(null);
//                tab.setBackgroundDrawable(null);
//                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
//                    try {
//                        Field field = tab.getClass().getSuperclass().getDeclaredField("mButtonDrawable");
//                        field.setAccessible(true);
//                        field.set(tab, null);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                ColorStateList colorStateList = getContext().getResources().getColorStateList(R.color.show_condition_pop_btn_text_selector);
                tab.setTextColor(colorStateList);
                tab.setGravity(Gravity.CENTER);
                tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                RadioGroup.LayoutParams lllp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                lllp.gravity =Gravity.CENTER_HORIZONTAL;
                tab.setText(mData.get(i));
                mRadioGroup.addView(tab,lllp);
            }
            for (int i = 0, size = mData.size(); i < size; i++) {

            }
            addView(mRadioGroup);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, final int index) {
        if (mData == null) {
            return;
        }
        Toast.makeText(getContext(), (index - 1) + "", Toast.LENGTH_SHORT).show();
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(index - 1);
        }
        if ((index - 1) > 1) {
            int width = ToolBox.getDisplayWith((Activity) getContext());
            final int itemWidth = ToolBox.dip2px(getContext(), ToolBox.dip2px(20));
            final int center = (width - itemWidth) / 2;
            post(new Runnable() {

                @Override
                public void run() {
                    ObjectAnimator animator = ObjectAnimator.ofInt(TabView.this, "scrollX", (itemWidth * index - center));
                    animator.setDuration(DURATION);
                    animator.start();
                }
            });

        } else {
            ObjectAnimator animator = ObjectAnimator.ofInt(TabView.this, "scrollX", 0);
            animator.setDuration(DURATION);
            animator.start();
        }
    }

    public void setTabSeleted(int index) {
        try {
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(index);
            if (radioButton != null) {
                mRadioGroup.check(radioButton.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int index);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }
}
