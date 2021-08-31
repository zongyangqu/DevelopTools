package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.Fruit
import kotlinx.android.synthetic.main.item2.view.*

/**
 * CreateDate:2021/8/23 14:25
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class RecycVerticalAdapter(val context : Context?,var data : ArrayList<Fruit>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false)
        var vh = VH(inflate)
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var vh = holder as VH
        var fruit = data?.get(position)
        vh.iv_item_lv.setImageResource(fruit?.Imid?:0)
        vh.tv_item_lv.text = fruit?.name
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    class VH(v : View) : RecyclerView.ViewHolder(v){
        val tv_item_lv: TextView = v.findViewById(R.id.tv_item_lv)
        val iv_item_lv: ImageView = v.findViewById(R.id.iv_item_lv)
    }
}