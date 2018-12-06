package com.san.os.myview.ui.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.ui.view.commen.ISanLinearLayout;

/**
 * @author luluc@yiche.com
 * @Description 头条列表里推荐群信息流itemView
 * @date 2018-03-01 19:07
 */

public class RecomendBigVView extends ISanLinearLayout {


    public RecomendBigVView(Context context,int oritaion, int screenWidthInDesign, float widthInDesign, float heightInDesign) {
        super(context, oritaion, screenWidthInDesign, widthInDesign, heightInDesign);
    }

    @Override
    protected void initView(Context context) {
        setGravity(Gravity.CENTER_HORIZONTAL);
        setBackgroundColor(getResources().getColor(R.color.red));

        setPadding(translate(5), translate(30), translate(5), translate(20));

        //头像
        ImageView img = new ImageView(getContext());
        LinearLayout.LayoutParams llpImg = getLayoutParams(50, 50);
        addView(img, llpImg);


        //groupName
        TextView groupNameView = new TextView(context);
        groupNameView.setText("嘉旅车主群");
        groupNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, translate(14));
        LinearLayout.LayoutParams llpGroupName = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        llpGroupName.topMargin = translate(10);
        addView(groupNameView, llpGroupName);


        //des
        TextView groupDesView = new TextView(context);
        groupDesView.setText("资深车主");
        groupDesView.setTextSize(TypedValue.COMPLEX_UNIT_PX, translate(12));
        LinearLayout.LayoutParams llpGroupDes = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        llpGroupDes.topMargin = translate(1);
        addView(groupDesView, llpGroupDes);

        //focus
        TextView focusView = new TextView(context);
        focusView.setText("关注TA");
        focusView.setTextSize(TypedValue.COMPLEX_UNIT_PX, translate(11));
        LinearLayout.LayoutParams llpFocus = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        llpFocus.topMargin = translate(10);
        addView(focusView, llpFocus);
    }


}
