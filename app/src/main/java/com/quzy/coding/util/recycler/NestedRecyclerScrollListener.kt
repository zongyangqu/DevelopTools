package com.quzy.coding.util.recycler

import androidx.recyclerview.widget.RecyclerView

interface NestedRecyclerScrollListener {

    fun onRecyclerScroll(parent: RecyclerView, totalScrollY: Int)
}
