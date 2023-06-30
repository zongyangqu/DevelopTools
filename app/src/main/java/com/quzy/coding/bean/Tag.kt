package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @date 3/20/21 10:12
 * @author zhaolei yang
 * @description 这里是tag合集
 */
@Parcelize
class Tag : Parcelable {

    var commonTags: List<TagCell>? = null // 标签列表

    var ribbonTags: List<TagCell>? = null // 腰带标签列表

    var titleTag: TagCell? = null // 标题前的标签，仅有一个
}
