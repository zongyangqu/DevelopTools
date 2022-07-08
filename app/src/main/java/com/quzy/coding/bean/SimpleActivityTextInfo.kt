package com.quzy.coding.bean

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:10
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Keep
@Parcelize
data class SimpleActivityTextInfo(
        val activitytext: String? = null, // 活动文案
        val signs: ArrayList<ActivityTextSign>? = null, // 活动文案的字体标记
        val cornermarker: String? = null // 角标
) : Parcelable
