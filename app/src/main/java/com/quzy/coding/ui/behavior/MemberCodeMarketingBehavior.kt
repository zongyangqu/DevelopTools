package com.quzy.coding.ui.behavior

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.ui.adapter.MemberCodeMainAdapter

/**
 * CreateDate:2023/6/17 15:35
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.behavior
 * @Description:会员码主页支付方式列表，跟随主页楼层的RecycleView滚动做布局改变
 */
class MemberCodeMarketingBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return target.id == R.id.memberCodeMarketingRecycleView && (axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        val marketingRecyclerView = target as? RecyclerView
        val adapter = marketingRecyclerView?.adapter as? MemberCodeMainAdapter
        val stickyPosition = adapter?.stickItemPosition ?: -1
        val layoutManger: LinearLayoutManager? =
            marketingRecyclerView?.layoutManager as? LinearLayoutManager

        val totalHeight = if (adapter?.hasCard == true) 140f.dp else 70f.dp
        val storeTitleView = marketingRecyclerView?.layoutManager?.findViewByPosition(
            (marketingRecyclerView.adapter as? MemberCodeMainAdapter)?.stickItemPosition
                ?: Int.MAX_VALUE
        )
        val currentTop = storeTitleView?.top ?: 0
        var alpha = 1F
        alpha = when {
            currentTop <= 0 -> {
                0F
            }
            currentTop > totalHeight -> {
                1F
            }
            else -> {
                currentTop / totalHeight
            }
        }
        if (alpha > 1.0F) {
            alpha = 1.0F
        }
        if (alpha < 0.01F) {
            alpha = 0F
        }
        // 门店标题之上的都需要做背景色渐变
        for (i in 0 until stickyPosition) {
            layoutManger?.findViewByPosition(i)
                ?.setBackgroundColor(UIUtils.getCurrentColor(alpha, Color.TRANSPARENT, Color.WHITE))
        }
    }
}
