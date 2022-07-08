package com.coding.qzy.baselibrary.utils.guidelayer.util

import android.content.Context
import android.text.TextUtils
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * CreateDate:2021/10/25 11:05
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.utils.guidelayer.util
 * @Description:
 */
object UIUtils {
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    fun getWindowWidth(context: Context?): Int {
        return context?.resources?.displayMetrics?.widthPixels ?: 0
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun getWindowHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun dip2px(context: Context?, dpValue: Float): Int {
        val scale: Float = context?.resources?.displayMetrics?.density ?: 0f
        return (dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    @Throws(IOException::class, IllegalArgumentException::class)
    @JvmStatic
    fun readFileFromAssets(
        context: Context,
        fileName: String?
    ): String? {
        require(!(TextUtils.isEmpty(fileName))) { "bad arguments!" }
        val assetManager = context.assets
        val input = assetManager.open(fileName!!)
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length = 0
        while (input.read(buffer).also { length = it } != -1) {
            output.write(buffer, 0, length)
        }
        output.close()
        input.close()
        return output.toString()
    }
}
