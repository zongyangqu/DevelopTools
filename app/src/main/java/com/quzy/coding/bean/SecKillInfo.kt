package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:59
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class SecKillInfo() : Parcelable {

    var buyType = 0 // 购买的价格类型 0:秒杀价 1:原价,

    var pushSign = false // 是否开启推送

    var realStock = 0 // 实际库存

    var secKillCopyWriting: String? = null // 秒杀展示文案：当真实库存<=限购库存时，显示仅剩X件 当限购库存<真实库存时，显示限购X件

    var secKillLimit = 0 // 实际秒杀限量,看前端需不需要

    var secKillPrice = 0 // 秒杀价格

    var specProp: String? = null // 商品规格

    var status = 0 // 状态

    var stock = 0 // 库存

    var stockLeft = 0 // 当秒杀库存低于等于10件时，剩余的秒杀库存数量，客户端据此显示进度条

    var stockStandard = 0 // 甚于库存进度条基准计算值，默认20

    var stockPrompt: String? = null // 秒杀库存相关提示

    var shopId: String? = null // 秒杀shopId

    var sellerId: String? = null // 秒杀sellerId

    var showProgressBar = 0 //是否显示进度条 0显示，1不显示

    constructor(parcel: Parcel) : this() {
        buyType = parcel.readInt()
        pushSign = parcel.readByte() != 0.toByte()
        realStock = parcel.readInt()
        secKillCopyWriting = parcel.readString()
        secKillLimit = parcel.readInt()
        secKillPrice = parcel.readInt()
        specProp = parcel.readString()
        status = parcel.readInt()
        stock = parcel.readInt()
        stockLeft = parcel.readInt()
        stockStandard = parcel.readInt()
        stockPrompt = parcel.readString()
        shopId = parcel.readString()
        sellerId = parcel.readString()
        showProgressBar = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(buyType)
        parcel.writeByte(if (pushSign) 1 else 0)
        parcel.writeInt(realStock)
        parcel.writeString(secKillCopyWriting)
        parcel.writeInt(secKillLimit)
        parcel.writeInt(secKillPrice)
        parcel.writeString(specProp)
        parcel.writeInt(status)
        parcel.writeInt(stock)
        parcel.writeInt(stockLeft)
        parcel.writeInt(stockStandard)
        parcel.writeString(stockPrompt)
        parcel.writeString(shopId)
        parcel.writeString(sellerId)
        parcel.writeInt(showProgressBar)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "SecKillInfo(buyType=$buyType, pushSign=$pushSign, realStock=$realStock, secKillCopyWriting=$secKillCopyWriting, secKillLimit=$secKillLimit, secKillPrice=$secKillPrice, specProp=$specProp, status=$status, stock=$stock, stockLeft=$stockLeft, stockStandard=$stockStandard, stockPrompt=$stockPrompt, shopId=$shopId, sellerId=$sellerId, showProgressBar=$showProgressBar)"
    }

    companion object CREATOR : Parcelable.Creator<SecKillInfo> {
        override fun createFromParcel(parcel: Parcel): SecKillInfo {
            return SecKillInfo(parcel)
        }

        override fun newArray(size: Int): Array<SecKillInfo?> {
            return arrayOfNulls(size)
        }
    }
}
