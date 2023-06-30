package com.quzy.coding.base.viewholder

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.bean.CartCallBackType
import com.quzy.coding.bean.CommonProductBean

/**
 * CreateDate:2023-06-29 16:19
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.viewholder
 * @Description: 通用的base商品单元ViewHolder
 */
abstract class CommonBaseProductViewHolder(itemView: View) : RecyclerViewHolder(itemView) {
    protected var sellerID: String? = null
    protected var shopID: String? = null
    protected var lifecycleOwner: LifecycleOwner? = null
    protected var fragmentManager: FragmentManager? = null
    protected var mCurrentPageType = PAGE_HOME
    protected var cartView: View? = null
    protected var cartNumber: TextView? = null
    protected var fromProductImageView: View? = null
    protected val context: Context by lazy {
        itemView.context
    }

    companion object {
        private const val MAIN_ACTIVITY = "cn.yonghui.hyd.MainActivity"
        const val PAGE_HOME = 0
        const val PAGE_SUB_HOME = 1
        private const val IS_DELIVERY = 1
        const val COMMON_NORMAL_PRICE_UNIT_SIZE = 12f
        const val COMMON_SMALL_PRICE_UNIT_SIZE = 10f
        const val OLDER_NORMAL_PRICE_UNIT_SIZE = 14f
        const val ACTION_TYPE_SIMILAR = 1 // 看相似
        const val ACTION_TYPE_NOTIFY = 2 // 到货提醒
        const val ACTION_TYPE_UNABLE = 12 // 按钮置灰
    }

    var isDelivery = IS_DELIVERY
    var cartViewFunc: (() -> View?)? = null

    init {
        mCurrentPageType = PAGE_HOME
    }



    /**
     * 设置商品单元上榜单入口点击事件
     */
    protected fun onRankLayoutClick(productBean: CommonProductBean) {
        if (!TextUtils.isEmpty(productBean.skuRankInfo?.action)) {
            AbToastUtil.showToast(itemView.context,productBean.skuRankInfo?.action)
        }
    }




    /**
     * @description 加购后更新购物车数量
     */
    fun updateCartCountAfterAddCart(cartCallBackType: CartCallBackType?, cartCount: Int) {
        if (cartCount > 0) {
            setBadgeNumText(cartCount, cartNumber)
            cartNumber?.visibility = View.VISIBLE
        } else {
            cartNumber?.visibility = View.GONE
        }
    }

    fun setBadgeNumText(cartCount: Int, cartView: TextView?) {
        cartView ?: return
        if (cartCount > 99) {
            cartView.layoutParams?.apply {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            cartView.setBackgroundResource(R.drawable.bg_badge_rect)
            cartView.text = "99+"
        } else {
            if (cartCount < 10) {
                val params = cartView.layoutParams
                params?.apply {
                    height = 14f.dpOfInt
                    width = 14f.dpOfInt
                    cartView.layoutParams = params
                }
                cartView.setBackgroundResource(R.drawable.bg_common_product_cart_num)
            } else {
                cartView.layoutParams?.apply {
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                    width = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                cartView.setBackgroundResource(R.drawable.bg_badge_rect)
            }
            cartView.text = cartCount.toString()
        }
    }

    /**
     * @param productBean 商品bean
     * @return int
     * @method getProductCartCount
     * @description 获取商品在购物车中的数量
     */
    fun getProductCartNum(productBean: CommonProductBean?): Int {
        return 2
    }



    /**
     * @method expandBindData
     * @description 给子类使用用于扩展自身的业务逻辑的方法
     * @date: 2021/3/17 3:23 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun expandBindData(itemView: View) {
    }

    /**
     * @method expandBindDataWithData
     * @description 给子类使用用于扩展自身的业务逻辑的方法
     * @param []
     * @return
     */
    protected open fun expandBindDataWithData(itemView: View, productBean: CommonProductBean) {
    }

    /**
     * @method updateSkinUi
     * @description 给子类使用需要扩展特有的view的换肤逻辑
     * @date: 2021/3/17 3:23 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun updateSkinUi() {
    }

    fun isHomePage(): Boolean {
        if (context !is Activity) return false
        return context.javaClass.name == MAIN_ACTIVITY
    }

    protected open fun goneSubTitles(): Boolean {
        return false
    }

    /**
     * @method goneTagList
     * @description 是否隐藏副标题下的标签列表和营销标签语
     * @date: 2021/3/17 3:59 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun goneTagsAndMarketingDesc(): Boolean {
        return false
    }

    /**
     * @method showTagAndMarketingDescAtTheSameTime
     * @description 是否标签列表和营销语同时展示
     * @date: 2021/3/20 12:03 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun showTagAndMarketingDescAtTheSameTime(): Boolean {
        return true
    }

    /**
     * @method goneSVipAndOriginalPrice
     * @description 是否隐藏原价和超级会员价
     * @date: 2021/3/20 10:24 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun goneSVipAndOriginalPrice(): Boolean {
        return true
    }

    /**
     * @method trackProductExpo
     * @description 商品单元曝光事件
     * @date: 2021/3/23 7:42 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    open fun trackProductExpo() {
    }

    /**
     * @method onAddToCartSuccess
     * @description 加购成功的回调
     * @date: 2021/3/25 4:17 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return Boolean true：执行后续默认操作， false：不执行后续默认操作
     */
    protected open fun onAddToCartSuccess(index: Int, callBackType: CartCallBackType): Boolean {
        return true
    }

    protected open fun getPriceUnitSize(): Float {
        return COMMON_NORMAL_PRICE_UNIT_SIZE
    }

    /**
     * 设置商品单元上品牌馆入口点击事件
     */
    protected fun onBrandLayoutClick(productBean: CommonProductBean) {
        productBean.brandInfo?.action?.let {
            AbToastUtil.showToast(context,it)
        }
    }

    /**
     * @methodName showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit
     * @description 执行价和划线价同行展示时，最大可显示的宽度
     * @param []
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/11/22 10:02 上午
     */
    protected open fun showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit(): Float {
        return 0f
    }

    /**
     * @methodName appointProductImageWidth
     * @description 指定商品主图片view的宽度，用于已经确定ImageLoaderView宽的场景，这样可以不需要等测量拿到宽高之后再进行图片请求
     * @param []
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/10/14 6:44 下午
     */
    protected open fun appointProductImageWidth(): Int? {
        return null
    }

    /**
     * @methodName isInSubMainPage
     * @description 是否是在二级首页
     * @param []
     * @return
     * @author Zeng ZhaoXuan
     * @time 2023/2/21 4:04 下午
     */
    protected fun isInSubMainPage(): Boolean {
        return itemView.context is Activity && itemView.context.javaClass.simpleName == "SubMainActivity"
    }

    protected open fun supportOlderMode(): Boolean {
        return false
    }

    protected open fun addToCart(
        productBean: CommonProductBean,
        fromView: View?,
        index: Int = 0
    ) {
        if (productBean.isSoldOut() || productBean.cartAction?.actionType == ACTION_TYPE_UNABLE) {
            // 已售罄或者超配
            return
        }

        if (onAddToCartSuccess(index, CartCallBackType.NORMAL)) {
            AbToastUtil.showToast(itemView.context,"加购成功")
            // 支持多规格商品加购动画
        }
    }

    /**
     * @methodName addToCartPageSource
     * @description 加购的页面来源
     * @param []
     * @return
     * @author Zeng ZhaoXuan
     * @time 2023/5/17 10:41 上午
     */
    protected open fun addToCartPageSource(): String? {
        return null
    }

}