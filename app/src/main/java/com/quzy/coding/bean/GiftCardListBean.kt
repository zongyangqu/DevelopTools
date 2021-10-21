package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:06
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
data class GiftCardListBean(var action: Int, var title: String, var desc: String, var image: String, var link: String, var bubbledesc: String?) :  Parcelable