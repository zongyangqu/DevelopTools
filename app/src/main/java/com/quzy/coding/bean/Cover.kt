package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * CreateDate:2021/10/19 16:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class Cover() : Parcelable {

    var imageUrl: String? = null // 商品图片

    var videoCoverImageUrl: String? = null // 商品视频的封面url

    var videoUrl: String? = null // 商品视频链接

    constructor(parcel: Parcel) : this() {
        imageUrl = parcel.readString()
        videoCoverImageUrl = parcel.readString()
        videoUrl = parcel.readString()
    }

    override fun toString(): String {
        return "Cover(imageurl=$imageUrl, videocoverimageurl=$videoCoverImageUrl, videourl=$videoUrl)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(videoCoverImageUrl)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cover> {
        override fun createFromParcel(parcel: Parcel): Cover {
            return Cover(parcel)
        }

        override fun newArray(size: Int): Array<Cover?> {
            return arrayOfNulls(size)
        }
    }
}
