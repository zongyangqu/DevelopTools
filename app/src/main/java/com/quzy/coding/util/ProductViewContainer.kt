package com.quzy.coding.util

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.bean.CommonProductBean
import com.quzy.coding.bean.ProductUiConfigBean
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.show
import com.quzy.coding.util.widget.IconFont
import com.quzy.coding.util.widget.PriceFontView

/**
 * CreateDate:2023-06-29 16:29
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description: 商品单元所有元素的承载体
 */
class ProductViewContainer(private val context: Context, val view: View) {
    val productUiConfigBean by lazy {
        ProductUiConfigBean()
    }
    var productImage: ImageView? = null
    var productTitle: TextView? = null
    var productSubTitle: TextView? = null
    var productMarketingDesc: TextView? = null
    var productPrice: PriceFontView? = null
    var productRise: TextView? = null
    var productSuperPrice: TextView? = null
    var productOriginalPrice: TextView? = null
    var productOriginalPriceBottom: TextView? = null
    var purchaseSpecialPriceIv: ImageView? = null
    var productAppSpecialPrice: ImageView? = null
    var addCartButton: IconFont? = null
    var clBrandLayout: ConstraintLayout? = null
    var tvBrandTitle: TextView? = null
    var ivBrandBg: ImageView? = null
    var cartNumber: TextView? = null
    var subLine: LinearLayout? = null
    var llTipsSubTitle: RelativeLayout? = null
    var itemSecKillTipsProgressBar: ProgressBar? = null
    var itemSekKillTipsSubTitle: TextView? = null
    var tvLimitSubtitle: TextView? = null
    var clAddCart: ConstraintLayout? = null

    //首件价
    var tvFirstLimit: TextView? = null

    // 单个商品购物车数量，目前只有新分类页可能还会买使用。
    var cartNum: TextView? = null

    // 榜单入口相关
    var rankIcon: ImageView? = null
    var rankTitle: TextView? = null
    var rankInfoLayout: ConstraintLayout? = null

    //商品促销信息 V8110新增
    var productPromotionTv: TextView? = null

    /**
     * @method setProductData
     * @description 渲染view
     * @date: 2021/3/19 9:00 PM
     * @author: ZhaoXuan.Zeng
     * @param [productBean]
     * @return
     */
    fun setProductData(productBean: CommonProductBean) {
        productImage?.let {
            Glide.with(context).load(productBean.cover?.imageUrl)
                .apply(RequestOptions.errorOf(R.mipmap.cat)).into(it)
        }
        setProductTitle(productBean)
        setProductSubTitle(productBean)
        setTagListAndMarketingDesc(
            productBean,
            productUiConfigBean.goneTagsAndMarketingDesc,
            productUiConfigBean.showTagAndMarketingDescAtTheSameTime
        )
        setProductPrice(productBean, productUiConfigBean.goneSVipAndOriginalPrice)
        setAddCartStatus(productBean)
        setRankInfo(productBean)
    }


    /**
     * @method setTagListAndMarketingDesc
     * @description 设置标签和营销语
     */
    private fun setTagListAndMarketingDesc(
        productBean: CommonProductBean,
        goneTagsAndMarketingDesc: Boolean,
        showAtTheSameTime: Boolean
    ) {
        val tagList = productBean.tag?.commonTags
        val recSlogan = productBean.recSlogan
        var seckillInfo = productBean.seckillInfo
        if (!goneTagsAndMarketingDesc && !TextUtils.isEmpty(recSlogan)){
            productMarketingDesc?.text = recSlogan
            productMarketingDesc?.show()
            updateRecSloganColor(productBean)
        } else {
            productMarketingDesc?.gone()
        }

    }


    /**
     * 设置商品元素上榜单信息
     */
    private fun setRankInfo(productBean: CommonProductBean) {
        if (productBean?.skuRankInfo != null && !TextUtils.isEmpty(productBean?.skuRankInfo?.name) && rankInfoLayout != null) {
            // v810商品单元榜单优化 ui稿：https://modao.cc/app/ax7jhkl8l2fhby7yjrjtjwciyg0ufgdk#screen=skxe984cj1vxa92
            rankInfoLayout?.visibility = View.VISIBLE
            when (productBean?.skuRankInfo?.rank) {
                1 -> {
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_one)
                }

                2 -> {
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_one)
                }

                3 -> {
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_three)
                }

                4 -> {
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_four)
                }

                5 -> {
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_five)
                }

                else -> {
                    // 兜底图
                    rankIcon?.setImageResource(R.drawable.ic_rank_top_default)
                }
            }
            rankTitle?.text = productBean?.skuRankInfo?.name
        } else {
            rankInfoLayout?.visibility = View.GONE
        }

        if (productBean?.brandInfo != null && !TextUtils.isEmpty(productBean?.brandInfo?.name) && clBrandLayout != null) {
            tvBrandTitle?.text = productBean?.brandInfo?.name
            ivBrandBg?.let {
                Glide.with(context).load(productBean?.brandInfo?.url)
                    .apply(RequestOptions.errorOf(R.mipmap.cat)).into(it)
            }
            clBrandLayout?.show()
        } else {
            clBrandLayout?.gone()
        }
    }

    /**
     * @method setProductTitle
     * @description 设置商品标题
     * @date: 2021/3/19 9:00 PM
     * @author: ZhaoXuan.Zeng
     * @param [productBean]
     * @return
     */
    private fun setProductTitle(productBean: CommonProductBean) {
        productTitle?.text = productBean.title
    }

    /**
     * @method setProductSubTitle
     * @description 设置副标题
     * @date: 2021/3/19 9:00 PM
     * @author: ZhaoXuan.Zeng
     * @param [productBean]
     * @return
     */
    private fun setProductSubTitle(productBean: CommonProductBean) {
        val subTitle = productBean.subTitle
        val recAttribute = productBean.recAttribute

        if (!recAttribute.isNullOrEmpty()) {
            productSubTitle?.visibility = View.VISIBLE
            productSubTitle?.text = getRecommendTitle(recAttribute)
        } else {
            if (!subTitle.isNullOrEmpty()) {
                productSubTitle?.visibility = View.VISIBLE
                productSubTitle?.text = subTitle
            } else {
                productSubTitle?.visibility = if (productUiConfigBean.goneSubTitle) {
                    View.GONE
                } else {
                    View.INVISIBLE
                }
            }
        }
    }

    /**
     * 设置商品单元推荐语 8120  优先级高于二级标题
     */
    fun getRecommendTitle(titles: List<String>?): SpannableStringBuilder? {
        var titleBd = SpannableStringBuilder()
        titles?.forEachIndexed { index, s ->
            if (index == 0) {
                titleBd.append(s)
            } else {
                titleBd.append(" | ")
                titleBd.setSpan(
                    AbsoluteSizeSpan(UIUtils.sp2px(context, 10f)), titleBd.length - 2,
                    titleBd.length - 1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                titleBd.append(s)
            }
        }
        return titleBd
    }

    /**
     * 更新营销推荐语或者上市日期文本颜色
     * @param productBean CommonProductBean
     */
    private fun updateRecSloganColor(productBean: CommonProductBean) {
        productMarketingDesc?.setTextColor(
            if (productBean.isBatchTime()) {
                ResourceUtil.getColor(R.color.subGreenColor)
            } else {
                ResourceUtil.getColor(R.color.themeColor)
            }
        )
    }

    private fun shouldShowPromotionMsg(
        promotionMsg: String?,
        productBean: CommonProductBean
    ): Boolean {
        //cornerStyle=2表示折扣标签，此时价格后不展示折扣信息
        //只有B组件和搜索一行一列有上述逻辑，其它组件不需要判断cornerStyle !=2
        return !promotionMsg.isNullOrEmpty() && productPromotionTv != null
                && productBean.cornerStyle != 2
    }

    /**
     * @method setProductPrice
     * @description 设置价格
     * @date: 2021/3/19 8:59 PM
     * @author: ZhaoXuan.Zeng
     * @param [productBean, goneSVipAndOriginalPrice]
     * @return
     */
    private fun setProductPrice(productBean: CommonProductBean, goneSVipAndOriginalPrice: Boolean) {
        val price = productBean.price?.price
        val firstPrice = productBean.price?.firstPrice //首购专享价 优先级>执行价
        if (price.isNullOrEmpty()) {
            showAbnormalPrice(goneSVipAndOriginalPrice)
        } else {
            if (!TextUtils.isEmpty(firstPrice) && purchaseSpecialPriceIv != null && productOriginalPriceBottom != null) {
                //首购
                showFirstBuyPrice(productBean)
            } else if (productBean.price?.priceType == "limit"
                && productBean.price?.priceDesc?.isNotEmpty() == true
                && productBean.price?.priceContent?.isNotEmpty() == true
                && tvFirstLimit != null
            ) {
                //首件
                showLimitPrice(productBean)
            } else {
                // 价格正常返回
                val superPrice = productBean.price?.svpPrice
                val originalPrice = productBean.price?.marketPrice
                val appSpecialPrice = productBean.price?.appSpecialPrice // app专享价
                val onlinePrice = productBean.price?.onlinePrice // 会员价
                val promotionMsg = productBean.price?.promotionMsg //促销信息
                // 现价
                val priceStringOri =
                    ResourceUtil.getString(R.string.format_yuan_string_common, price)
                val priceSpan = SpannableString(priceStringOri)

                val yuanSize = productUiConfigBean.priceUnitSize
                priceSpan.setSpan(
                    AbsoluteSizeSpan(UIUtils.sp2px(context, yuanSize)),
                    0,
                    1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )

                productPrice?.text = priceSpan
                productPrice?.setTypeface(PriceFontView.typeface, Typeface.NORMAL)
                productPrice?.setTextColor(SkinUtils.getColor(context, R.color.themeColor))
                // 批次品
                productRise?.visibility = if (productBean.isSkuProduct()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                purchaseSpecialPriceIv?.gone()
                tvFirstLimit?.gone()
                productOriginalPriceBottom?.gone()
                // 优先级：超级会员价＞商品促销＞会员专享价＞划线价
                // 超级会员价
                if (!superPrice.isNullOrEmpty() && superPrice.toDouble() < price.toDouble() && productSuperPrice != null) {
                    showSuperPrice(superPrice)
                } else if (shouldShowPromotionMsg(promotionMsg, productBean)) { //V8110 新增促销信息
                    showPromotionMsg(goneSVipAndOriginalPrice, promotionMsg)
                } else if ((!appSpecialPrice.isNullOrEmpty() || !onlinePrice.isNullOrEmpty()) && productAppSpecialPrice != null) {
                    showAppSpecialPrice(onlinePrice)
                } else {
                    productSuperPrice?.visibility = if (goneSVipAndOriginalPrice) {
                        View.GONE
                    } else {
                        View.INVISIBLE
                    }
                    productAppSpecialPrice?.visibility = View.GONE
                    productPromotionTv?.gone()
                    // 划线价
                    showOriginalPrice(price, originalPrice)
                }
            }
        }
    }

    /**
     * 展示划线价
     */
    private fun showOriginalPrice(price: String, originalPrice: String?) {
        if (!originalPrice.isNullOrEmpty() && originalPrice.toDouble() > price.toDouble() && productOriginalPrice != null) {
            val priceText = productPrice?.text
            val originalText = context.getString(R.string.format_yuan_string, originalPrice)
            if (productUiConfigBean.showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit > 0f && productPrice != null && !priceText.isNullOrEmpty()) {
                val priceTextWidth =
                    productPrice?.paint?.measureText(priceText, 0, priceText.length - 1) ?: -1f
                val originalPriceTextWidth = productOriginalPrice?.paint?.measureText(
                    originalText,
                    0,
                    originalText.length - 1
                ) ?: -1f
                if (priceTextWidth + originalPriceTextWidth >= productUiConfigBean.showPriceAndOriginalPriceAtTheSameTimeMaxWidthLimit) {
                    productOriginalPrice?.visibility = View.GONE
                } else {
                    productOriginalPrice?.visibility = View.VISIBLE
                    productOriginalPrice?.paint?.flags = Paint.STRIKE_THRU_TEXT_FLAG
                    productOriginalPrice?.text = originalText
                    productOriginalPrice?.setTextColor(
                        SkinUtils.getColor(
                            context,
                            R.color.subGreyColor
                        )
                    )
                }
            } else {
                productOriginalPrice?.visibility = View.VISIBLE
                productOriginalPrice?.paint?.flags = Paint.STRIKE_THRU_TEXT_FLAG
                productOriginalPrice?.text = originalText
                productOriginalPrice?.setTextColor(
                    SkinUtils.getColor(
                        context,
                        R.color.subGreyColor
                    )
                )
            }
        } else {
            productOriginalPrice?.visibility = View.GONE
        }
    }

    /**
     * 展示App专享价
     */
    private fun showAppSpecialPrice(onlinePrice: String?) {
        // app 专享价
        productOriginalPrice?.visibility = View.GONE
        productSuperPrice?.visibility = View.GONE
        productPromotionTv?.gone()
        productAppSpecialPrice?.visibility = View.VISIBLE

        // 虽然产品定义这里会员价（onlinePrice）和 专享价（appSpecialPrice）优先级相同，互斥。
        // 但是如果两者都不为空，则这里还是以会员价优先级高于专享价
        productAppSpecialPrice?.apply {
            background = null
            setImageResource(
                if (!onlinePrice.isNullOrEmpty()) {
                    R.drawable.ic_app_special_price
                } else {
                    R.drawable.ic_app_special_price
                }
            )
        }
    }

    /**
     * 展示促销价格信息
     */
    private fun showPromotionMsg(goneSVipAndOriginalPrice: Boolean, promotionMsg: String?) {
        productOriginalPrice?.visibility = View.GONE
        productAppSpecialPrice?.visibility = View.GONE
        productSuperPrice?.visibility = if (goneSVipAndOriginalPrice) {
            View.GONE
        } else {
            View.INVISIBLE
        }
        productPromotionTv?.show()
        productPromotionTv?.text = promotionMsg
    }

    /**
     * 展示超级会员价
     */
    private fun showSuperPrice(superPrice: String) {
        productOriginalPrice?.visibility = View.GONE
        productAppSpecialPrice?.visibility = View.GONE
        productPromotionTv?.gone()
        productSuperPrice?.visibility = View.VISIBLE
    }

    /**
     * 展示首价|首件价格
     */
    private fun showLimitPrice(productBean: CommonProductBean) {
        //首价底部展示marketPrice
        val price = productBean.price?.marketPrice
        val firstPrice = productBean.price?.priceContent

        productOriginalPriceBottom?.text =
            ResourceUtil.getString(R.string.format_yuan_string_common, price)
        // 现价
        val priceStringOri = ResourceUtil.getString(R.string.format_yuan_string_common, firstPrice)
        val priceSpan = SpannableString(priceStringOri)
        val yuanSize = productUiConfigBean.priceUnitSize
        priceSpan.setSpan(
            AbsoluteSizeSpan(UIUtils.sp2px(context, yuanSize)),
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        productPrice?.text = priceSpan
        productPrice?.setTypeface(PriceFontView.typeface, Typeface.NORMAL)
        productPrice?.setTextColor(SkinUtils.getColor(context, R.color.themeColor))

        tvFirstLimit?.text = productBean.price?.priceDesc
        tvFirstLimit?.show()
        productOriginalPriceBottom?.show()
        productPromotionTv?.gone()
        productAppSpecialPrice?.gone()
        productOriginalPrice?.gone()
        productSuperPrice?.gone()
        purchaseSpecialPriceIv?.gone()
        // 批次品
        productRise?.visibility = if (productBean.isSkuProduct()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * 首购价格展示
     */
    private fun showFirstBuyPrice(productBean: CommonProductBean) {
        val price = productBean.price?.price
        val firstPrice = productBean.price?.firstPrice //首购专享价 优先级>执行价

        productOriginalPriceBottom?.text =
            ResourceUtil.getString(R.string.format_yuan_string_common, price)
        // 现价
        val priceStringOri = ResourceUtil.getString(R.string.format_yuan_string_common, firstPrice)
        val priceSpan = SpannableString(priceStringOri)
        val yuanSize = productUiConfigBean.priceUnitSize
        priceSpan.setSpan(
            AbsoluteSizeSpan(UIUtils.sp2px(context, yuanSize)),
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        productPrice?.text = priceSpan
        productPrice?.setTypeface(PriceFontView.typeface, Typeface.NORMAL)
        productPrice?.setTextColor(SkinUtils.getColor(context, R.color.themeColor))
        purchaseSpecialPriceIv?.show()
        productOriginalPriceBottom?.show()
        productPromotionTv?.gone()
        productAppSpecialPrice?.gone()
        productOriginalPrice?.gone()
        productSuperPrice?.gone()
        tvFirstLimit?.gone()
        // 批次品
        productRise?.visibility = if (productBean.isSkuProduct()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * 异常价格展示
     */
    private fun showAbnormalPrice(goneSVipAndOriginalPrice: Boolean) {
        // 如果正常价格为空，则隐藏划线价和超级会员价，红色价格标注价格异常
        // 保留一个空格，
        val exceptionPriceString =
            SpannableString("${ResourceUtil.getString(R.string.str_price_exception)} ")
        val yuanSize = 12f
        exceptionPriceString.setSpan(
            AbsoluteSizeSpan(UIUtils.sp2px(context, yuanSize)),
            0,
            6,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        productPrice?.text = exceptionPriceString
        productPrice?.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        productPrice?.setTextColor(ResourceUtil.getColor(R.color.themeColor))
        productRise?.visibility = View.GONE
        productOriginalPrice?.visibility = View.GONE
        productAppSpecialPrice?.visibility = View.GONE
        productOriginalPriceBottom?.visibility = View.GONE
        productPromotionTv?.gone()
        purchaseSpecialPriceIv?.gone()
        tvFirstLimit?.gone()
        productSuperPrice?.visibility = if (goneSVipAndOriginalPrice) {
            View.GONE
        } else {
            View.INVISIBLE
        }
    }

    /**
     * @method setAddCartStatus
     * @description 设置加购按钮状态和是否展示看相似按钮
     * @date: 2021/6/29 6:40 下午
     * @author: ZhaoXuan.Zeng
     * @param [productsDataBean]
     * @return
     */
    private fun setAddCartStatus(productBean: CommonProductBean) {
        if (productBean.isSoldOut()) {
            // 售罄状态
            addCartButton?.setTextColor(ResourceUtil.getColor(R.color.subGreyColor))
        } else {
            addCartButton?.setTextColor(ResourceUtil.getColor(R.color.themeColor))
        }
    }
}
