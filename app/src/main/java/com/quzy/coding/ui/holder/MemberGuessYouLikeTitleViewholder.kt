package com.quzy.coding.ui.holder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.view_content_guess_you_like_title.view.*

/**
 * CreateDate:2021/10/28 15:27
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberGuessYouLikeTitleViewholder(mLifecycleOwner: LifecycleOwner?, itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindData(s: String?) {

        itemView.tv_search_result_guess_title.text=s
    }

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }
}