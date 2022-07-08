package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:56
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class Batch() : Parcelable {

    var batchFlag: Int =
            0 // 批次标识，1是有一个批次 2是有多个批次（用以页面控制是否展示 “起”文案展示），0 不是批次 = ['0: 不是批次', '1:一个批次', '2:多个批次（用以页面控制是否展示 “起”文案展示'],

    var batchSkuCode: String? = null // 只有一个批次商品的时候,批次商品返回第一个批次编码RE1

    constructor(parcel: Parcel) : this() {
        batchFlag = parcel.readInt()
        batchSkuCode = parcel.readString()
    }

    override fun toString(): String {
        return "Batch(batchflag=$batchFlag, batchskucode=$batchSkuCode)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(batchFlag)
        parcel.writeString(batchSkuCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Batch> {
        override fun createFromParcel(parcel: Parcel): Batch {
            return Batch(parcel)
        }

        override fun newArray(size: Int): Array<Batch?> {
            return arrayOfNulls(size)
        }
    }
}
