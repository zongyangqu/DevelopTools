package com.quzy.coding.bean


/**
 * CreateDate:2022/11/2 15:59
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.common.productcard.mvvm.model.product.card
 * @Description:
 */
class BrandInfo (
    val name: String?,
    val url: String?,
    val action: String?,
    val buriedPoint: HomeBrandBuriedPoint?
)

class HomeBrandBuriedPoint(
    val yh_activityUrl: String?,
    val yh_owner: String?,
    val yh_activityPageId: String?,
    val yh_elementIndexNum: String?,
    val yh_contentName: String?,
    val yh_remarkName: String?,
    val yh_moduleName: String?,
    val yh_pageName: String?,
    val yh_elementName: String?,
    val yh_productId: String?,
    var yh_productName: String?
)