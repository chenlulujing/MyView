package com.san.os.myview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class SearchEditText extends EditText {

    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Context mContext;

    public SearchEditText(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

        imgInable = mContext.getResources().getDrawable(
                R.drawable.close_coupon);

        imgInable.setBounds(0, 0, imgInable.getIntrinsicWidth(), imgInable.getIntrinsicHeight());

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                /** 接口回调 */
                if (mListener != null) {
                    mListener.onSearchTextChanger(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }

    // 设置删除图片
    private void setDrawable() {
        if (length() < 1)
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        else
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgInable != null && event.getAction() == MotionEvent.ACTION_UP) {

            boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                    && (event.getX() < ((getWidth() - getPaddingRight())));

            if (touchable) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    private onSearchTextChangeListener mListener;

    public void setOnSearchTextChangeListener(
            onSearchTextChangeListener mListener) {
        this.mListener = mListener;

    }

    public interface onSearchTextChangeListener {

        public void onSearchTextChanger(String text);
    }
}