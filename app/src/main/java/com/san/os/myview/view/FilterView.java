package com.san.os.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    FilterItemView chanelFilterView, wordSizeFilterView, serializeFilterView;

    private HashMap<String,List<FilterItemModel>> mChannelData = new HashMap<>();

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
    String[] chanelSecondTags2_des = {"都市", "奇幻", "玄幻", "武侠", "仙侠", "科幻", "灵异", "历史", "军事","游戏","竞技","其他"};
    String[] chanelSecondTags2_ids = {"10000133", "10000633", "10001133", "10002233", "10002633", "10003133", "10003933", "10004433", "10004433","10006033","10006533","10007033"};
    String[] chanelSecondTags1_ids = {"10500133", "10500233", "10500333", "10500433", "10500533", "10501233", "10500633", "10500833", "10503233"};
    String[] chanelSecondTags3_des = {"玄幻言情", "现代言情", "浪漫青春", "古代言情", "仙侠奇缘", "悬疑灵异", "科幻游戏", "其他"};
    String[] chanelSecondTags3_ids = {"10007233", "10008133", "10009233", "10009733", "10010433", "10010933", "10011533", "10012833"};



    private void init(Context context) {
        setOrientation(VERTICAL);
        mTagClickListener = new TagClickListener(observer);

        initChannelData();

        //频道
        chanelFilterView = new FilterItemView(context, true);
        List<FilterItemModel> tags1 = new ArrayList<>();
        for (int i = 0, size = chanelTags_des.length; i < size; i++) {
            tags1.add(new FilterItemModel("频道", FilterItemModel.CLASS_CHANEL_PRIMARY, chanelTags_des[i], chanelTags_ids[i]));
        }
        chanelFilterView.setData("频道", tags1, 3, mTagClickListener);
        addItem(chanelFilterView);


        //字数
        wordSizeFilterView = new FilterItemView(context);
        List<FilterItemModel> tags2 = new ArrayList<>();
        for (int i = 0, size = wordSizeTags_desc.length; i < size; i++) {
            tags2.add(new FilterItemModel("字数", FilterItemModel.CLASS_WORDSIZE, wordSizeTags_desc[i], wordSizeTags_ids[i]));
        }
        wordSizeFilterView.setData("字数", tags2, 2, mTagClickListener);
        addItem(wordSizeFilterView);

        //状态
        serializeFilterView = new FilterItemView(context);
        List<FilterItemModel> tags3 = new ArrayList<>();
        for (int i = 0, size = serializeTags_des.length; i < size; i++) {
            tags3.add(new FilterItemModel("状态", FilterItemModel.CLASS_STATUS, serializeTags_des[i], serializeTags_ids[i]));
        }
        serializeFilterView.setData("状态", tags3, 3, mTagClickListener);
        addItem(serializeFilterView);
    }

    private void initChannelData() {
        //出版分类
        List<FilterItemModel>   tags1Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags1_des.length; i < size; i++) {
            tags1Second.add(new FilterItemModel("出版", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags1_des[i], chanelSecondTags1_ids[i]));
        }
        mChannelData.put("出版",tags1Second);


        List<FilterItemModel>   tags2Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags2_des.length; i < size; i++) {
            tags2Second.add(new FilterItemModel("男生", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags2_des[i], chanelSecondTags2_ids[i]));
        }
        mChannelData.put("男生",tags2Second);


        List<FilterItemModel>   tags3Second = new ArrayList<>();
        for (int i = 0, size = chanelSecondTags3_des.length; i < size; i++) {
            tags3Second.add(new FilterItemModel("女生", FilterItemModel.CLASS_CHANEL_SECOEND, chanelSecondTags3_des[i], chanelSecondTags3_ids[i]));
        }
        mChannelData.put("女生",tags3Second);
    }

    private void showChanelSecond(String secondDesc) {
        chanelFilterView.setSecondTags(secondDesc+"分类", mChannelData.get(secondDesc), 3, mTagClickListener);
    }

    private void hideChanelSecond() {
        chanelFilterView.hideChanelSecond();
    }

    public void addItem(FilterItemView itemView, int index) {
        addView(itemView, index);
    }

    public void addItem(FilterItemView itemView) {
        addView(itemView);
    }

    private Consumer<FilterItemModel> observer = new Consumer<FilterItemModel>() {
        @Override
        public void accept(FilterItemModel item) throws Exception {
            if (item.classification_id == FilterItemModel.CLASS_CHANEL_PRIMARY) {
                if (item.mIsSelected) {
                    showChanelSecond(item.desc);
                } else {
                    hideChanelSecond();
                }
            }else {

            }
        }
    };


    public static class TagClickListener implements View.OnClickListener {

        private Consumer mConsumer;

        public TagClickListener(Consumer consumer) {
            mConsumer = consumer;
        }

        @Override
        public void onClick(View v) {
            v.setSelected(!v.isSelected());
            if (v.getTag() != null && v.getTag() instanceof FilterItemModel) {
                ((FilterItemModel) v.getTag()).mIsSelected = v.isSelected();
                Observable.just(v.getTag()).subscribe(mConsumer);
            }

//            1. 频道item点击，通知刷新列表
//            2. 频道item点击，刷新频道二级分类
//            3. 频道二级分类item点击，刷新列表
//            4. 字数item点击，刷新列表
//            5. 状态item点击，刷新列表
//            6. 刷新反选状态
//            if(){
//                //频道一级
//                if(){
//                    //选中 对比
//                }else {
//                    //取消 隐藏频道二级分类
//                }
//            }else {
//                //频道二级  刷新列表
//
//            }
//            Toast.makeText(v.getContext(), ((FilterItemModel) v.getTag()).tagId, Toast.LENGTH_SHORT).show();
        }
    }
}
