package com.quzy.coding.util.recycler

import android.content.Context
import android.util.AttributeSet
import com.quzy.coding.base.NestedChildItemContainer

/** 嵌套滑动子View，一定要继承该View  */
class NestedChildRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedPublicRecyclerView(context, attrs, defStyleAttr), NestedChildItemContainer {

    init {
        isNestedScrollingEnabled = true
        overScrollMode = OVER_SCROLL_ALWAYS
    }
}
