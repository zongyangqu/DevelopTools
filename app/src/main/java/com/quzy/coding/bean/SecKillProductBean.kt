package com.quzy.coding.bean

import android.content.pm.ActivityInfo

/**
 * CreateDate:2023/2/14 13:53
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class SecKillProductBean : CommonProductBean(), MultipleType {

    var activityInfo: ActivityInfo? = null

    override fun viewType(): Int {
        return 2
    }
}