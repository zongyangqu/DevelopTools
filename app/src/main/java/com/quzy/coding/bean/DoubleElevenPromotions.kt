package com.quzy.coding.bean

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:09
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
@Keep
data class DoubleElevenPromotions(
        val img: String,
        val jumpUrl: String,
        val key: String,
        val elementName :String
) : Parcelable