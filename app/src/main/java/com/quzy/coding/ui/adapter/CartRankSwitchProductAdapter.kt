package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.ui.holder.CartRankSwitchProductViewHolder

/**
 * CreateDate:2023-06-29 23:27
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class CartRankSwitchProductAdapter : RecyclerView.Adapter<CartRankSwitchProductViewHolder>() {
    val dataList by lazy {
        ArrayList<RankListProductBean>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRankSwitchProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_new_rank_list_layout, parent, false)
        return CartRankSwitchProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartRankSwitchProductViewHolder, position: Int) {
        holder.expandBindData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}