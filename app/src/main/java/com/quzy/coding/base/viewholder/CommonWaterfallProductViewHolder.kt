package com.quzy.coding.base.viewholder

import android.view.View
import com.quzy.coding.base.viewholder.CommonSingleProductViewHolder
import com.quzy.coding.databinding.CommonWaterFallProductItemLayoutBinding
import com.quzy.coding.util.extend.increaseTouchRange

/**
 * CreateDate:2023-06-29 17:29
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description: 瀑布流不固定高度商品组件
 */
open class CommonWaterfallProductViewHolder(itemView: View, private val realView: View? = null) : CommonSingleProductViewHolder(itemView) {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        CommonWaterFallProductItemLayoutBinding.bind(realView ?: itemView)
    }


    override fun initViewContainer() {
        viewContainer.run {
            productImage = viewBinding.ivProduct
            productTitle = viewBinding.tvTitle
            productSubTitle = viewBinding.tvSubtitle
            productMarketingDesc = viewBinding.tvMarketingDesc
            productPrice = viewBinding.tvPrice
            productRise = viewBinding.tvRise
            productSuperPrice = viewBinding.tvSuperPrice
            productOriginalPrice = viewBinding.tvOriginalPrice
            addCartButton = viewBinding.btnAddCart
            rankIcon = viewBinding.ivRankIcon
            rankTitle = viewBinding.tvRankTitle
            rankInfoLayout = viewBinding.clRankLayout
            productAppSpecialPrice = viewBinding.ivAppSpecialPrice
            clBrandLayout = viewBinding.clBrandLayout
            tvBrandTitle = viewBinding.tvBrandTitle
            ivBrandBg = viewBinding.ivBrandBg
            productPromotionTv = viewBinding.promotionTv
            cartNumber = viewBinding.cartNumber
            subLine = viewBinding.subLine
            llTipsSubTitle = viewBinding.llTipsSubTitle
            itemSecKillTipsProgressBar = viewBinding.itemSecKillTipsProgressBar
            itemSekKillTipsSubTitle = viewBinding.itemSekKillTipsSubTitle
            tvLimitSubtitle = viewBinding.tvLimitSubtitle
            productOriginalPriceBottom = viewBinding.tvOriginalPriceBottom
            purchaseSpecialPriceIv = viewBinding.purchaseSpecialPriceIv
            clAddCart = viewBinding.clAddCart
        }
        viewBinding.clAddCart.increaseTouchRange(12)
    }

    override fun goneSubTitles(): Boolean {
        return true
    }
}
