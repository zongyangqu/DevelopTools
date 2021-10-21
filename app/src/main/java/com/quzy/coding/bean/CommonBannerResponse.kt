package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 17:12
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */

@Parcelize
class CommonBannerResponse(var adSpaceCode: String?, var adSpaceName: String?, var adSpaceType: Int?, var assemblykey: String ?, var modellist: MutableList<CmsAdaModel>? = arrayListOf(), var bannerLists: MutableList<CmsAdaModel>? = arrayListOf(), var assemblyname: String ?, var assemblyid: String ?, var orderName: String?) : Parcelable {

    @Parcelize
    class CmsAdaModel :  Parcelable {
        var adUnitName: String? = null
        var url: String? = null
        var action: String? = null
        var imgurl: String? = null
        var width: Int? = 0
        var height: Int? = 0
        var name: String ? = null
        var pid: String? = null
        var position: Int ? = 0
        var activityCode: String? = null
    }
}
