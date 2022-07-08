package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 17:05
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class VipHintBean() : MemberOrderItemModel(), Parcelable {
    var desc1: String? = null

    var desc2: String? = null

    var desc3: String? = null

    var desc4: String? = null

    var link: String? = null

    var iconUrl: String? = null

    var backImgUrl: String? = null

    var btn: String ? = ""

    constructor(source: Parcel) : this()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VipHintBean> = object : Parcelable.Creator<VipHintBean> {
            override fun createFromParcel(source: Parcel): VipHintBean = VipHintBean(source)
            override fun newArray(size: Int): Array<VipHintBean?> = arrayOfNulls(size)
        }
    }
}