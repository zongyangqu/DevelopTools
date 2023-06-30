package com.quzy.coding.util.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.ui.adapter.CartRankSwitchProductAdapter

/**
 * CreateDate:2023-06-29 23:36
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description:
 */
class CartRankListViewProductSwitcher : CartRankProductSwitcher<CartRankSwitchProductAdapter> {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
    }

    override fun createItemDecoration(): RecyclerView.ItemDecoration? {
        var itemDecoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                outRect.right = if (position in 0..1) {
                    itemDecorationSize
                } else {
                    0
                }
            }
        }
        return itemDecoration
    }

    override fun getDecorationItemSize(): Int {
        val size = (UIUtils.getWindowWidth(context)/2 - (12f + 4.5f + 12f + 150f).dpOfInt)/2
        return if(size > 0) {
            size
        } else {
            0
        }
    }

    override fun createAdapter(): CartRankSwitchProductAdapter {
        return CartRankSwitchProductAdapter()
    }

    override fun updateData(
        adapter: CartRankSwitchProductAdapter,
        dataList: ArrayList<RankListProductBean>
    ) {
        adapter.dataList.addAll(dataList)
    }

    override fun animationDelay(): Long {
        return 300L
    }
}