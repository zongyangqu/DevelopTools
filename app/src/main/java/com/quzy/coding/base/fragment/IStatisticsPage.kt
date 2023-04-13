package com.quzy.coding.base.fragment

import android.app.Activity

/**
 * CreateDate:2023/2/14 11:28
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.fragment
 * @Description:
 */
interface IStatisticsPage {
    fun getAnalyticsDisplayName(): String?
    fun isEnablePageView(): Boolean
    fun getContainerActivity(): Activity?
}