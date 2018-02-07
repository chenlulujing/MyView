package com.san.os.myview.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.model.RadioItem;
import com.san.os.myview.tool.ToolBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luluc@yiche.com
 * @Description 单选自定义控件
 * @date 2016-09-06 11:06
 */
public class RadioButtonView extends LinearLayout implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final int DEFALT_ROW_COUNT = 3;
    private static final float DEFALT_PARENT_LEFT_PADDING = ToolBox.dip2px(10);
    private static final float DEFALT_PARENT_RIGHT_PADDING = ToolBox.dip2px(10);
    private static final float DEFALT_CHILD_MARGIN = ToolBox.dip2px(5);
    private static final float DEFALT_Ver_MARGIN = ToolBox.dip2px(5);
    private static final float DEFALT_RADIOBUTTON_HEIGHT = ToolBox.dip2px(50);
    private static final float DEFALT_DRAWABLE_PADDING = ToolBox.dip2px(3);
    private static final int DEFALT_TEXT_SIZE = 15;
    private static final String DEFALT_TITLE = "标题";


    private Context mContext;
    private TextView mTitle;
    private TextView mMore;
    private FlowRadioGroup mRadioGroup;
    private float mParentLeftPadding = DEFALT_PARENT_LEFT_PADDING;
    private float mParentRightPadding = DEFALT_PARENT_RIGHT_PADDING;
    private float mChildMargin = DEFALT_CHILD_MARGIN;
    private float mVerMargin = DEFALT_Ver_MARGIN;
    private float mRadioHeight = DEFALT_RADIOBUTTON_HEIGHT;
    private float mDrawablePadding = DEFALT_DRAWABLE_PADDING;
    private int mRowCount = DEFALT_ROW_COUNT;
    private float mTextSize;
    private float mScreenWidth;
    private String mTitleText = DEFALT_TITLE;


    private List<RadioItem> mData = new ArrayList<RadioItem>();

    public RadioButtonView(Context context) {
        super(context);
        init(context);
    }

    public RadioButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadioButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_radiobutton, this);
        mTitle = (TextView) findViewById(R.id.title);
        mMore = (TextView) findViewById(R.id.more);
        mRadioGroup = (FlowRadioGroup) findViewById(R.id.radiogroup);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RadioButtonView);


        mTitleText = a.getString(R.styleable.RadioButtonView_titletext);
        mRowCount = a.getInt(R.styleable.RadioButtonView_rowcount, DEFALT_ROW_COUNT);
        mChildMargin = a.getDimension(R.styleable.RadioButtonView_radiobuttonmargin, 0);
        mParentLeftPadding = a.getDimension(R.styleable.RadioButtonView_android_paddingLeft, 0);
        mParentRightPadding = a.getDimension(R.styleable.RadioButtonView_android_paddingRight, 0);
        mVerMargin = a.getDimension(R.styleable.RadioButtonView_marginver, 0);
        mRadioHeight = a.getDimension(R.styleable.RadioButtonView_radiobuttonheight, DEFALT_RADIOBUTTON_HEIGHT);
        mDrawablePadding = a.getDimension(R.styleable.RadioButtonView_drawablepadding, DEFALT_DRAWABLE_PADDING);
        mTextSize = a.getDimensionPixelSize(R.styleable.RadioButtonView_textsize, ToolBox.sp2px(context, DEFALT_TEXT_SIZE));
        a.recycle();

        mTitle.setText(TextUtils.isEmpty(mTitleText) ? context.getResources().getString(R.string.biaoti) : mTitleText);
        initRadioGroup();

        mMore.setOnClickListener(this);


        getButtonWidth();
    }

    private void getButtonWidth() {
        ViewTreeObserver vto2 = getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mScreenWidth = getWidth();
                initMore();
                initRadioGroup();
            }
        });

    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_radiobutton, this);
        mRadioGroup = (FlowRadioGroup) findViewById(R.id.radiogroup);

        mTitle = (TextView) findViewById(R.id.title);
        mMore = (TextView) findViewById(R.id.more);
        initRadioGroup();

        mMore.setOnClickListener(this);
    }

    public void setData(List<RadioItem> list, boolean isShow,int width) {
        mData = list;
        mMoreVisible = isShow;
    }




    private void initRadioGroup() {
        if (mData == null) {
            return;
        }
        if (mData.size() == 0) {
            return;
        }
        mScreenWidth = getWidth() - (mRowCount - 1) * mChildMargin - (mParentLeftPadding + mParentRightPadding);
//        LinearLayout.LayoutParams lp = (LayoutParams) mRadioGroup.getLayoutParams();
        // TODO: 16/9/6
//        MarginLayoutParams lpMargin = (MarginLayoutParams) mRadioGroup.getLayoutParams();
        for (int i = 0, size = mMoreVisible ? mData.size() : (mRowCount < mData.size() ? mRowCount : mData.size()); i < size; i++) {
            RadioItem item = mData.get(i);
            createRadioButtonItem(mScreenWidth, i, item);
        }
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void createRadioButtonItem(float screenWidth, int i, RadioItem item) {
        RadioGroup.LayoutParams lpRadioGroup = new RadioGroup.LayoutParams((int) (screenWidth / mRowCount), (int) mRadioHeight);

        RadioButton radioButton = new RadioButton(mContext);
        radioButton.setVisibility(i < mRowCount ? View.VISIBLE : (mMoreVisible ? View.VISIBLE : View.GONE));
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        radioButton.setText(item.content);
        radioButton.setSingleLine(true);
        radioButton.setEllipsize(TextUtils.TruncateAt.END);
        ColorStateList colorStateList=mContext.getResources().getColorStateList(R.color.show_condition_pop_btn_text_selector);
        radioButton.setTextColor(colorStateList);
        radioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
        radioButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.skin_drawable_select_car_selector));


        if(!TextUtils.isEmpty(item.colorString)){
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
            shapeDrawable.setIntrinsicWidth((int) mTextSize);
            shapeDrawable.setIntrinsicHeight((int) mTextSize);
            shapeDrawable.getPaint().setColor(Color.parseColor(item.colorString));

            Drawable drawable = shapeDrawable;
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            radioButton.setCompoundDrawables(drawable, null, null, null);
            radioButton.setCompoundDrawablePadding((int) mDrawablePadding);
        }


        lpRadioGroup.topMargin = (int) mVerMargin;
        if (i % mRowCount != 0) {
            lpRadioGroup.leftMargin = (int) mChildMargin;
        }
        radioButton.setPadding(10, 10, 10, 10);
        radioButton.setGravity(Gravity.CENTER);
        mRadioGroup.addView(radioButton, lpRadioGroup);
        if(i==0){
            mRadioGroup.check(mRadioGroup.getChildAt(i).getId());
        }
    }


    public void setTitleText(String str) {
        if (!TextUtils.isEmpty(str)) {
            mTitle.setText(str);
        }
    }


    private boolean mMoreVisible = false;

    public void setShow(boolean is) {
        updateShow(is);
    }

    private void updateShowDelete(boolean is) {
        if (mData.size() <= mRowCount) {
            return;
        }
        mMoreVisible = is;
        if (!mMoreVisible) {
            while (mRadioGroup.getChildCount() > mRowCount) {
                mRadioGroup.removeViewAt(mRadioGroup.getChildCount() - 1);
            }
        } else {
            for (int i = mRowCount, size = mData.size(); i < size; i++) {
                createRadioButtonItem(mScreenWidth, i, mData.get(i));
            }
        }
        mRadioGroup.setOnCheckedChangeListener(this);
        initMore();
    }

    private void updateShow(boolean is) {
        if (mData.size() <= mRowCount) {
            return;
        }
        mMoreVisible = is;
        if (mMoreVisible) {
            //展开
            if (mData.size() > mRadioGroup.getChildCount()) {
                //第一次补全
                for (int i = mRowCount, size = mData.size(); i < size; i++) {
                    createRadioButtonItem(mScreenWidth, i, mData.get(i));
                }
            } else {
                //非第一次展开
                for (int i = mRowCount, size = mData.size(); i < size; i++) {
                    mRadioGroup.getChildAt(i).setVisibility(View.VISIBLE);
                }
                invalidate();
            }
        } else {
            //收起
            for (int i = mRowCount, size = mData.size(); i < size; i++) {
                mRadioGroup.getChildAt(i).setVisibility(View.GONE);
            }
            invalidate();
        }


        initMore();
    }

    private void initMore() {
        mMore.setText(mMoreVisible ? mContext.getResources().getString(R.string.shou_qi) : mContext.getResources().getString(R.string.gengduo));
        Drawable arrowDrawable = mMoreVisible ? mContext.getResources().getDrawable(R.drawable.skin_drawable_arrow_top) : mContext.getResources().getDrawable(R.drawable.skin_drawable_arrow_bottom);
        arrowDrawable.setBounds(0, 0, arrowDrawable.getMinimumWidth(),
                arrowDrawable.getMinimumHeight());
        mMore.setCompoundDrawables(null, null, arrowDrawable, null);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.more) {
            updateShow(!mMoreVisible);
        }
    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(RadioItem item);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mData == null) {
            return;
        }
        RadioButton radbtn = (RadioButton) findViewById(checkedId);
        Log.i("lulu_", radbtn.getText().toString());
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(mData.get(checkedId - 1));
        }
    }
}
