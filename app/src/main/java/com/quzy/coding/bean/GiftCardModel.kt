package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:05
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
data class GiftCardModel(
        var asset: GiftCardListModel,
        var banner: GiftCardListModel,
        var functionnew: GiftCardListModel,
        var promotion: GiftCardListModel
) :  Parcelable {

}