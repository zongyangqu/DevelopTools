package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.base.MultipleTypeAdapter
import com.quzy.coding.databinding.ItemSecKillProductCommonBinding
import com.quzy.coding.ui.holder.SecKillProductViewHolder

/**
 * CreateDate:2023/2/14 13:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
open class SecKillBaseAdapter(
    protected val code: String,
    protected val round: Int,
    protected val lifecycleOwner: LifecycleOwner,
    protected val fragmentManager: FragmentManager,
    protected val categoryName: String? = "",
    protected val position: String? = "",
) : MultipleTypeAdapter() {

    override fun createViewHolder(
        viewType: Int,
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder? {

        return SecKillProductViewHolder(
            code,
            round,
            ItemSecKillProductCommonBinding.inflate(inflater, parent, false),
            categoryName,
            position
        )
    }

    fun notifyAllCartNum() {
        //notifyItemRangeChanged(0, itemCount, SecKillConstants.PAY_LOAD_CART_NUM)
    }
}
