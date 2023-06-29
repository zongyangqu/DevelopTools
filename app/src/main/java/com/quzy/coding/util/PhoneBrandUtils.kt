package com.quzy.coding.util

import android.annotation.SuppressLint
import android.os.Build
import android.text.TextUtils
import java.lang.Exception

/**
 * CreateDate:2023/6/16 16:49
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description: 判断手机的型号的工具类
 */
object PhoneBrandUtils {
    fun sysProperty(key: String, defValue: String): String? {
        var res: String? = null
        try {
            @SuppressLint("PrivateApi") val clazz = Class.forName("android.os.SystemProperties")
            val method = clazz.getMethod(
                "get",
                String::class.java,
                String::class.java
            )
            res = method.invoke(clazz, *arrayOf<Any>(key, defValue)) as String
        } catch (ignore: Exception) {
        }
        if (res == null) {
            res = ""
        }
        return res
    }

    fun isASUS(): Boolean {
        // 华硕手机
        return Build.MANUFACTURER.equals("ASUS", ignoreCase = true) ||
                Build.BRAND.equals("ASUS", ignoreCase = true)
    }

    fun isHuawei(): Boolean {
        // 华为手机、荣耀手机
        return Build.MANUFACTURER.equals("HUAWEI", ignoreCase = true) ||
                Build.BRAND.equals("HUAWEI", ignoreCase = true) ||
                Build.BRAND.equals("HONOR", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.build.version.emui", ""))
    }

    fun isZTE(): Boolean {
        // 中兴手机
        return Build.MANUFACTURER.equals("ZTE", ignoreCase = true) ||
                Build.BRAND.equals("ZTE", ignoreCase = true)
    }

    fun isXiaomi(): Boolean {
        // 小米手机、红米手机、美图手机
        return Build.MANUFACTURER.equals("XIAOMI", ignoreCase = true) ||
                Build.BRAND.equals("XIAOMI", ignoreCase = true) ||
                Build.BRAND.equals("REDMI", ignoreCase = true) ||
                Build.BRAND.equals("MEITU", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.miui.ui.version.name", ""))
    }

    fun isOppo(): Boolean {
        // 欧珀手机、真我手机
        return Build.MANUFACTURER.equals("OPPO", ignoreCase = true) ||
                Build.BRAND.equals("OPPO", ignoreCase = true) ||
                Build.BRAND.equals("REALME", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.build.version.opporom", ""))
    }

    fun isVivo(): Boolean {
        // 维沃手机、爱酷手机
        return Build.MANUFACTURER.equals("VIVO", ignoreCase = true) ||
                Build.BRAND.equals("VIVO", ignoreCase = true) ||
                Build.BRAND.equals("IQOO", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.vivo.os.version", ""))
    }

    fun isOnePlus(): Boolean {
        // 一加手机
        return Build.MANUFACTURER.equals("ONEPLUS", ignoreCase = true) ||
                Build.BRAND.equals("ONEPLUS", ignoreCase = true)
    }

    fun isBlackShark(): Boolean {
        // 黑鲨手机
        return Build.MANUFACTURER.equals("BLACKSHARK", ignoreCase = true) ||
                Build.BRAND.equals("BLACKSHARK", ignoreCase = true)
    }

    fun isSamsung(): Boolean {
        // 三星手机
        return Build.MANUFACTURER.equals("SAMSUNG", ignoreCase = true) ||
                Build.BRAND.equals("SAMSUNG", ignoreCase = true)
    }

    fun isMotolora(): Boolean {
        // 摩托罗拉手机
        return Build.MANUFACTURER.equals("MOTOLORA", ignoreCase = true) ||
                Build.BRAND.equals("MOTOLORA", ignoreCase = true)
    }

    fun isNubia(): Boolean {
        // 努比亚手机
        return Build.MANUFACTURER.equals("NUBIA", ignoreCase = true) ||
                Build.BRAND.equals("NUBIA", ignoreCase = true)
    }

    fun isMeizu(): Boolean {
        // 魅族手机
        return Build.MANUFACTURER.equals("MEIZU", ignoreCase = true) ||
                Build.BRAND.equals("MEIZU", ignoreCase = true) ||
                Build.DISPLAY.toUpperCase().contains("FLYME")
    }

    fun isLenovo(): Boolean {
        // 联想手机
        return Build.MANUFACTURER.equals("LENOVO", ignoreCase = true) ||
                Build.BRAND.equals("LENOVO", ignoreCase = true) ||
                Build.BRAND.equals("ZUK", ignoreCase = true)
    }

    fun isFreeme(): Boolean {
        // 卓易手机
        return if (Build.MANUFACTURER.equals("FREEMEOS", ignoreCase = true)) {
            true
        } else !TextUtils.isEmpty(sysProperty("ro.build.freeme.label", ""))
    }

    fun isSSUI(): Boolean {
        // 这是啥玩意的手机？百度及谷歌都搜不到相关资料
        return if (Build.MANUFACTURER.equals("SSUI", ignoreCase = true)) {
            true
        } else !TextUtils.isEmpty(sysProperty("ro.ssui.product", ""))
    }

    fun is360(): Boolean {
        // 360手机
        return Build.MANUFACTURER.equals("360", ignoreCase = true) ||
                Build.BRAND.equals("360", ignoreCase = true)
    }

    fun isCoolpad(): Boolean {
        // 酷派手机、奇酷手机
        return Build.MANUFACTURER.equals("YULONG", ignoreCase = true) ||
                Build.MANUFACTURER.equals("COOLPAD", ignoreCase = true) ||
                Build.BRAND.equals("COOLPAD", ignoreCase = true) ||
                Build.MANUFACTURER.equals("QIKU", ignoreCase = true) ||
                Build.BRAND.equals("QIKU", ignoreCase = true)
    }

    fun isSmartisan(): Boolean {
        // 锤子手机、坚果手机
        return Build.MANUFACTURER.equals("SMARTISAN", ignoreCase = true) ||
                Build.BRAND.equals("SMARTISAN", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.smartisan.version", ""))
    }

    fun isLetv(): Boolean {
        // 乐视手机
        return Build.MANUFACTURER.equals("LETV", ignoreCase = true) ||
                Build.BRAND.equals("LETV", ignoreCase = true) ||
                !TextUtils.isEmpty(sysProperty("ro.letv.release.version", ""))
    }

    fun isAmigo(): Boolean {
        // 金立手机
        return Build.MANUFACTURER.equals("AMIGO", ignoreCase = true) ||
                Build.BRAND.equals("AMIGO", ignoreCase = true) ||
                Build.DISPLAY.toUpperCase().contains("AMIGO") ||
                !TextUtils.isEmpty(sysProperty("ro.gn.gnromvernumber", ""))
    }

    fun isSony(): Boolean {
        // 索尼手机
        return Build.MANUFACTURER.equals("SONY", ignoreCase = true) ||
                Build.BRAND.equals("SONY", ignoreCase = true)
    }

    fun isLG(): Boolean {
        // LG手机
        return Build.MANUFACTURER.equals("LG", ignoreCase = true) ||
                Build.BRAND.equals("LG", ignoreCase = true)
    }
}
