package com.quzy.coding.ui.holder

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.quzy.coding.R
import com.quzy.coding.base.viewholder.CommonSingleProductViewHolder
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.databinding.CartItemRankListProductLayoutBinding
import com.quzy.coding.util.ICartView
import com.quzy.coding.util.extend.disNull
import com.quzy.coding.util.extend.singleClick

/**
 * CreateDate:2023-06-30 15:55
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:榜单入口组件商品ViewHolder
 */
class CartRankingListProductViewHolder (itemView: View, val mICartView: ICartView?) : CommonSingleProductViewHolder(itemView) {

    private val viewBinding by lazy {
        CartItemRankListProductLayoutBinding.bind(itemView)
    }

    override fun initViewContainer() {
        viewContainer.apply {
            productPrice = viewBinding.tvPrice
            cartNumber = viewBinding.cartNumber
        }
    }

    fun bindProductData(product: RankListProductBean) {
        super.bindProductData(product)
        Glide.with(itemView.context).load(product.cover?.imageUrl).into(viewBinding.ivProduct).apply { RequestOptions.errorOf(
            R.mipmap.icon_common_placeholder) }
        Glide.with(itemView.context).load(product.leftTopImageUrl).into(viewBinding.ivLabel)
        val hotPrompt = product.heatPrompt
        if (hotPrompt.isNullOrEmpty()) {
            viewBinding.llInformation.visibility = View.GONE
        } else {
            viewBinding.llInformation.visibility = View.VISIBLE
            viewBinding.tvHotPrompt.text = hotPrompt.disNull()
        }

    }
}