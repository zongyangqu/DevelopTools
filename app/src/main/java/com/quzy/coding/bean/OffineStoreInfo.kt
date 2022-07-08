package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:07
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
data class OffineStoreInfo(
        var cityId: String?,
        var distance: String?,
        var tags: ArrayList<TagBean>?,
        var report: Boolean?,
        var sellerId: String?,
        var shopId: String?,
        var shopName: String?,
        var logoUrl: String?
) : Parcelable