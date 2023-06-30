package com.quzy.coding.bean

/**
 * CreateDate:2023-06-29 14:59
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class WaterFullGuessYouLikeBean(
    itemType: Int,
    var commonProductBean: CommonProductBean?,
    var mSearchId: Int ? = 0,
    var recommendResource: String ? = "",
    var historyId: String ? = "",
    var traceId: String ? = "",
    var requestId: String ? = "",
    var asId: String ? = "",
    // 该坑位能否展示猜你喜欢卡片
    var enableShowGuessYouLikeCard :Boolean = false,
    // 该坑位是否展示过猜你喜欢卡片
    var isShowGuessYouLikeCard :Boolean = false,
    // 该坑位能否展示精选搭配卡片
    var enableShowTogetherCard :Boolean = false,
    // 该坑位是否展示过精选搭配卡片
    var isShowTogetherCard :Boolean = false
): CartBaseBean(itemType)