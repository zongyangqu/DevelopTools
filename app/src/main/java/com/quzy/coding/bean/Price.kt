package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:59
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class Price() : Parcelable {

    var marketPrice: String? = null // 市场价（划线价），文本，不包含人民币符号

    var price: String? = null // 价格，文本，不包含人民币符号

    var svpPrice: String? = null // 超级会员价，文本，不包含人民币符号，超级会员价和划线价互斥，不会同时出现

    var pricePrompt: String? = null // 常购商品：上次xxx元

    constructor(parcel: Parcel) : this() {
        marketPrice = parcel.readString()
        price = parcel.readString()
        svpPrice = parcel.readString()
        pricePrompt = parcel.readString()
    }

    override fun toString(): String {
        return "Price(marketPrice=$marketPrice, price=$price, svpPrice=$svpPrice)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(marketPrice)
        parcel.writeString(price)
        parcel.writeString(svpPrice)
        parcel.writeString(pricePrompt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Price> {
        override fun createFromParcel(parcel: Parcel): Price {
            return Price(parcel)
        }

        override fun newArray(size: Int): Array<Price?> {
            return arrayOfNulls(size)
        }
    }
}
