package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:11
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
class MemberRankBean : Parcelable {
    var levelValue: Int ? = null
    var levelNickValue: String ? = null
    var link: String ? = null
}
