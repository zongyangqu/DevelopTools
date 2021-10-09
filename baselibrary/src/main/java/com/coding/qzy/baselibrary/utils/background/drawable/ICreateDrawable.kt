package com.coding.qzy.baselibrary.utils.background.drawable

import android.graphics.drawable.Drawable

/**
 * Created by xiaoqi on 2018/9/12
 */
interface ICreateDrawable {
    @Throws(Exception::class)
    fun create(): Drawable?
}