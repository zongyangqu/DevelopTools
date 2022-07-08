package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2022/6/28 10:54
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.lib.style.coupon.model
 * @Description: 新人弹窗商品
 */
@Parcelize
data class NewCustomerProductModel(var imgUrl: String?, var priceText: String?, var inStock: Int?) :  Parcelable
