package com.quzy.coding.base

import android.app.Activity

/**
 * CreateDate:2023/2/14 11:31
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
interface ILoginCheck {

    fun onLoginActivityResult(result: Int)

    fun isAtyAlive(): Boolean

    fun getAtyContext(): Activity?
}