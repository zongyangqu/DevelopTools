package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class PreSaleInfo() :Parcelable {

    var presaleStatus = 1 // 预售状态 1: 立即抢购 2：轮空（不可用） 3：已抢完 4:抢购截止, 5:xxx开始预售 = ['1', '3', '4', '5']

    var soldVolDesc: String? = null // 预售销量限量提示文案

    var showHotIcon = 0 // 标识是否显示火焰图标，0不显示，1显示

    constructor(parcel: Parcel) : this() {
        presaleStatus = parcel.readInt()
        soldVolDesc = parcel.readString()
        showHotIcon = parcel.readInt()
    }

    override fun toString(): String {
        return "PreSaleInfo(presaleStatus=$presaleStatus, soldVolDesc=$soldVolDesc, showHotIcon=$showHotIcon)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(presaleStatus)
        parcel.writeString(soldVolDesc)
        parcel.writeInt(showHotIcon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PreSaleInfo> {
        override fun createFromParcel(parcel: Parcel): PreSaleInfo {
            return PreSaleInfo(parcel)
        }

        override fun newArray(size: Int): Array<PreSaleInfo?> {
            return arrayOfNulls(size)
        }
    }
}
