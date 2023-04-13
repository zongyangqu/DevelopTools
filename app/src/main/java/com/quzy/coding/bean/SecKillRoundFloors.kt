package com.quzy.coding.bean

/**
 * CreateDate:2023/2/14 11:08
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
open class SecKillRoundFloors(
    val floors: MutableList<MultipleType>,
    val isRefresh: Boolean,
    val canLoadMore: Boolean
)