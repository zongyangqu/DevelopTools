package com.quzy.coding.base.viewholder

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.bean.CommonProductBean
import com.quzy.coding.util.ProductViewContainer
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.isShowing
import com.quzy.coding.util.extend.show
import com.quzy.coding.util.extend.singleClick

/**
 * CreateDate:2023-06-29 16:17
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.viewholder
 * @Description: 公用单商品的ViewHolder
 */
abstract class CommonSingleProductViewHolder(itemView: View) : CommonBaseProductViewHolder(itemView) {
    protected val viewContainer = ProductViewContainer(context, itemView)

    protected var productBean: CommonProductBean? = null

    init {
        itemView.singleClick {
            trackedModuleClick()
            AbToastUtil.showToast(itemView.context,"点击了列表")
        }
    }

    protected abstract fun initViewContainer()

    protected open fun trackedModuleClick() {}

    protected open fun trackedModuleClickAddCart() {}

    /**
     * @method bindProductData
     * @description 绑定商品数据
     * @date: 2021/3/17 3:49 PM
     * @author: ZhaoXuan.Zeng
     * @param [productsDataBean]
     * @return
     */
    fun bindProductData(productBean: CommonProductBean) {
        this.productBean = productBean
        initViewContainer()
        cartNumber = viewContainer.cartNumber
        viewContainer.addCartButton?.singleClick {
            trackedModuleClickAddCart()
            addToCart(productBean, viewContainer.productImage)
        }
        viewContainer.rankInfoLayout?.singleClick {
            onRankLayoutClick(productBean)
        }
        viewContainer.clBrandLayout?.singleClick {
            onBrandLayoutClick(productBean)
        }
        viewContainer.productUiConfigBean.apply {
            goneSubTitle = goneSubTitles()
            goneTagsAndMarketingDesc = goneTagsAndMarketingDesc()
            showTagAndMarketingDescAtTheSameTime = showTagAndMarketingDescAtTheSameTime()
            goneSVipAndOriginalPrice = goneSVipAndOriginalPrice()
            priceUnitSize = getPriceUnitSize()
            showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit = showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit()
        }
        viewContainer.setProductData(productBean)
        setProductCartCount(productBean)
        setAddCartMargin()
        expandBindData(itemView)
        updateSkinUi()
    }

    /**
     * 设置商品数量
     */
    open fun setProductCartCount(productBean: CommonProductBean?) {
        if (viewContainer?.cartNumber != null) {
            if (productBean == null) {
                return
            }
            if (productBean.isSoldOut()) {
                // 已售完
                dealSoldOut()
            } else {
                // 还有库存
                val cartCount = getProductCartNum(productBean)
                if (cartCount == 0) {
                    dealCartCountIsEmpty(productBean)
                } else {
                    dealCartCountIsNotEmpty(productBean, cartCount)
                }
            }
        }
    }

    /**
     * 处理购物车数量为0时的状态
     */
    private fun dealCartCountIsEmpty(productBean: CommonProductBean) {
        viewContainer?.cartNumber?.gone()
    }

    /**
     * @description 处理购物车数量不为0的状态
     */
    private fun dealCartCountIsNotEmpty(productBean: CommonProductBean, cartCount: Int) {
        setBadgeNumText(cartCount, viewContainer?.cartNumber)
        viewContainer?.cartNumber?.show()
    }

    private fun dealSoldOut() {
        viewContainer?.cartNumber?.gone()
    }

    /**
     * 设置操作商品视图间距
     */
    private fun setAddCartMargin() {
        var clAddCartLayoutParams = viewContainer?.clAddCart?.layoutParams as? ConstraintLayout.LayoutParams
        clAddCartLayoutParams?.let {
            if (viewContainer?.productOriginalPriceBottom?.isShowing() == true) {
                it.bottomMargin =  17f.dpOfInt
            } else {
                it.bottomMargin = 0f.dpOfInt
            }
            viewContainer?.clAddCart?.layoutParams = it
        }
    }

    override fun trackProductExpo() {
        super.trackProductExpo()
    }
}
