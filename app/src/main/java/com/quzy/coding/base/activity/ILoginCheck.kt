package com.quzy.coding.base.activity

import android.app.Activity

/**
 * CreateDate:2023/6/16 16:29
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.activity
 * @Description:
 */
interface ILoginCheck {

    fun onLoginActivityResult(result: Int)

    fun isAtyAlive(): Boolean

    fun getAtyContext(): Activity?
}