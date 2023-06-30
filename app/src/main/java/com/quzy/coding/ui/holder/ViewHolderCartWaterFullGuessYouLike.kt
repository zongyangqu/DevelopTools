package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.base.viewholder.CommonWaterfallProductViewHolder
import com.quzy.coding.bean.CartCallBackType
import com.quzy.coding.bean.WaterFullGuessYouLikeBean
import com.quzy.coding.util.ICartView
import com.quzy.coding.util.extend.singleClick

/**
 * CreateDate:2023-06-29 17:31
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class ViewHolderCartWaterFullGuessYouLike(mICartView: ICartView?,itemView: View) :
    CommonWaterfallProductViewHolder(itemView){
    init {

        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.width = UIUtils.getWindowWidth(context) / 2
        itemView.layoutParams = params


        this.lifecycleOwner = mICartView?.getAty()

        if (isInSubMainPage()) {
            this.mCurrentPageType = PAGE_SUB_HOME
        } else {
            this.mCurrentPageType = PAGE_HOME
        }

        itemView.singleClick {
        }
    }

    protected var productGuessBean: WaterFullGuessYouLikeBean? = null

    fun onBindView(itemView: View, mICartView: ICartView) {
    }

    fun onBindData(
        cartBaseBean: WaterFullGuessYouLikeBean?,
        position: Int
    ) {
        if (cartBaseBean is WaterFullGuessYouLikeBean) {
            productGuessBean = cartBaseBean
            cartBaseBean.commonProductBean?.let { productBean ->
                bindProductData(productBean)
            }
        }
    }

    override fun onAddToCartSuccess(index: Int, callBackType: CartCallBackType): Boolean {
        var requestTogetherCard = false
        return true
    }

}
