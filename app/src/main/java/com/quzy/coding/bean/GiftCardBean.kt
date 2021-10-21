package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 17:07
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
data class GiftCardBean(var available: Int, var balance: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(available)
        writeInt(balance)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GiftCardBean> = object : Parcelable.Creator<GiftCardBean> {
            override fun createFromParcel(source: Parcel): GiftCardBean = GiftCardBean(source)
            override fun newArray(size: Int): Array<GiftCardBean?> = arrayOfNulls(size)
        }
    }
}
