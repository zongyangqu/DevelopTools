package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * CreateDate:2021/10/19 17:08
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class TagBean() : Parcelable, Serializable {

    var type: String? = ""
    var text: String? = ""
    var imgurl: String? = ""
    var uitype: String ? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        text = parcel.readString()
        imgurl = parcel.readString()
        uitype = parcel.readString()
    }

    constructor(type: String?, text: String?, imgurl: String?) : this() {
        this.type = type
        this.text = text
        this.imgurl = imgurl
    }

    constructor(type: String?, text: String?) : this() {
        this.type = type
        this.text = text
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(text)
        parcel.writeString(imgurl)
        parcel.writeString(uitype)
    }

    override fun describeContents(): Int {
        return 0
    }



    companion object CREATOR : Parcelable.Creator<TagBean> {
        override fun createFromParcel(parcel: Parcel): TagBean {
            return TagBean(parcel)
        }

        override fun newArray(size: Int): Array<TagBean?> {
            return arrayOfNulls(size)
        }
    }
}