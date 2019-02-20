package com.san.os.myview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.model.FilterItemModel;
import com.san.os.myview.tool.ToolBox;
import com.san.os.myview.ui.view.FlowGroupLayout;

import java.util.List;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-19 17:43
 */

public class FilterItemView extends LinearLayout {

    private static final int MARGINLEFTRIGHT = 12;
    private static final int ITEM_MARGIN_LR = 10;
    private static final int ITEM_MARGIN_TB = 10;
    private static final int ITEM_PADDING_TB = 10;

    private TextView mTitleView;
    private FlowGroupLayout mTagsView;

    private TextView mSecondTitleView;
    private FlowGroupLayout mSecondTagsView;

    public FilterItemView(Context context) {
        super(context);
        init(context, false);
    }

    public FilterItemView(Context context, boolean withSecondTags) {
        super(context);
        init(context, withSecondTags);
    }

    public FilterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, false);
    }

    public FilterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, false);
    }


    private void init(Context context, boolean withSecondTags) {
        setOrientation(VERTICAL);

        //一级标题
        mTitleView = new TextView(context);
        LinearLayout.LayoutParams llpTitleView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llpTitleView.topMargin = ToolBox.dip2px(25);
        llpTitleView.leftMargin = ToolBox.dip2px(MARGINLEFTRIGHT);
        addView(mTitleView, llpTitleView);

        //一级tags
        mTagsView = new FlowGroupLayout(context);
        LinearLayout.LayoutParams llpTagsView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llpTagsView.topMargin = ToolBox.dip2px(2);
        llpTagsView.leftMargin = ToolBox.dip2px(MARGINLEFTRIGHT);
        addView(mTagsView, llpTagsView);

        if (withSecondTags) {
            //二级标题
            mSecondTitleView = new TextView(context);
            mSecondTitleView.setVisibility(GONE);
            LinearLayout.LayoutParams llpTitleSecondView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            llpTitleSecondView.topMargin = ToolBox.dip2px(12);
            llpTitleSecondView.leftMargin = ToolBox.dip2px(MARGINLEFTRIGHT);
            addView(mSecondTitleView, llpTitleSecondView);

            //二级tags
            mSecondTagsView = new FlowGroupLayout(context);
            mSecondTagsView.setVisibility(GONE);
            LinearLayout.LayoutParams llpTagsSecondView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            llpTagsSecondView.topMargin = ToolBox.dip2px(2);
            llpTagsSecondView.leftMargin = ToolBox.dip2px(MARGINLEFTRIGHT);
            addView(mSecondTagsView, llpTagsSecondView);
        }
    }


//    public void setData(String title, List<String> tags, int size, FilterView.TagClickListener listener) {
//        mTitleView.setText(title);
//        int tagWidth = (int) ((ToolBox.dip2px(280) - ToolBox.dip2px(MARGINLEFTRIGHT * 2) - ToolBox.dip2px(ITEM_MARGIN_LR)) / ((float) size));
//        if (mTagsView.getChildCount() != 0) {
//            mTagsView.removeAllViews();
//        }
//        for (String tagStr : tags) {
//            TextView tagView = new TextView(getContext());
//            tagView.setTextColor(ToolBox.getResources().getColorStateList(R.color.text_selector));
//            tagView.setTag(tagStr);
//            tagView.setPadding(0, ToolBox.dip2px(ITEM_PADDING_TB), 0, ToolBox.dip2px(ITEM_PADDING_TB));
//            tagView.setGravity(Gravity.CENTER);
//            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(tagWidth, LayoutParams.WRAP_CONTENT);
//            llp.topMargin = ITEM_MARGIN_TB;
//            tagView.setText(tagStr);
//            tagView.setOnClickListener(listener);
//            mTagsView.addView(tagView, llp);
//        }
//    }

    public void setData(String title, List<FilterItemModel> tagsModels, int size, FilterView.TagClickListener listener) {
        mTitleView.setText(title);
        int tagWidth = (int) ((ToolBox.dip2px(280) - ToolBox.dip2px(MARGINLEFTRIGHT * 2) - ToolBox.dip2px(ITEM_MARGIN_LR)) / ((float) size));
        if (mTagsView.getChildCount() != 0) {
            mTagsView.removeAllViews();
        }
        for (FilterItemModel item : tagsModels) {
            TextView tagView = new TextView(getContext());
            tagView.setTextColor(ToolBox.getResources().getColorStateList(R.color.text_selector));
            tagView.setTag(item);
            tagView.setPadding(0, ToolBox.dip2px(ITEM_PADDING_TB), 0, ToolBox.dip2px(ITEM_PADDING_TB));
            tagView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(tagWidth, LayoutParams.WRAP_CONTENT);
            llp.topMargin = ITEM_MARGIN_TB;
            tagView.setText(item.desc);
            tagView.setOnClickListener(listener);
            mTagsView.addView(tagView, llp);
        }
    }

//    public void setSecondTags(String secondTitle, List<String> secondTags, int size, FilterView.TagClickListener listener) {
//        if (mSecondTagsView != null && mSecondTagsView != null) {
//            mSecondTitleView.setText(secondTitle);
//            int tagWidth = (int) ((ToolBox.dip2px(280) - MARGINLEFTRIGHT * 2 - ITEM_MARGIN_LR) / ((float) size));
//            if (mSecondTagsView.getChildCount() != 0) {
//                mSecondTagsView.removeAllViews();
//            }
//            for (String tagStr : secondTags) {
//                TextView tagView = new TextView(getContext());
//                tagView.setTextColor(ToolBox.getResources().getColorStateList(R.color.text_selector));
//                tagView.setTag(tagStr);
//                tagView.setPadding(0, ToolBox.dip2px(ITEM_PADDING_TB), 0, ToolBox.dip2px(ITEM_PADDING_TB));
//                tagView.setGravity(Gravity.CENTER);
//                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(tagWidth, LayoutParams.WRAP_CONTENT);
//                llp.topMargin = ITEM_MARGIN_TB;
//                tagView.setText(tagStr);
//                tagView.setOnClickListener(listener);
//                mSecondTagsView.addView(tagView, llp);
//            }
//        }
//    }

    public void setSecondTags(String secondTitle, List<FilterItemModel> secondTags, int size, FilterView.TagClickListener listener) {
        if (mSecondTagsView != null && mSecondTagsView != null&&secondTags!=null) {
            if(mSecondTitleView.getTag()!=null&&mSecondTitleView.getTag() instanceof String&& TextUtils.equals((String)mSecondTitleView.getTag(),secondTitle)){
                return;
            }
            mSecondTitleView.setText(secondTitle);
            mSecondTitleView.setTag(secondTitle);
            int tagWidth = (int) ((ToolBox.dip2px(280) - MARGINLEFTRIGHT * 2 - ITEM_MARGIN_LR) / ((float) size));
            if (mSecondTagsView.getChildCount() != 0) {
                mSecondTagsView.removeAllViews();
            }
            for (FilterItemModel item : secondTags) {
                TextView tagView = new TextView(getContext());
                tagView.setTextColor(ToolBox.getResources().getColorStateList(R.color.text_selector));
                tagView.setTag(item);
                tagView.setPadding(0, ToolBox.dip2px(ITEM_PADDING_TB), 0, ToolBox.dip2px(ITEM_PADDING_TB));
                tagView.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(tagWidth, LayoutParams.WRAP_CONTENT);
                llp.topMargin = ITEM_MARGIN_TB;
                tagView.setText(item.desc);
                tagView.setOnClickListener(listener);
                mSecondTagsView.addView(tagView, llp);
            }

            mSecondTitleView.setVisibility(VISIBLE);
            mSecondTagsView.setVisibility(VISIBLE);
        }
    }

    public void hideChanelSecond(){
        mSecondTitleView.setVisibility(GONE);
        mSecondTagsView.setVisibility(GONE);
    }

}
