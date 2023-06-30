package com.quzy.coding.bean.model

object CartItemType {
    const val ITEM_VIEW_TYPE_DEFAULT = -1
    const val TYPE_COUPON_COLLECTION = 1 // 优惠券凑单
    const val TYPE_MODULE_TITLE = 2 // 模块头部
    const val TYPE_PROMOTION_TITLE = 3 // 促销活动提示
    const val TYPE_EXCHANGE_PRODUCT = 4 // 换购商品列表
    const val TYPE_PRODUCT = 5 // 购物车的商品组件
    const val TYPE_LINE = 6 // 分割线
    const val TYPE_TAGS = 7 // V8120刷选标签

    const val TYPE_EMPTY = 8 // 购物车的空态图  再用
    const val ITEM_VIEW_TYPE_SELLER_INFO_BAR = 9 // 门店信息、优惠券、活动信息
    const val TYPE_TOP_MSG = 10 // 顶部公告模块

    const val ITEM_VIEW_TYPE_OUT_STOCK_MORE = 29 // 补货中商品查看更多
    const val ITEM_VIEW_TYPE_BALANCE_INFO_BAR = 30 // 商家底部的结算部分
    const val ITEM_VIEW_TYPE_TIP_DIVIDER_FAT = 31 // 12dp的间距

    const val ITEM_VIEW_TYPE_GUESS_FAV_TITLE = 518 // 猜你喜欢的标题
    const val ITEM_VIEW_TYPE_GUESS_FAV_FOOTER = 521 // 猜你喜欢的底部
    const val ITEM_VIEW_TYPE_NO_DELIVERLY_BAR = 522 // 商品超配info
    const val ITEM_VIEW_TYPE_NODELIVERLY_PROUCT = 523 // 超配商品
    const val ITEM_VIEW_TYPE_NODELIVERLY_MORE = 524 // 超配商品查看更多
    const val ITEM_VIEW_TYPE_WATER_FULL_GUESS_YOU_LIKE = 525 // 瀑布流的猜你喜欢
    const val ITEM_VIEW_TYPE_CART_I_OFTEN_BUY = 526 // 我常买
    const val ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE = 527 // 购物车榜单入口组件
    const val ITEM_VIEW_TYPE_CART_GUESSYOULIKE_CARD_ENTRANCE = 528 // 购物车猜你喜欢卡片入口组件
    const val ITEM_VIEW_TYPE_CART_TOGETHER_CARD_ENTRANCE = 529 // 购物车精选搭配卡片入口组件
    const val ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE_NEW = 530 // 购物车新榜单入口组件 v975
}
