package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:57
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class CartAction() : Parcelable {

    var actionText: String? = null // 当加购按钮不出现(instock=0)时，同位置的跳转按钮文本 = ['看相似', '到货提醒'],

    var actionType =
            0 // 按钮类型 1:看相似, 2:到货提醒, 3:预售：已抢完, 4:预售：抢购截止, 5:预售：x月x日 HH:mm 开始预售, 6:秒杀:原价买，7:秒杀:马上抢-正常，8:秒杀:马上抢-全灰，9:秒杀:开抢提醒，10:秒杀:已设提醒 = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],

    var actionUrl: String? = null // 当加购按钮不出现(instock=0)时，同位置的跳转链接

    var alerted = 0 // 是否已设置到货提醒

    constructor(parcel: Parcel) : this() {
        actionText = parcel.readString()
        actionType = parcel.readInt()
        actionUrl = parcel.readString()
        alerted = parcel.readInt()
    }

    override fun toString(): String {
        return "CartAction(actionText=$actionText, actionType=$actionType, actionUrl=$actionUrl, alerted=$alerted)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(actionText)
        parcel.writeInt(actionType)
        parcel.writeString(actionUrl)
        parcel.writeInt(alerted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartAction> {
        override fun createFromParcel(parcel: Parcel): CartAction {
            return CartAction(parcel)
        }

        override fun newArray(size: Int): Array<CartAction?> {
            return arrayOfNulls(size)
        }
    }
}
