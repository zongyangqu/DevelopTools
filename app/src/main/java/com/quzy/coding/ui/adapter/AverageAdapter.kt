package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.bean.Fruit
import com.quzy.coding.ui.holder.AverageKotlinVH
import kotlinx.android.synthetic.main.item2.view.*

/**
 * CreateDate:2021/8/23 14:25
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class AverageAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var totalItemWidth: Int = 0
    var spanConut: Int = 4
    var fruit: ArrayList<Fruit>? = null

    fun setData(data: ArrayList<Fruit>?) {
        this.fruit = data
        totalItemWidth = UIUtils.getWindowWidth(context) - 12f.dpOfInt * 4
        spanConut = if (data?.size ?: 1 > 3) 4 else data?.size ?: 1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_average, parent, false)
        var vh = AverageKotlinVH(inflate)
        return vh
    }

    private fun initItemViewData(itemView: View) {
        // 每列展示n个
        itemView.layoutParams.width = totalItemWidth / spanConut
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        initItemViewData(holder?.itemView)
        var vh = holder as AverageKotlinVH
        var fruit = fruit?.get(position)
        vh.bindData(fruit)
    }

    override fun getItemCount(): Int {
        return fruit?.size ?: 0
    }

}
