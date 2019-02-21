package com.san.os.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.san.os.myview.model.FilterItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-19 18:54
 */

public class FilterView extends LinearLayout {

    private TagClickListener mTagClickListener;
    FilterItemView mChanelFilterView, mWordSizeFilterView, mStatusFilterView;


    private HashMap<String, List<FilterItemModel>> mChannelData = new HashMap<>();

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

    String[] chanelTags_des = {"出版", "男生", "女生"};
    String[] chanelTags_ids = {"1000", "1001", "1002"};
    String[] wordSizeTags_desc = {"0~50万字", "50~100万字", "100~200万字", "200万字以上"};
    String[] wordSizeTags_ids = {"0-50", "50-100", "100-200", "200-0"};
    String[] serializeTags_des = {"完结", "连载"};
    String[] serializeTags_ids = {"IS_FINISHED", "IS_SERIALIZED"};
    String[] chanelSecondTags1_des = {"影视原著", "小说", "文学", "青春", "经管", "人文社科", "生活", "科技", "成功励志"};
    String[] chanelSecondTags2_des = {"都市", "奇幻", "玄幻", "武侠", "仙侠", "科幻", "灵异", "历史", "军事", "游戏", "竞技", "其他"};
    String[] chanelSecondTags2_ids = {"10000133", "10000633", "10001133", "10002233", "10002633", "10003133", "10003933", "10004433", "10004933", "10006033", "10006533", "10007033"};
    String[] chanelSecondTags1_ids = {"10500133", "10500233", "10500333", "10500433", "10500533", "10501233", "10500633", "10500833", "10503233"};
    String[] chanelSecondTags3_des = {"玄幻言情", "现代言情", "浪漫青春", "古代言情", "仙侠奇缘", "悬疑灵异", "科幻游戏", "其他"};
    String[] chanelSecondTags3_ids = {"10007233", "10008133", "10009233", "10009733", "10010433", "10010933", "10011533", "10012833"};


    private void init(Context context) {
        setOrientation(VERTICAL);


    }

    public void initdata(Context context) {
        mTagClickListener = new TagClickListener(observer);
        initChannelData();

        //频道
        mChanelFilterView = new FilterItemView(context, true);
        List<FilterItemModel> tags1 = new ArrayList<>();
        for (int i = 0, size = chanelTags_des.length; i < size; i++) {
            tags1.add(new FilterItemModel("频道", FilterItemModel.CLASS_CHANEL_PRIMARY, chanelTags_des[i], chanelTags_ids[i]));
        }
        mChanelFilterView.setData("频道", tags1, 3, mTagClickListener);
        addItem(mChanelFilterView);


        //字数
        mWordSizeFilterView = new FilterItemView(context);
        List<FilterItemModel> tags2 = new ArrayList<>();
        for (int i = 0, size = wordSizeTags_desc.length; i < size; i++) {
            tags2.add(new FilterItemModel("字数", FilterItemModel.CLASS_WORDSIZE, wordSizeTags_desc[i], wordSizeTags_ids[i]));
        }
        mWordSizeFilterView.setData("字数", tags2, 2, mTagClickListener);
        addItem(mWordSizeFilterView);

        //状态
        mStatusFilterView = new FilterItemView(context);
        List<FilterItemModel> tags3 = new ArrayList<>();
        for (int i = 0, size = serializeTags_des.length; i < size; i++) {
            tags3.add(new FilterItemModel("状态", FilterItemModel.CLASS_STATUS, serializeTags_des[i], serializeTags_ids[i]));
        }
        mStatusFilterView.setData("状态", tags3, 3, mTagClickListener);
        addItem(mStatusFilterView);
    }

    private void initChannelData() {
        //出版分类
        List<FilterItemModel> tags1Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags1_des.length; i < size; i++) {
            tags1Second.add(new FilterItemModel("出版", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags1_des[i], chanelSecondTags1_ids[i]));
        }
        mChannelData.put("出版", tags1Second);


        List<FilterItemModel> tags2Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags2_des.length; i < size; i++) {
            tags2Second.add(new FilterItemModel("男生", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags2_des[i], chanelSecondTags2_ids[i]));
        }
        mChannelData.put("男生", tags2Second);


        List<FilterItemModel> tags3Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags3_des.length; i < size; i++) {
            tags3Second.add(new FilterItemModel("女生", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags3_des[i], chanelSecondTags3_ids[i]));
        }
        mChannelData.put("女生", tags3Second);
    }

    private void showChanelSecond(String secondDesc) {
        mChanelFilterView.setSecondTags(secondDesc + "分类", mChannelData.get(secondDesc), 3, mTagClickListener);
    }

    private void hideChanelSecond() {
        mChanelFilterView.hideChanelSecond();
    }

    public void addItem(FilterItemView itemView, int index) {
        addView(itemView, index);
    }

    public void addItem(FilterItemView itemView) {
        addView(itemView);
    }

    public void clearStatus() {
        mChanelFilterView.clearStatus();
        mWordSizeFilterView.clearStatus();
        mStatusFilterView.clearStatus();
    }


    public static class TagClickListener implements View.OnClickListener {

        private Consumer mObserver;

        public TagClickListener(Consumer consumer) {
            mObserver = consumer;
        }

        @Override
        public void onClick(View v) {
            v.setSelected(!v.isSelected());
            if (v.getTag() != null && v.getTag() instanceof FilterItemModel) {
                ((FilterItemModel) v.getTag()).mIsSelected = v.isSelected();
                Observable.just(v.getTag()).subscribe(mObserver);
            }
        }
    }

    private void request(){

        if(!TextUtils.isEmpty(mChanelFilterView.getTagSecondId())){

        }else if(!TextUtils.isEmpty(mChanelFilterView.getTagId())){

        }else {

        }

        Log.i("ll_request","");
    }
    private Consumer observer;
    public void setObserver(Consumer observer){
        this.observer = observer;

    }

    public void chanelFilterClick(FilterItemModel item){
        if (item.mIsSelected) {
            mChanelFilterView.setTagId(item.tagId);
            showChanelSecond(item.desc);
            mChanelFilterView.clearOtherSelectedStatus();
            mChanelFilterView.clearTagSecondId();
        } else {
            mChanelFilterView.clearTagId();
            hideChanelSecond();
        }
    }
    public void chanelSecondFilterClick(FilterItemModel item){
        if(item.mIsSelected){
            mChanelFilterView.setTagSecondId(item.tagId);
            mChanelFilterView.clearOtherSelectedStatus();
        }else {
            mChanelFilterView.clearTagSecondId();
        }
    }
    public void wordSizeFilterClick(FilterItemModel item){
        if(item.mIsSelected){
            mWordSizeFilterView.setTagId(item.tagId);
            mWordSizeFilterView.clearOtherSelectedStatus();
        }else {
            mWordSizeFilterView.clearTagId();
        }
    }
    public void statusFilterClick(FilterItemModel item){
        if(item.mIsSelected){
            mStatusFilterView.setTagId(item.tagId);
            mStatusFilterView.clearOtherSelectedStatus();
        }else {
            mWordSizeFilterView.clearTagId();
        }
    }


}
