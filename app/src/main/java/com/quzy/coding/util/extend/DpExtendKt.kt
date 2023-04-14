package com.coding.qzy.baselibrary.utils.extend

import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.base.BaseApplication

/**
 * CreateDate:2021/10/25 11:03
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.utils.extend
 * @Description:
 */
val Float.dp
    get() = UIUtils.dip2px(BaseApplication.getContext(), this).toFloat()

val Float.dpOfInt
    get() = UIUtils.dip2px(BaseApplication.getContext(), this)

val Float.sp
    get() = UIUtils.sp2px(BaseApplication.getContext(), this).toFloat()