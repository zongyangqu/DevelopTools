package com.quzy.coding.base

import android.content.Context
import android.util.AttributeSet

/**
 * CreateDate:2023/6/16 16:53
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description: 骨架屏加载view
 */
class LoadingView : NewLoadingView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}