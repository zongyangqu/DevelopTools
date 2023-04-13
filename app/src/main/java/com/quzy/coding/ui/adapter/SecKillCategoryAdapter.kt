package com.quzy.coding.ui.adapter

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * CreateDate:2023/2/14 13:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class SecKillCategoryAdapter(
    code: String,
    round: Int,
    categoryName: String?,
    position: String?,
    lifecycleOwner: LifecycleOwner,
    fragmentManager: FragmentManager
) : SecKillBaseAdapter(code, round, lifecycleOwner, fragmentManager,categoryName,position){
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        when (holder) {

        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        when (holder) {
        }
    }

}
