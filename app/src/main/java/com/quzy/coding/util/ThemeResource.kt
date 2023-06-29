package com.quzy.coding.util

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.coding.qzy.baselibrary.utils.extend.dp
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication

/**
 * CreateDate:2023/6/16 17:48
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
class ThemeResource {

    private val context by lazy {
        BaseApplication.getContext()
    }

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = ThemeResource()
    }

    /**
     * @method createBgThemeGradientBtn
     * @description 主渐变按钮
     * @date: 2020/6/2 1:38 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    fun createBgThemeGradientBtn(): Drawable {
        val colors = intArrayOf(SkinUtils.getColor(context, R.color.gradientBtnFrom), SkinUtils.getColor(context, R.color.gradientBtnTo))
        val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
        val cornerNum = 100f.dp
        drawable.cornerRadius = cornerNum
        return drawable
    }
}