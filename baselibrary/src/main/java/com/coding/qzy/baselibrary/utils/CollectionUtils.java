package com.coding.qzy.baselibrary.utils;

import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */


public class CollectionUtils {

    public static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
