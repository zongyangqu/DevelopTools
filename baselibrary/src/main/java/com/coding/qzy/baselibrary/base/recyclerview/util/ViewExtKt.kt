package com.coding.qzy.baselibrary.base.recyclerview.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * CreateDate:2021/12/17 10:58
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.base.recyclerview.util
 * @Description:
 */
fun Int.toView(context: Context, parent: ViewGroup? = null, attach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(this, parent, attach)
}