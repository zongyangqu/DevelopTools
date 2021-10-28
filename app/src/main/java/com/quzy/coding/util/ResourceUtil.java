package com.quzy.coding.util;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseApplication;

/**
 * CreateDate:2021/10/28 10:14
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
public class ResourceUtil {

    public static int getColor(int colorResId) {
        return ContextCompat.getColor(BaseApplication.getContext(), colorResId);
    }

    public static int getDimensionPixelSize(int dimenRes) {
        return BaseApplication.getContext().getResources().getDimensionPixelSize(dimenRes);
    }

    public static String getString(int strResId) {
        return BaseApplication.getContext().getString(strResId);
    }

    public static String getString(int strResId, Object... objects) {
        return String.format(getString(strResId), objects);
    }

    /**
     * 兼容SVG
     *
     * @param drawableResId
     * @return
     */
    @Nullable
    public static Drawable getDrawable(int drawableResId) {
        if (drawableResId == 0) {
            return null;
        } else {
            return ContextCompat.getDrawable(BaseApplication.getContext(), drawableResId);
        }
    }

}
