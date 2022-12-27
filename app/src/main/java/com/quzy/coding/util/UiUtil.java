package com.quzy.coding.util;

import android.content.Context;

/**
 * CreateDate:2022/11/28 17:26
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
public class UiUtil {

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
