package com.san.os.myview.model;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-19 17:32
 */

public class SearchFilterBuilder {

    public String order = "";
    public String category_id = "";
    public String serialize_status = "";
    public String wordCount = "";

    @Override
    public String toString() {
        return "&order=" + order + "&category_id=" + category_id + "&serialize_status=" + serialize_status + "&wordCount=" + wordCount;
    }

    public void clearStatus() {
        category_id = "";
        serialize_status = "";
        wordCount = "";
    }
}
