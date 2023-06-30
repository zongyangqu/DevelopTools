package com.quzy.coding.ui.holder

import android.view.View
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.quzy.coding.R
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.databinding.ItemCartNewRankListLayoutBinding

/**
 * CreateDate:2023-06-29 23:28
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:购物车新榜单资源位的商品ViewHolder
 */
class CartRankSwitchProductViewHolder(itemView: View) : RecyclerViewHolder(itemView) {
    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ItemCartNewRankListLayoutBinding.bind(itemView)
    }



    fun expandBindData(rankListBean : RankListProductBean) {
        Glide.with(itemView.context).load(rankListBean?.cover?.imageUrl).into(viewBinding?.ivProduct).apply { RequestOptions.errorOf(R.mipmap.cat) }
        Glide.with(itemView.context).load(rankListBean?.leftTopImageUrl).into(viewBinding?.ivLabel).apply { RequestOptions.errorOf(R.mipmap.cat) }
        playInAnimation()
    }

    private fun playInAnimation() {
        val inAnim = AnimationUtils.loadAnimation(itemView.context, R.anim.anim_cart_rank_switch_product_in)
        viewBinding.root.startAnimation(inAnim)
    }

    fun playOutAnimation() {
        val outAnim = AnimationUtils.loadAnimation(itemView.context, R.anim.anim_cart_rank_switch_product_out)
        viewBinding.root.startAnimation(outAnim)
    }

}