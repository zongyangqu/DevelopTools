package com.quzy.coding.bean

/**
 * CreateDate:2021/10/19 17:09
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class CustomerProperties(
        val propertyKey: String?,
        val propertyName: String?,
        val propertyValue: String?,
        val cornerMarkText: String?,
        val action: String?
)

class PromotionAndFunction(
        val image: String?,
        val link: String?,
        val title: String?,
        val action: Int?,
        val cornerMarkText: String?,
        val desc: String?
)

class ActivityAreaBean(
        val title: String?,
        val activities: ArrayList<ItemActivityCardBean>?
)