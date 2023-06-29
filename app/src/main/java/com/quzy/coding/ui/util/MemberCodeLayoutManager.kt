package com.quzy.coding.ui.util

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.ui.adapter.MemberCodeMainAdapter

/**
 * CreateDate:2023/6/17 16:57
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.util
 * @Description:
 */
class MemberCodeLayoutManager(context: Context?, private val mAdapter: RecyclerView.Adapter<*>) : LinearLayoutManager(context) {
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return try {
            super.scrollVerticallyBy(dy, recycler, state)
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    override fun canScrollVertically(): Boolean {
//        val childRecyclerView = findNestedScrollingChildRecyclerView()
//        return childRecyclerView == null || childRecyclerView.isScrollTop() || childRecyclerView.adapter?.itemCount ?: 0 == 0
//    }

    override fun addDisappearingView(child: View?) {
        try {
            super.addDisappearingView(child)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    private fun findNestedScrollingChildRecyclerView(): RecommendChildRecyclerView? {
//        return (mAdapter as? MemberCodeMainAdapter)?.nestedBottomViewHolder?.getCurrentChildRecyclerView()
//    }
}
