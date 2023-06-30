package com.quzy.coding.util.extend

/**
 * CreateDate:2023-06-30 15:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.extend
 * @Description:
 */
fun String.toIntCatch(): Int {
    var toInt = -1
    try {
        toInt = this.toInt()
    } catch (e: Exception) {
    }
    return toInt
}

fun String?.disNull(): String {
    if (this == null) {
        return ""
    }
    return this
}