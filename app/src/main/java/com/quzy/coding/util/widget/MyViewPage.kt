package com.quzy.coding.util.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * CreateDate:2021/12/3 17:09
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description:
 */
class MyViewPage(context: Context?, attributeSet: AttributeSet?) : ViewPager(context!!, attributeSet) {
    private var canSwipe = true
    fun setCanSwipe(canSwipe: Boolean) {
        this.canSwipe = canSwipe
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return canSwipe && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return canSwipe && super.onInterceptTouchEvent(ev)
    }
}