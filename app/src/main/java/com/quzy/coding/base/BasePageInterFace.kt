package com.quzy.coding.base

/**
 * CreateDate:2023/2/14 11:31
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
interface BasePageInterFace {
    fun enableSkeleton(layoutid: Int)

    fun showLoadingView(show: Boolean)
}