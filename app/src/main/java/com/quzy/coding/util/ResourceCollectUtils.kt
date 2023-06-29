package com.quzy.coding.util

import android.content.res.Resources

/**
 * CreateDate:2023/6/16 17:45
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
object ResourceCollectUtils {
    private const val TYPE_COLOR = "color"
    private const val TYPE_DRAWABLE = "drawable"
    private const val TYPE_STRING = "string"

    @JvmStatic
    fun getColorFromThemePkg(colorName: String, resources: Resources, pkgName: String?): Int {
        return resources.getIdentifier(colorName, TYPE_COLOR, pkgName)
    }

    @JvmStatic
    fun getDrawableIdFromThemePkg(drawableName: String, resources: Resources, pkgName: String?): Int {
        return resources.getIdentifier(drawableName, TYPE_DRAWABLE, pkgName)
    }

    @JvmStatic
    fun getStringIdFromThemePkg(strName: String, resources: Resources, pkgName: String?): Int {
        return resources.getIdentifier(strName, TYPE_STRING, pkgName)
    }
}