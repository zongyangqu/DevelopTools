package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/28 10:26
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class TagCell() : Parcelable {

    var imageUrl: String? = null // 标签图片url，比如全球购中的国旗

    var text: String? = null // 标签文字

    /**
     * 标签类型 10: 价格优惠, 11:服务, 12:优惠券, 13:履约, 14:预售,
     * 15:跨境购, 16:特色, 30:次日达, 31:商家直发, 32:产地直发,
     * 50:冷鲜, 51:冷藏, 52:冷冻
     */
    var type = 0

    constructor(parcel: Parcel) : this() {
        imageUrl = parcel.readString()
        text = parcel.readString()
        type = parcel.readInt()
    }

    override fun toString(): String {
        return "TagCell(imageurl=$imageUrl, text=$text, type=$type)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(text)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TagCell> {
        override fun createFromParcel(parcel: Parcel): TagCell {
            return TagCell(parcel)
        }

        override fun newArray(size: Int): Array<TagCell?> {
            return arrayOfNulls(size)
        }
    }
}
