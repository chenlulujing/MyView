package com.san.os.myview.model;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-20 11:32
 */

public class FilterItemModel {
    public static final int CLASS_CHANEL_PRIMARY = 1 << 0;
    public static final int CLASS_CHANEL_SECOEND = 1 << 1;
    public static final int CLASS_WORDSIZE = 1 << 2;
    public static final int CLASS_STATUS = 1 << 3;


    public String classification_des;
    public int classification_id;
    public String desc;
    public String tagId;
    public boolean mIsSelected;

    public FilterItemModel(String classification_des, int classification_id, String desc, String tagId) {
        this.classification_des = classification_des;
        this.classification_id = classification_id;
        this.desc = desc;
        this.tagId = tagId;
    }
}
