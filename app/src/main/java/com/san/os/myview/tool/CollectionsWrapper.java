package com.san.os.myview.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 *
 * @author chendy
 */
public class CollectionsWrapper {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 默认取20
     */
    public static boolean hasNextPage(List<?> list) {
        return hasNextPage(list, 20);
    }

    public static boolean hasNextPage(List<?> list, int prePageSize) {
        return !isEmpty(list) && (list.size() == prePageSize);
    }

    public static <T> ArrayList<T> parseArrayList(List<T> list) {
        if(list==null){
            return null;
        }
        return parseArrayList(list, 0, list.size());
    }

    /**
     * 将其他List转换为ArrayList
     * @param start include
     * @param end exclude
     */
    public static <T> ArrayList<T> parseArrayList(List<T> list, int start, int end) {
        if (list == null) {
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = start, size = list.size(); i < end && i < size; i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }
}
