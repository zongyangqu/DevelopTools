package com.coding.qzy.baselibrary.widget.smoothtransindicator

import android.graphics.Color
import com.coding.qzy.baselibrary.widget.smoothtransindicator.GradientColorUtil

/**
 * CreateDate:2023/4/14 16:55
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget.smoothtransindicator
 * @Description:
 */
object GradientColorUtil {
    /**
     * 计算从startColor过度到endColor过程中百分比为franch时的颜色值
     * @param startColor 起始颜色 int类型
     * @param endColor 结束颜色 int类型
     * @param franch franch 百分比0.5
     * @return 返回int格式的color
     */
    fun caculateColor(startColor: Int, endColor: Int, franch: Float): Int {
        val strStartColor = "#" + Integer.toHexString(startColor)
        val strEndColor = "#" + Integer.toHexString(endColor)
        return Color.parseColor(caculateColor(strStartColor, strEndColor, franch))
    }

    fun caculateColor(startColor: String, endColor: String, franch: Float): String {
        val startAlpha = startColor.substring(1, 3).toInt(16)
        val startRed = startColor.substring(3, 5).toInt(16)
        val startGreen = startColor.substring(5, 7).toInt(16)
        val startBlue = startColor.substring(7).toInt(16)
        val endAlpha = endColor.substring(1, 3).toInt(16)
        val endRed = endColor.substring(3, 5).toInt(16)
        val endGreen = endColor.substring(5, 7).toInt(16)
        val endBlue = endColor.substring(7).toInt(16)
        val currentAlpha = ((endAlpha - startAlpha) * franch + startAlpha).toInt()
        val currentRed = ((endRed - startRed) * franch + startRed).toInt()
        val currentGreen = ((endGreen - startGreen) * franch + startGreen).toInt()
        val currentBlue = ((endBlue - startBlue) * franch + startBlue).toInt()
        return ("#" + getHexString(currentAlpha) + getHexString(currentRed)
                + getHexString(currentGreen) + getHexString(currentBlue))
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    fun getHexString(value: Int): String {
        var hexString = Integer.toHexString(value)
        if (hexString.length == 1) {
            hexString = "0$hexString"
        }
        return hexString
    }
}