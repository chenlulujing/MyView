package com.san.os.myview.model;

/**
 * @author chenlulu@qiyi.com
 * @Description
 * @date 2019-02-19 17:32
 */

public class SearchFilterBuilder {

    public int orider;
    public String category_id;
    public int serialize_status;
    public long wordCount;

    @Override
    public String toString() {
        return "&orider="+orider+"&category_id="+category_id+"&serialize_status="+serialize_status+"&wordCount="+wordCount;
    }
}
