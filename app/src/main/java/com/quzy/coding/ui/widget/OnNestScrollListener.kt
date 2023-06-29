package com.quzy.coding.ui.widget

import androidx.recyclerview.widget.RecyclerView


interface OnNestScrollListener {
    fun onParentScrollStateChanged(recyclerView: RecyclerView, newState: Int)

    fun onParentScrolled(
        recyclerView: RecyclerView,
        totalDx: Int,
        currentDx: Int,
        totalDy: Int,
        currentDy: Int
    )

    fun onChildScrollStateChanged(recyclerView: RecyclerView, newState: Int)

    fun onChildScrolled(
        recyclerView: RecyclerView,
        totalDx: Int,
        currentDx: Int,
        totalDy: Int,
        currentDy: Int
    )
}
