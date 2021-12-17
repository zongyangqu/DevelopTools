package com.quzy.coding.ui.adapter

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.quzy.coding.ui.activity.ChangeRecyclerViewModeKotActivity
import com.quzy.coding.util.ISearchResult

/**
 * CreateDate:2021/12/16 18:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
open class NewRecyclerViewHolder(protected var parentView: View, var mISearchResult: ISearchResult) : RecyclerViewHolder(parentView){

    open fun initType(){
        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.isFullSpan = mISearchResult.getCurrentViewType() == 1
    }

}