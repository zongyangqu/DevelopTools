package com.quzy.coding.base

import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.bean.MultipleType

/**
 * CreateDate:2023/2/14 13:43
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
abstract class MultipleTypeAdapter protected constructor() :
    BaseTypeAdapter<MultipleType>() {
    companion object {
        private const val NONE_TYPE = -1
    }

    override fun bindHolder(holder: ViewHolderTag<MultipleType>, position: Int) {
        data.getOrNull(position)?.let {
            holder.setHolder(it)
        }
    }

    override fun bindHolder(
        holder: ViewHolderTag<MultipleType>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        data.getOrNull(position)?.let {
            holder.setHolder(it, payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data.getOrNull(position)?.viewType() ?: NONE_TYPE
    }

    override fun holderBindExtras(holder: RecyclerView.ViewHolder, position: Int) {
    }
}
