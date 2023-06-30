package com.quzy.coding.bean

/**
 * CreateDate:2023-06-29 16:30
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description: 商品单元规范UI配置bean
 */
class ProductUiConfigBean {
    // 是否gone掉副标题
    var goneSubTitle = false
    // 是否gone掉副标题下的标签列表和营销标签语，不保留控件宽高
    var goneTagsAndMarketingDesc = false
    // 是否标签和营销语同时展示
    var showTagAndMarketingDescAtTheSameTime = true
    // 是否gone掉原价和超级会员价，不保留控件宽高
    var goneSVipAndOriginalPrice = true
    // 人民币价格符号￥的文字大小
    var priceUnitSize = 12f
    // 执行价和划线价能同时展示时的最大宽度限制（一般用于执行价和划线价展示在同一行时）
    var showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit = 0f
    // 是否展示老年版
    var showOlderMode = false
}