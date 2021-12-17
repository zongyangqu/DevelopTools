package com.coding.qzy.baselibrary.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * CreateDate:2021/12/17 15:59
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.utils
 * @Description:
 */
object MMKVUtils {
    var mv: MMKV? = null
    init {
        mv= MMKV.defaultMMKV()
    }
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    fun encode(key: String?, `object`: Any) {
        when (`object`) {
            is String -> {
                mv?.encode(key, `object`)
            }
            is Int -> {
                mv?.encode(key, `object`)
            }
            is Boolean -> {
                mv?.encode(key, `object`)
            }
            is Float -> {
                mv?.encode(key, `object`)
            }
            is Long -> {
                mv?.encode(key, `object`)
            }
            is Double -> {
                mv?.encode(key, `object`)
            }
            is ByteArray -> {
                mv?.encode(key, `object`)
            }
            else -> {
                mv?.encode(key, `object`.toString())
            }
        }
    }

    fun encodeSet(key: String?, sets: Set<String?>?) {
        mv?.encode(key, sets)
    }

    fun encodeParcelable(key: String?, obj: Parcelable?) {
        mv?.encode(key, obj)
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    fun decodeInt(key: String?): Int? {
        return mv?.decodeInt(key, 1)
    }

    fun decodeDouble(key: String?): Double {
        return mv?.decodeDouble(key, 0.00)!!
    }

    fun decodeLong(key: String?): Long {
        return mv?.decodeLong(key, 0L)!!
    }

    fun decodeBoolean(key: String?): Boolean {
        return mv?.decodeBool(key, false) == true
    }

    fun decodeBooleanTure(key: String?, defaultValue: Boolean): Boolean {
        return mv?.decodeBool(key, defaultValue) == true
    }

    fun decodeFloat(key: String?): Float {
        return mv?.decodeFloat(key, 0f)!!
    }

    fun decodeBytes(key: String?): ByteArray {
        return mv?.decodeBytes(key)!!
    }

    fun decodeString(key: String?): String {
        return mv?.decodeString(key, "").toString()
    }

    fun decodeStringDef(key: String?, defaultValue: String): String {
        return mv?.decodeString(key, defaultValue).toString()
    }


    fun decodeStringSet(key: String?): Set<String> {
        return mv?.decodeStringSet(key, emptySet()) as Set<String>
    }

    fun <T : Parcelable?> decodeParcelable(key: String?, tClass: Class<T>?): T? {
        return mv?.decodeParcelable(key, tClass)
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    fun removeKey(key: String?) {
        mv?.removeValueForKey(key)
    }

    /**
     * 清除所有key
     */
    fun clearAll() {
        mv?.clearAll()
    }
}