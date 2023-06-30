package com.quzy.coding.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

interface ICartView  {
    fun getAty(): FragmentActivity?

    fun notifyDataSetChanged()
    fun notifyItemChanged(position: Int)
}
