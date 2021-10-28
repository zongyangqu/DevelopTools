package com.quzy.coding.util.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.quzy.coding.R
import com.quzy.coding.bean.TagBean
import com.quzy.coding.util.DrawableUtils

/**
 * CreateDate:2021/10/27 19:37
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
class TagView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {
        const val DISCOUNT_TAG = "discount" // 优惠标签
        const val SERVICE_TAG = "service" // 服务标签
        const val ADDRESS_TAG = "address" // 地址标签
        const val COUPON_TAG = "coupon" // 优惠券标签
        const val WXCOUPON = "wxcoupon" // 优惠券标签(微信)
        const val DISABLE = "disable" // 不可用标签
        const val DELIVERY_TAG = "delivery_tag" // 履约标签
        const val DELIVERY_TAG_CART = "delivery_tag_cart" // 购物车履约标签
        const val OMNICHANNEL = "omnichannel" // 全渠道标签
        const val OFFLINECHANNEL = "offlinechannel" // 线下门店券标签
        const val SPECIAL = "special" // 结算页内商品展示签收提醒标签文案
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    fun setTagData(tagBean: com.quzy.coding.bean.TagBean): Boolean {
        if (tagBean == null) {
            visibility = View.GONE
            return false
        } else {
            visibility = View.VISIBLE
        }
        if (TextUtils.isEmpty(tagBean.text) || tagBean.text == null) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
        }
        text = tagBean.text
        return setTagStyle(tagBean)
    }

    private fun setTagStyle(tagBean: TagBean?): Boolean {
        var support = false
        when (tagBean?.type) {
            DISCOUNT_TAG -> { // 优惠标签
                support = true
                setDiscountStyle()
            }
            SERVICE_TAG -> { // 服务标签
                support = true
                setServiceStyle()
            }

            ADDRESS_TAG -> { // 地址标签
                support = true
                setAddressTagStyle()
            }

            COUPON_TAG, OMNICHANNEL, OFFLINECHANNEL -> { // 优惠券标签
                support = true
                setCouponTagStyle()
            }

            WXCOUPON -> { // 微信优惠券标签
                support = true
            }
            DISABLE -> { // 不可用标签
                support = true
                setUnableStyle()
            }

            DELIVERY_TAG -> { // 履约标签
                support = true
                setDeliveryStyle()
            }

            DELIVERY_TAG_CART -> {
                support = true
                setDeliveryCartStyle()
            }

            SPECIAL -> {
                support = true
                setDeliveryStyle()
            }

            else -> { // 兜底逻辑
                support = true
                setServiceStyle()
            }
        }
        return support
    }

    private fun setDiscountStyle() {
        height = ScreenUtils.dp2px(context, 14)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 2), 0, ScreenUtils.dp2px(context, 2), 0)
        setTextColor(ContextCompat.getColor(context, R.color.themeColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_discount)
    }

    private fun setServiceStyle() {
        height = ScreenUtils.dp2px(context, 14)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 2), 0, ScreenUtils.dp2px(context, 2), 0)
        setTextColor(ContextCompat.getColor(context, R.color.subGreenColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_service)
    }

    private fun setAddressTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 4), 0, ScreenUtils.dp2px(context, 4), 0)
        paint.isFakeBoldText = true
        setTextColor(ContextCompat.getColor(context, R.color.themeColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_address)
        minimumWidth = ScreenUtils.dp2px(context, 30)
    }

    private fun setUnableStyle() {
        height = ScreenUtils.dp2px(context, 14)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 2), 0, ScreenUtils.dp2px(context, 2), 0)
        setTextColor(ContextCompat.getColor(context, R.color.subLightBlackColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_unable)
    }

    private fun setCouponTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 4), 0, ScreenUtils.dp2px(context, 4), 0)
        setTextColor(ContextCompat.getColor(context, R.color.themeColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_coupon)
    }

    // 普通优惠券可用、可领时标签
    fun setCouponAvailableTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(6f.dpOfInt, 0, 6f.dpOfInt, 0)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        background = DrawableUtils.createCornerLeftRightDrawable(
                0f,
                0f,
                9f,
                0f,
                ContextCompat.getColor(context, R.color.orangeColorGradientFrom),
                ContextCompat.getColor(context, R.color.orangeColorGradientTo)
        )
    }

    // 支付宝优惠券可用、可领时标签
    fun setAliPayCouponAvailableTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(6f.dpOfInt, 0, 6f.dpOfInt, 0)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        background = DrawableUtils.createCornerLeftRightDrawable(
                0f,
                0f,
                9f,
                0f,
                ContextCompat.getColor(context, R.color.alipay_coupon_tag_from),
                ContextCompat.getColor(context, R.color.alipay_coupon_tag_to)
        )
    }

    // 微信优惠券可用、可领时标签
    fun setWeChatCouponAvailableTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(6f.dpOfInt, 0, 6f.dpOfInt, 0)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        background = DrawableUtils.createCornerLeftRightDrawable(
                0f,
                0f,
                9f,
                0f,
                ContextCompat.getColor(context, R.color.wechat_coupon_tag_from),
                ContextCompat.getColor(context, R.color.wechat_coupon_tag_to)
        )
    }

    // 优惠券不可用、不可领时标签
    fun setCouponUnAvailableTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(6f.dpOfInt, 0, 6f.dpOfInt, 0)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        background = DrawableUtils.createCornerDrawable(
                0f,
                0f,
                9f,
                0f,
                R.color.subMediumGreyColor
        )
    }

    // 超级会员优惠券可用、可领时标签
    fun setCouponSVipTagStyle() {
        height = ScreenUtils.dp2px(context, 16)
        gravity = Gravity.CENTER
        setPadding(6f.dpOfInt, 0, 6f.dpOfInt, 0)
        setTextColor(ContextCompat.getColor(context, R.color.gold_svip_tag_text))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        background = DrawableUtils.createCornerLeftRightDrawable(
                0f,
                0f,
                9f,
                0f,
                ContextCompat.getColor(context, R.color.blackGradientBackgroundFrom),
                ContextCompat.getColor(context, R.color.blackGradientBackgroundTo)
        )
    }

    /**
     * @method setDeliveryStyle
     * @description 履约标签样式
     * @date: 2020/7/17 9:40 AM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    private fun setDeliveryStyle() {
        height = ScreenUtils.dp2px(context, 14)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 2), 0, ScreenUtils.dp2px(context, 2), 0)
        setTextColor(ContextCompat.getColor(context, R.color.subLightBlackColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_unable)
    }

    /**
     * @method setDeliveryCartStyle
     * @description 购物车履约标签样式
     * @date: 2020/7/17 9:51 AM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    private fun setDeliveryCartStyle() {
        height = ScreenUtils.dp2px(context, 14)
        gravity = Gravity.CENTER
        setPadding(ScreenUtils.dp2px(context, 2), 0, ScreenUtils.dp2px(context, 2), 0)
        setTextColor(ContextCompat.getColor(context, R.color.subGreenColor))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
        setBackgroundResource(R.drawable.shape_tag_service)
    }
}
