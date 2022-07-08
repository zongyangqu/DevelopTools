package com.quzy.coding.bean

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:11
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Keep
@Parcelize
data class ActivityTextSign(
        val start: Int,
        val end: Int,
        val color: String?,
        val size: Int,
        val weight: String?
) : Parcelable {

    companion object {
        const val WEIGHT_BOLD = "bold"
        const val WEIGHT_NORMAL = "normal"
    }
}
