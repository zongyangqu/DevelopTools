package com.quzy.coding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder

/**
 * CreateDate:2023/2/14 13:43
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
abstract class BaseTypeAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val data: MutableList<T> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return createViewHolder(viewType, LayoutInflater.from(parent.context), parent)
            ?: RecyclerViewHolder(parent)
    }

    /**
     * 创建holder
     *
     * @return 对应类型的ViewHolder
     */
    protected abstract fun createViewHolder(
        viewType: Int,
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder?

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(bean: T) {
        val start = itemCount
        data.add(bean)
        notifyItemInserted(start)
    }

    fun add(list: List<T>?) {
        if (list.isNullOrEmpty()) {
            return
        }
        val start = itemCount
        val count = list.size
        data.addAll(list)
        notifyItemRangeInserted(start, count)
    }

    fun clear() {
        val count = itemCount
        data.clear()
        notifyItemRangeRemoved(0, count)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderTag<T>)?.let {
            bindHolder(it, position)
        }
        holderBindExtras(holder, position)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            (holder as? ViewHolderTag<T>)?.let {
                bindHolder(it, position, payloads)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    protected abstract fun bindHolder(
        holder: ViewHolderTag<T>,
        position: Int
    )

    protected abstract fun bindHolder(
        holder: ViewHolderTag<T>,
        position: Int,
        payloads: MutableList<Any>
    )

    protected abstract fun holderBindExtras(holder: RecyclerView.ViewHolder, position: Int)
}
