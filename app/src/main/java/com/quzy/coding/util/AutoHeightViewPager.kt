package com.quzy.coding.util

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

/**
 * CreateDate:2023/2/14 18:45
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
class AutoHeightViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childView = getChildAt(currentItem)
        if (childView != null) {
            childView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val viewHeight = childView.measuredHeight
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY))
        } else {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
        }
    }
}