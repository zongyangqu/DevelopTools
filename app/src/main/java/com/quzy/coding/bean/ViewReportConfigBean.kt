package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2023/1/4 16:54
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class ViewReportConfigBean() : Parcelable {

    var status :Int = 0 //1代表开启 0代表关闭
    var commonPageStatus :String ?= null //通用页面开关
    var minViewCount :Int ?= null //通用页面 最小页面数
    var customPages :List<CustomPages> ?= null
    var customPagesMap :Map<String,CustomPages> ?= null

    constructor(parcel: Parcel) : this() {
        status = parcel.readInt()
        commonPageStatus = parcel.readString()
        minViewCount = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(status)
        parcel.writeString(commonPageStatus)
        parcel.writeValue(minViewCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewReportConfigBean> {
        override fun createFromParcel(parcel: Parcel): ViewReportConfigBean {
            return ViewReportConfigBean(parcel)
        }

        override fun newArray(size: Int): Array<ViewReportConfigBean?> {
            return arrayOfNulls(size)
        }
    }

}