package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:55
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
open class ProductBean() : Parcelable {
    var action: String? = null // 点击商品单元跳转的action

    var batch: Batch? = null // 商品批次信息

    var cartAction: CartAction? = null // 当加购按钮不出现(instock=0)时，同位置的跳转按钮

    var cornerImageUrl: String? = null // 角标图片地址

    var cornerStyle = 0 // 角标样式 1：氛围 0：普通

    var cover: Cover? = null // 卡片封面

    var coverActionUrl: String? = null // 当非商品卡片类型时，点击封面图跳转的action ,

    var inStock = 0 // 是否有库存，1 有库存，0 无库存

    var presaleInfo: PreSaleInfo? = null // 当此商品是预售商品时，预售相关信息

    var price: Price? = null // 价格

    var recSlogan: String? = null // 营销推荐语

    var scene = 0

    var seckillInfo: SecKillInfo? = null // 当此商品是秒杀商品时，秒杀相关信息

    var skuCode: String? = null // 商品编码

    var skuSaleType = 0 // 商品销售类型 0 普通商品 1 全球潮物商品 2 次日达商品" 3 一件代发商品

    var skuType = 0 // 商品类型：普通、预售、秒杀

    var subTitle: String? = null // 副标题

    var title: String? = null // 标题


    var type = 0 // 商品卡片类型

    var w: String? = null // 卡片宽度 xN 表示一行几列 = ['1N', '2N', '3N']


    var preprocess = 0 // 是否是预处理 0 非预处理 1 预处理

    constructor(parcel: Parcel) : this() {
        action = parcel.readString()
        batch = parcel.readParcelable(Batch::class.java.classLoader)
        cartAction = parcel.readParcelable(CartAction::class.java.classLoader)
        cornerImageUrl = parcel.readString()
        cornerStyle = parcel.readInt()
        cover = parcel.readParcelable(Cover::class.java.classLoader)
        coverActionUrl = parcel.readString()
        inStock = parcel.readInt()
        presaleInfo = parcel.readParcelable(PreSaleInfo::class.java.classLoader)
        price = parcel.readParcelable(Price::class.java.classLoader)
        recSlogan = parcel.readString()
        scene = parcel.readInt()
        seckillInfo = parcel.readParcelable(SecKillInfo::class.java.classLoader)
        skuCode = parcel.readString()
        skuSaleType = parcel.readInt()
        skuType = parcel.readInt()
        subTitle = parcel.readString()
        title = parcel.readString()
        type = parcel.readInt()
        w = parcel.readString()
        preprocess = parcel.readInt()
    }

    override fun toString(): String {
        return "ProductBean(action=$action, batch=$batch, cartaction=$cartAction, cornerImageUrl=$cornerImageUrl, cornerStyle=$cornerStyle, cover=$cover, coverActionUrl=$coverActionUrl, inStock=$inStock, presaleInfo=$presaleInfo, price=$price, recSlogan=$recSlogan, scene=$scene, seckillInfo=$seckillInfo, skuCode=$skuCode, skuSaleType=$skuSaleType, skuType=$skuType, subTitle=$subTitle, title=$title,  type=$type, w=$w, preprocess=$preprocess)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(action)
        parcel.writeParcelable(batch, flags)
        parcel.writeParcelable(cartAction, flags)
        parcel.writeString(cornerImageUrl)
        parcel.writeInt(cornerStyle)
        parcel.writeParcelable(cover, flags)
        parcel.writeString(coverActionUrl)
        parcel.writeInt(inStock)
        parcel.writeParcelable(presaleInfo, flags)
        parcel.writeParcelable(price, flags)
        parcel.writeString(recSlogan)
        parcel.writeInt(scene)
        parcel.writeParcelable(seckillInfo, flags)
        parcel.writeString(skuCode)
        parcel.writeInt(skuSaleType)
        parcel.writeInt(skuType)
        parcel.writeString(subTitle)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeString(w)
        parcel.writeInt(preprocess)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductBean) return false

        if (skuCode != other.skuCode) return false

        return true
    }

    override fun hashCode(): Int {
        return skuCode?.hashCode() ?: 0
    }

    companion object CREATOR : Parcelable.Creator<ProductBean> {
        override fun createFromParcel(parcel: Parcel): ProductBean {
            return ProductBean(parcel)
        }

        override fun newArray(size: Int): Array<ProductBean?> {
            return arrayOfNulls(size)
        }
    }
}
