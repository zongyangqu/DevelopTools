package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import kotlinx.android.synthetic.main.membercenter_servicehelp_item.view.*

/**
 * CreateDate:2021/11/4 20:31
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class HorizontalRefreshItemViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var ctx: Context
    var mData: MutableList<String> = arrayListOf()

    var totalItemWidth: Int = 0
    var spanConut: Int = 4

    fun setData(ctx: Context, mData: MutableList<String>) {
        this.ctx = ctx
        this.mData = mData
        notifyDataSetChanged()
        totalItemWidth = UIUtils.getWindowWidth(ctx) - 17f.dpOfInt * 2
        spanConut = if (mData?.size >= 4) 4 else mData?.size
    }

    private fun initItemViewData(
        itemView: View,
        item_servicehelp_name: TextView,
        position: Int
    ) {
        // 每列展示n个
        itemView.layoutParams.width = totalItemWidth / spanConut
        item_servicehelp_name.text = mData[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pulltorefresh_grid, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as MyViewHolder
        initItemViewData(holder.itemView, holder.item_servicehelp_name, position)
    }

    override fun getItemCount(): Int {
        return mData?.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_servicehelp_name: TextView = itemView.findViewById(R.id.tv_subtitle)
    }
}
