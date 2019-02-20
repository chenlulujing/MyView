package com.san.os.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-19 18:54
 */

public class FilterView extends LinearLayout {

    private TagClickListener mTagClickListener;

    public FilterView(Context context) {
        super(context);
        init(context);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    String[] chanelTags = {"出版","男生","女生"};
    String[] wordSizeTags = {"0~50万字","50~100万字","100~200万字","200万字以上"};
    String[] serializeTags = {"完结","连载"};
    String[] chanelSecondTags = {"影视原著","小说","文学","青春","经管","人文社科","生活","科技","成功励志"};
    private void init(Context context) {
        setOrientation(VERTICAL);
        mTagClickListener = new TagClickListener();

        //频道
        FilterItemView chanelFilterView = new FilterItemView(context,true);
        List<String> tags1  = new ArrayList<>();
        for(String tag:chanelTags){
            tags1.add(tag);
        }
        List<String> tags1Second  = new ArrayList<>();
        for(String tag:chanelSecondTags){
            tags1Second.add(tag);
        }
        chanelFilterView.setData("频道", tags1,3,mTagClickListener);
        chanelFilterView.setSecondTags("出版分类",tags1Second,3,mTagClickListener);
        addItem(chanelFilterView);


        //字数
        FilterItemView wordSizeFilterView = new FilterItemView(context);
        List<String> tags2  = new ArrayList<>();
        for(String tag:wordSizeTags){
            tags2.add(tag);
        }
        wordSizeFilterView.setData("字数", tags2,2,mTagClickListener);
        addItem(wordSizeFilterView);

        //状态
        FilterItemView serializeFilterView = new FilterItemView(context);
        List<String> tags3  = new ArrayList<>();
        for(String tag:serializeTags){
            tags3.add(tag);
        }
        serializeFilterView.setData("状态", tags3,3,mTagClickListener);
        addItem(serializeFilterView);
    }

    public void addItem(FilterItemView itemView, int index) {
        addView(itemView, index);
    }

    public void addItem(FilterItemView itemView) {
        addView(itemView);
    }

    public static class TagClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"click",Toast.LENGTH_LONG).show();
        }
    }
}
