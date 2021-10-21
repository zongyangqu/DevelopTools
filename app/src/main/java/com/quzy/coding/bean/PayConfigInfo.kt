package com.quzy.coding.bean

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:08
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
@Keep
data class PayConfigInfo(
        val icon: String, // 展示icon
        val copywriting: String, // 展示文案
        val cornerMarkText: String, // 展示文案
        val jumpLink: String, // 跳转链接
        val isMark: String? = null, // 是否打标
        val picHeight: Int, // 图片高度
        val picWidth: Int // 图片宽度
) : Parcelable {
    companion object {
        lateinit var CREATOR: Parcelable.Creator<PayConfigInfo>
    }
}