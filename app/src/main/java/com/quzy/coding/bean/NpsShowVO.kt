package com.quzy.coding.bean

import android.os.Parcelable
import android.text.SpannableStringBuilder
import kotlinx.android.parcel.IgnoredOnParcel
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

class ProductBlock(
    var blockId: String?,
    var blockType: Int?,
    var position: Int?,
    var skuBlock: CommonProductBean?,
    var statistic: EventStatistic?,
    val rankList: RankListDataBean?
)

class RankListDataBean {
    var bgImgUrl: String? = null
    var subTitle: String? = null
    var title: String? = null
    /**
     * 榜单类型枚举值：
     * 1：72小时热卖 2：蔬菜 3：水果 4：肉禽蛋品 5：海鲜水产 6：乳品烘焙热 7：速食冻品 8：休闲零食 9：酒饮 10：水饮饮料 11：粮油副食
     * 12：回购榜 13：蔬菜回购榜 14：水果回购榜 15：肉禽蛋品回购榜 16：海鲜水产回购榜 17：牛奶回购榜 18：面包烘焙回购榜 19：速食冻品回购榜
     * 20：休闲零食回购榜 21：酒水饮料回购榜 22：粮油副食回购榜 23：水饮回购榜 24：人气爆款排行榜
     */
    var rankType = -1
    var skus: ArrayList<RankListProductBean>? = null
    var imageUrlV: String? = null
    var imageUrlH: String? = null
}

class RankListProductBean : CommonProductBean() {
    // 热度值
    var heatPrompt: String? = null
    // 左上角排名图标
    var leftTopImageUrl: String? = null
    // 发起搜索的搜索词，目前在搜索输入页榜单里的 热门发现列表item点击埋点时用
    var keyword: String? = null
}


