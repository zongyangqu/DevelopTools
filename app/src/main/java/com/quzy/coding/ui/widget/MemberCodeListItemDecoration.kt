package com.quzy.coding.ui.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.ui.adapter.MemberCodeMainAdapter

/**
 * CreateDate:2023/6/17 17:00
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget
 * @Description:
 */
class MemberCodeListItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val memberCodeMainAdapter = parent.adapter as MemberCodeMainAdapter
        val stickItemPosition = memberCodeMainAdapter.stickItemPosition
        val currentViewPosition = parent.getChildAdapterPosition(view)
        // 门店标题以下，都加上顶部距离
        if (currentViewPosition > stickItemPosition) {
            outRect.top = 9f.dpOfInt
        }
        if (memberCodeMainAdapter.hasCard) {
            if (currentViewPosition > 0 && stickItemPosition < stickItemPosition - 1) {
                outRect.top = 9f.dpOfInt
            }
        } else {
            if (currentViewPosition > 0 && stickItemPosition < stickItemPosition) {
                outRect.top = 9f.dpOfInt
            }
        }

        if (currentViewPosition == stickItemPosition) {
            outRect.top = 9f.dpOfInt
        }
    }
}
