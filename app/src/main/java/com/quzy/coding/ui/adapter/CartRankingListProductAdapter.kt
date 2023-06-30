package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.ui.holder.CartRankingListProductViewHolder
import com.quzy.coding.util.ICartView

/**
 * CreateDate:2023-06-30 15:51
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:榜单入口商品列表Adapter
 */
class CartRankingListProductAdapter(val mICartView: ICartView?) : RecyclerView.Adapter<CartRankingListProductViewHolder>() {

    val dataList by lazy {
        ArrayList<RankListProductBean>()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRankingListProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_rank_list_product_layout, parent, false)
        return CartRankingListProductViewHolder(view, mICartView)
    }

    override fun onBindViewHolder(holder: CartRankingListProductViewHolder, position: Int) {
        holder.bindProductData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}