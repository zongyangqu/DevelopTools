package com.quzy.coding.bean

import android.os.Parcelable
import android.text.SpannableStringBuilder
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

class NpsShowVO(
    val isShow: Int?,
    val npsConfigId: Int?,
    val leftContent: String?,
    val question: String?,
    val rightContent: String?,
    val score: List<Int>?,
    val title: String?
) :  Serializable
class NpsShowInfoVO(
    val leftContent: String?,
    val question: String?,
    val rightContent: String?,
    val score: List<Int>?,
    val title: String?
) :  Serializable

class NpsCommit() :Serializable
class NpsClose() : Serializable

class NpsCommitReq(
    val channelId: Int?,
    val cityId: String?,
    val cityName: String,
    val commercial: String,
    val commercialName: String,
    val configId: Int,
    val score: Int,
    val userId: String
) :  Serializable
class NpsCloseReq(
    val configId: Int,
    val userId: String
) :  Serializable

class RecResDataVO(
    var artificialrecommend: ArtificialRecommend,
    var asId: String,
    var categorybannerid: String,
    var page: String,
    var cardTitle: String,
    var pageBase: PageBase,
    var pagecount: Int,
    var recommendChoose: Int,
    var recommendsource: Int,
    var results: List<Block>,
    var total: Int,
    var totalpage: Int,
    var traceId: String
)
class ArtificialRecommend()
class PageBase(
    val hasNext: Int?,
    val id: String?,
    val nextPage: Int?,
    val nextUrl: String?,
    val pageName: String?,
    val pageSt: String?,
    val pageT: String?,
    val statistics: PageStatistic?
)
class Block(
    val blockId: String?,
    val blockType: String?,
    val position: String?,
    val skuBlock: CommonProductBean?,
    val statistic: EventStatistic?
)
class GuessItemData(
    var asId: String?,
    var traceId: String?,
    var size: Int,
    var skuBlock: CommonProductBean?,
    var pagename: String
)
class EventStatistic
class PageStatistic(
    val rPage: String?,
    val rpage: String?,
    val traceId: String?
)

data class HintBean(
        val iconNum: Int?,
        val action: String?,
        val iconUrl: String?,
        val keyWord: String?,
        val querySource: String?,
        val secondIconUrl: String?,
        val skuList: ArrayList<String?>?,
        val sortType: Int?,
        val title: String?,
        val isEmpty: Boolean = false,
        var content: SpannableStringBuilder?
)
