package com.quzy.coding.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.MeasureSpec
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * 嵌套RecyclerView的内部RecycleView
 */
open class ChildRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    RecyclerView(context, attrs, defStyleAttr) {

    private var mVelocityY = 0

    var totalDy: Int = 0

    var startY: Float = 0F

    var startX: Float = 0F

    var scrollVertical = false

    var isScrollUp = false

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                startY = ev.y
                startX = ev.x
            }
            MotionEvent.ACTION_MOVE -> {
                isScrollUp = ev.y - startY > 0
                scrollVertical = abs(ev.y - startY) - abs(ev.x - startX) > 0
            }
        }
        if (scrollVertical) {
            // 内部子RecycleView消费掉事件，当滚动到底部或者顶部时会把事件交给外部RecycleView
            if ((isScrollUp && !isScrollTop()) || (!isScrollUp && !isScrollBottom())) {
                requestDisallowInterceptTouchEvent(true)
            } else {
                requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        if (isAttachedToWindow.not()) return false
        val fling = super.fling(velocityX, velocityY)
        mVelocityY = if (!fling || velocityY >= 0) {
            // fling为false表示加速度达不到fling的要求，将mVelocityY重置
            0
        } else {
            // 正在进行fling
            velocityY
        }
        return fling
    }

    fun isScrollTop(): Boolean {
        // RecyclerView.canScrollVertically(-1)的值表示是否能向上滚动，false表示已经滚动到顶部
        return !canScrollVertically(-1)
    }

    fun isScrollBottom(): Boolean {
        // RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
        return !canScrollVertically(1)
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val heightSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
//        super.onMeasure(widthMeasureSpec, heightSpec)
//    }
}
