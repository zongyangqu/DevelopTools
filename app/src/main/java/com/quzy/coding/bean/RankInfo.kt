package com.quzy.coding.bean

import android.os.Parcel
import android.os.Parcelable

class RankInfo() : Parcelable {
    // 排行榜跳转链接
    var action: String? = null

    // 排行图标
    var rankingIcon: String? = null

    // 排行榜名称: 粮油副食热卖榜第3名
    var title: String? = null

    // 榜单排名 1～5
    var rank: Int = 0

    // 榜单名称（不带排名）: 粮油副食热卖榜
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        action = parcel.readString()
        rankingIcon = parcel.readString()
        title = parcel.readString()
        rank = parcel.readInt()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(action)
        parcel.writeString(rankingIcon)
        parcel.writeString(title)
        parcel.writeInt(rank)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "RankInfo(action=$action, rankingIcon=$rankingIcon, title=$title, rank=$rank, name=$name)"
    }

    companion object CREATOR : Parcelable.Creator<RankInfo> {
        override fun createFromParcel(parcel: Parcel): RankInfo {
            return RankInfo(parcel)
        }

        override fun newArray(size: Int): Array<RankInfo?> {
            return arrayOfNulls(size)
        }
    }
}
