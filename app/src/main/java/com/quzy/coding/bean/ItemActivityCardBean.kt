package com.quzy.coding.bean

/**
 * CreateDate:2021/10/19 17:09
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class ItemActivityCardBean(
        val key: String?, // 每一个卡片的key
        val type: Int?, // 卡片类型
        val bgImg: String?, // 卡片整个的背景图
        val headInfo: HeadInfoBean?, // 头信息
        val contentInfo: ContentInfoBean?, // 中间部分信息
        val bottomLeftInfo: BottomLeftRightBean?, // 左下角信息
        val bottomRightInfo: BottomLeftRightBean?, // 右下角信息
        val moduleName: String?, // 埋点和曝光用
        var isRefresh: Boolean?, // 是否是刷新使用
        val bottomInfo: BottomInfo? //好物秀
)

class HeadInfoBean(
        val iconImg: String?, // 图标
        val redSpot: Int?,
        val firstTitle: SimpleActivityTextInfo?,
        val secondTitle: SimpleActivityTextInfo?
)

class ContentInfoBean(
        val bgImg: String?, // 内容部分的背景图
        val contentImg: String?, // 内容部分里的小图片，目前type=2 时会返回这个字段
        val jumpUrl: String?, // 跳转url
        val elementName: String?, // 事件名称，埋点用
        val button: ContentButtonBean, // 内容部分里的按钮bean 比如去领取 做任务等
        val firstTitle: SimpleActivityTextInfo?, // 内容部分里的第一个标题
        val secondTitle: SimpleActivityTextInfo?, // 内容部分里的第二个标题
        val skus: ArrayList<SimpleProductBean>?, // 商品列表 type=3 回购榜的时候会返回这个字段
        val liveRooms: ArrayList<LiveProductBean>? //好物秀   直播数据
)

class TitleSignBean(
        val start: Int?,
        val end: Int?,
        val color: String?,
        val size: Int?,
        val weight: String?
)

class ContentButtonBean(
        val bgImg: String?,
        val elementName: String?, // 事件名称，埋点用
        val jumpUrl: String?
)

class BottomLeftRightBean(
        val bgImg: String?, // 图标
        val jumpUrl: String?, // 跳转url
        val toast: String?, // 提示文案, 跳转链接为空时适用
        val title: String?, // 标题
        val elementName: String?, // 事件名称，埋点用
        val bubbleTitle: String? // 角标 2张可领
)

class SimpleProductBean(
        val title: String?,
        val heatPrompt: String?,
        val cover: ProductCoverBean?,
        val price: ProductPriceBean?,
        val leftTopImageUrl: String?,
        val action: String?
)

class ProductCoverBean(
        val imageUrl: String?
)

class ProductPriceBean(
        val marketPrice: String?,
        val price: String?
)

class BottomInfo(
        val bgImg: String?, //图标
        val jumpUrl: String?, //跳转url
        val toast: String?, //提示文案, 跳转链接为空时适用
        val title: String?, //标题
        val elementName: String?, //事件名称，埋点用
        val bubbleTitle: String?, //角标 2张可领
        val recommend: List<RecommendeBean>?, //推荐
        val secondTitle: String
)

class LiveProductBean(
        val firstTitle: String?,
        val jumpUrl: String?,
        val liveIcon: String?,
        val secondTitle: String?,
        val liveStatusDesc: String?,
        val coverImg: String?,
        val desc: String?,
        val endTime: String?,
        val liveStatus: Int?,
        val name: String?,
        val pid: String?,
        val roomId: String?,
        val elementName: String?,
        val startTime: String?

)

class RecommendeBean(
        val icon: String?,
        val text: String?
)
