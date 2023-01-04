package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable


/**
 * CreateDate:2022/12/27 23:56
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.utils.viewreport.bean
 * @Description:
 */
class CustomPages() : Parcelable {
    var pagename :String ?= null //页面名称 只是用于标记
    var viewClass :String ?= null //页面类
    var targetViews :List<String> ?= null //检查的元素，数组，必须都存在
    var placeHolderViews :List<String> ?= null //兜底页面

    constructor(parcel: Parcel) : this() {
        pagename = parcel.readString()
        viewClass = parcel.readString()
        targetViews = parcel.createStringArrayList()
        placeHolderViews = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pagename)
        parcel.writeString(viewClass)
        parcel.writeStringList(targetViews)
        parcel.writeStringList(placeHolderViews)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomPages> {
        override fun createFromParcel(parcel: Parcel): CustomPages {
            return CustomPages(parcel)
        }

        override fun newArray(size: Int): Array<CustomPages?> {
            return arrayOfNulls(size)
        }
    }
}