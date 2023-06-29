package com.quzy.coding.ui.behavior

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.coding.qzy.baselibrary.widget.roundview.RoundConstraintLayout
import com.coding.qzy.baselibrary.widget.roundview.RoundLinearLayout
import com.quzy.coding.R
import com.quzy.coding.ui.adapter.MemberCodeMainAdapter
import com.quzy.coding.util.extend.margins

/**
 * CreateDate:2023/6/17 14:27
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.behavior
 * @Description: 会员码顶部支付方式列表，跟随主页楼层的RecycleView滚动做布局改变
 */
class MemberCodePayTypeBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
    var xhfPay: RoundConstraintLayout? = null
    var aliPay: RoundConstraintLayout? = null
    var weiXinPay: RoundConstraintLayout? = null
    var aliPayIndex = -1
    var weiXinPayIndex = -1
    var startWidth = -1
    var endWidth = 0
    var padding = -1

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return directTargetChild.id == R.id.memberCodeMarketingRecycleView && (axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0)
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
        val layout = (child as? RoundLinearLayout)
        val itemCount = layout?.childCount ?: 0
        for (i in 0..itemCount) {
            val payType = layout?.getChildAt(i) as? RoundConstraintLayout
            when (payType?.getTag(R.id.member_code_pay_type_item)) {
                "pay.xhpay.app.pay" -> xhfPay = payType
                "pay.weixin.app" -> {
                    weiXinPay = payType
                    weiXinPayIndex = i
                }
                "pay.alipay.app" -> {
                    aliPay = payType
                    aliPayIndex = i
                }
            }
        }
        val marketingRecyclerView = target as? RecyclerView
        val adapter = marketingRecyclerView?.adapter as? MemberCodeMainAdapter
        val storeTitleView = marketingRecyclerView?.layoutManager?.findViewByPosition(adapter?.stickItemPosition ?: Int.MAX_VALUE)
        val currentTop = storeTitleView?.top ?: 0
        val totalHeight = if (adapter?.hasCard == true) 140f.dp else 70f.dp
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

        if (padding < 0) {
            padding = layout?.paddingLeft ?: 0
        }

        if (alpha == 0F && xhfPay?.alpha ?: 0 == 0) {
            return
        }

        if (alpha == 1.0F && xhfPay?.alpha ?: 0 == 1.0F) {
            return
        }

        // 背景色渐变
        layout?.setBackgroundColor(UIUtils.getCurrentColor(alpha, Color.TRANSPARENT, Color.WHITE))
        // 小辉付alpha渐变
        xhfPay?.alpha = alpha

        val newPadding = getValueByProgress(padding, 0, 1 - alpha)

        layout?.let {
            it.setPadding(newPadding, it.paddingTop, newPadding, it.paddingBottom)
        }

        changePayTypeItemView(xhfPay, alpha, padding - newPadding)
    }

    /**
     * 顶部支付方式的宽度变化动画
     */
    private fun changePayTypeItemView(itemView: RoundConstraintLayout?, progress: Float, padding: Int) {
        if (startWidth < 0) {
            startWidth = itemView?.measuredWidth ?: 0
            endWidth = 0
        }

        val currentWidth = getValueByProgress(startWidth, endWidth, 1 - progress)
        val currentMargin = getValueByProgress(3f.dpOfInt, 0f.dpOfInt, 1 - progress)

        if (itemView?.isVisible == true) {
            // 小辉付宽度 startWidth -> 0
            val layoutParams = itemView.layoutParams
            layoutParams?.width = currentWidth
            itemView.layoutParams = layoutParams
            itemView.margins(currentMargin, 0, currentMargin, 0)
        }

        // 小辉付宽度渐变到0，多出的宽度由其他两个占用
        val newWidth = startWidth + (startWidth - currentWidth) / 2 + padding + currentMargin

        if (aliPay?.isVisible == true) {
            // 支付宝大小变化
            val layoutParamsLeft = aliPay?.layoutParams
            layoutParamsLeft?.width = newWidth
            aliPay?.layoutParams = layoutParamsLeft
        }

        if (weiXinPay?.isVisible == true) {
            // 微信支付大小变化
            val layoutParamsRight = weiXinPay?.layoutParams
            layoutParamsRight?.width = newWidth
            weiXinPay?.layoutParams = layoutParamsRight
        }
    }

    /**
     * 计算中间值 线性变化
     */
    private fun getValueByProgress(start: Int, end: Int, progress: Float): Int {
        if (progress <= 0) {
            return start
        }
        if (progress >= 1) {
            return end
        }
        return (start + (end - start) * progress).toInt()
    }
}
