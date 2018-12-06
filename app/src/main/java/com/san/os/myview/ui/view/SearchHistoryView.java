package com.san.os.myview.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2018-10-10 15:56
 */

public class SearchHistoryView extends RelativeLayout {

    private static final int MARGIN_LEFT = 20;

    public List<String> mData = new ArrayList<>();
    private FlowGroupLayout mListView;


    public SearchHistoryView(Context context) {
        super(context);
        initView(context);
    }

    public SearchHistoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchHistoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //历史搜索
        TextView desc = new TextView(context);
        desc.setId(R.id.search_des);
        desc.setText("历史搜索");
        addView(desc);

        //删除
        ImageView deleteImg = new ImageView(context);
        deleteImg.setBackgroundDrawable(ToolBox.getResources().getDrawable(R.drawable.ic_search_clear));
        RelativeLayout.LayoutParams rlpDeleteImg = new RelativeLayout.LayoutParams(50, 50);
        rlpDeleteImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(deleteImg, rlpDeleteImg);
        deleteImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.removeAllViews();
            }
        });

        //内容
        mListView = new FlowGroupLayout(context);
        RelativeLayout.LayoutParams rlpListView = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rlpListView.addRule(RelativeLayout.BELOW, R.id.search_des);
        addView(mListView, rlpListView);
    }

    public void setData(List<String> list) {
        if (list == null || list.size() == 0) {
            mData.clear();
            mListView.removeAllViews();
            return;
        }
        mListView.removeAllViews();
        for (String itemStr : list) {
            MaxWidthTextView itemView = new MaxWidthTextView(getContext(), ToolBox.getDisplayWith((Activity) getContext()));
            itemView.setText(itemStr);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.leftMargin = MARGIN_LEFT;
            mListView.addView(itemView, llp);
        }
    }

}
