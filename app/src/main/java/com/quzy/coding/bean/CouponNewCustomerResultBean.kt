package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

/**
 * CreateDate:2022/7/1 10:07
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
open class CouponNewCustomerResultBean(
    var coupons: ArrayList<CouponMineDataBean>? = arrayListOf(),
    var sendstatus: Int? = null,
    var totalamount: Int? = null,
    var url: String? = null,
    // 835新增的埋点字段1为发券，2为补券
    var sendType: Int? = null,
    var newPersonSkus: ArrayList<NewCustomerProductModel>? = arrayListOf(),
    var title: String? = null,
    var subTitle: String? = null,
    var activityCode: String? = null,
    var buttonCopyWriting: String? = null
) : Parcelable {
    fun isSuccess(): Boolean = sendstatus ?: 0 == 1
}
