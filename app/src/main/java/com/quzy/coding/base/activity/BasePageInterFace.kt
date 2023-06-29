package com.quzy.coding.base.activity

/**
 * CreateDate:2023/6/16 16:30
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.activity
 * @Description:
 */
interface BasePageInterFace {

    fun enableSkeleton(layoutid: Int)

    fun showLoadingView(show: Boolean)
}