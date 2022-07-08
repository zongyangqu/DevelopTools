package com.quzy.coding.ui.holder

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.quzy.coding.R
import com.quzy.coding.bean.CouponMineDataBean
import com.quzy.coding.databinding.NewcustomterCouponItemLayoutBinding
import com.quzy.coding.util.CouponArgEdgeTreatment

/**
 * CreateDate:2022/6/23 16:47
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.lib.style.newcustomer
 * @Description: 新人弹窗 优惠券部分   870需求
 */
class NewCustomerCouponHolder(
    var layout: View,
    var context: Context
) : RecyclerView.ViewHolder(layout) {

    val viewBinding by lazy {
        NewcustomterCouponItemLayoutBinding.bind(layout)
    }

    fun bindData(mData: CouponMineDataBean?) {
        val shapePathModel = ShapeAppearanceModel
            .Builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(9f.dp)
            .setTopEdge(CouponArgEdgeTreatment(57f.dp, 4f.dp, true))
            .setBottomEdge(CouponArgEdgeTreatment(57f.dp, 4f.dp, false))
            .build()
        val drawable = MaterialShapeDrawable(shapePathModel)
        drawable.setTint(ContextCompat.getColor(itemView.context, R.color.white))
        viewBinding?.clCouponContentContainer.background = drawable
        if (TextUtils.isEmpty(mData?.expirationDesc)) {
            viewBinding?.tvCouponDate.visibility = View.GONE
        } else {
            viewBinding?.tvCouponDate.visibility = View.VISIBLE
            viewBinding?.tvCouponDate.text = mData?.expirationDesc
        }
        handleCouponContent(mData)
    }

    fun handleCouponContent(bean: CouponMineDataBean?) {
        viewBinding?.llCouponTitleContainer.setPadding(0, 0, 0, 0)
        val contentStr: String
        val spannableString = SpannableStringBuilder()

        if (bean?.catalog == 6 && !bean?.conditiondesc.isNullOrEmpty()) { // 折扣券
            contentStr = TextUtils.substring(
                bean?.conditiondesc,
                0,
                if (bean?.conditiondesc.indexOf("折") > -1) bean?.conditiondesc.indexOf("折") + 1 else bean?.conditiondesc.length
            )
            spannableString.append(contentStr)
            spannableString.setSpan(
                AbsoluteSizeSpan(UIUtils.sp2px(itemView.context, 12f)),
                0,
                contentStr.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            spannableString.setSpan(
                AbsoluteSizeSpan(UIUtils.sp2px(itemView.context, 20f)),
                0,
                if (contentStr.length > 1) contentStr.length - 1 else contentStr.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            viewBinding?.tvCouponContent?.text = spannableString
        } else {
            if (bean?.amount ?: 0 > 0) {
                val title =
                    centToYuanDeleteZeroUnitString(itemView.context, bean?.amount ?: 0)
                contentStr = title.toString()
                spannableString.append(contentStr)
                spannableString.setSpan(
                    AbsoluteSizeSpan(UIUtils.sp2px(itemView.context, 10f)),
                    0,
                    1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                spannableString.setSpan(
                    AbsoluteSizeSpan(UIUtils.sp2px(itemView.context, 20f)),
                    1,
                    title!!.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    title.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                viewBinding?.tvCouponContent?.text = spannableString
            } else {
                contentStr = bean?.conditiondesc ?: ""
                spannableString.append(contentStr)
                spannableString.setSpan(
                    if (contentStr.length > 2) AbsoluteSizeSpan(
                        UIUtils.sp2px(
                            itemView.context,
                            14f
                        )
                    ) else AbsoluteSizeSpan(UIUtils.sp2px(itemView.context, 18f)),
                    0,
                    contentStr.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )

                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    contentStr.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                viewBinding?.tvCouponContent?.text = spannableString
            }
        }
    }

    fun setBottomMargin(bottomMarginDp: Float) {
        val lp = viewBinding?.clCouponContainer.layoutParams
        if (lp is RecyclerView.LayoutParams) {
            lp.bottomMargin = bottomMarginDp.dpOfInt
        }
    }

    fun centToYuanDeleteZeroUnitString(context: Context, cents: Int): String? {
        var result = (cents / 100.0).toString()
        if (result.indexOf(".") > 0) {
            result = result.replace("0+?$".toRegex(), "") //去掉后面无用的零
            result = result.replace("[.]$".toRegex(), "") //如小数点后面全是零则去掉小数点
        }
        return context.getString(R.string.format_yuan_string, result)
    }
}
