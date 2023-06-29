package com.quzy.coding.ui.widget

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.widget.roundview.RoundConstraintLayout
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.PayType
import com.quzy.coding.bean.QuickJumpTypeBean
import com.quzy.coding.databinding.LayoutMemberCodePayTypeBinding
import com.quzy.coding.util.ResourceUtil
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.show

/**
 * CreateDate:2023/6/17 14:46
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget
 * @Description:
 */
class MemberCodePayTypeView(context: Context, attrs: AttributeSet?) : RoundConstraintLayout(context, attrs) {
    var viewBinding: LayoutMemberCodePayTypeBinding? = null

    init {
        viewBinding = LayoutMemberCodePayTypeBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.layout_member_code_pay_type, this, true)
        )
    }

    fun bindData(model: QuickJumpTypeBean?, onLine: Boolean, itemClickCallBack: ((QuickJumpTypeBean) -> Unit)? = null) {
        viewBinding?.root?.setTag(R.id.member_code_pay_type_item, model?.payValue)
        Glide.with(context).load(model?.payLogo).into(viewBinding?.payTypeIcon!!)
        viewBinding?.payTypeTitle?.setTextColor(ResourceUtil.getColor(R.color.white))
        viewBinding?.payTypeCodeIcon?.setTextColor(ResourceUtil.getColor(R.color.white))
        viewBinding?.payTypeArrow?.setTextColor(ResourceUtil.getColor(R.color.white))
        viewBinding?.payTypeIcon?.alpha = 1f
        when (model?.payValue ?: "") {
            PayType.PAY_TYPE_YHJR -> {
                viewBinding?.payTypeLL?.setBackgroundColor(parseColor(PayType.PAY_TYPE_YHJR, model?.bgColor))
                if (!onLine) {
                    viewBinding?.payTypeTitle?.setTextColor(ResourceUtil.getColor(R.color.subGreyColor))
                    viewBinding?.payTypeCodeIcon?.setTextColor(ResourceUtil.getColor(R.color.subGreyColor))
                    viewBinding?.payTypeArrow?.setTextColor(ResourceUtil.getColor(R.color.subGreyColor))
                    viewBinding?.payTypeIcon?.alpha = 0.4f
                }
            }

            PayType.PAY_TYPE_WECHAT_DEFAULT -> {
                viewBinding?.payTypeLL?.setBackgroundColor(parseColor(PayType.PAY_TYPE_WECHAT_DEFAULT, model?.bgColor))
            }
            PayType.PAY_TYPE_ALIPAY_DEFAULT -> {
                viewBinding?.payTypeLL?.setBackgroundColor(parseColor(PayType.PAY_TYPE_ALIPAY_DEFAULT, model?.bgColor))
            }
            else -> { }
        }
        model?.let {
            viewBinding?.payTypeIconPromo?.text = it.promoText
            if (TextUtils.isEmpty(it.promoText)) {
                viewBinding?.payTypeIconPromo?.gone()
            } else {
                viewBinding?.payTypeIconPromo?.show()
            }
            viewBinding?.payTypeTitle?.text = it.payName ?: ""
        }

    }

    fun parseColor(patType: String, colorString: String?): Int {
        if (colorString?.isNotEmpty() == true) {
            try {
                return Color.parseColor(colorString)
            } catch (e: Exception) {
            }
        }
        // 兜低处理
        when (patType) {
            PayType.PAY_TYPE_YHJR -> {
                return ContextCompat.getColor(BaseApplication.getContext(), R.color.order_color_eb3223)
            }
            PayType.PAY_TYPE_WECHAT_DEFAULT -> {
                return ContextCompat.getColor(BaseApplication.getContext(), R.color.wechat_coupon_tag_to)
            }
            PayType.PAY_TYPE_ALIPAY_DEFAULT -> {
                return ContextCompat.getColor(BaseApplication.getContext(), R.color.color_1677FF)
            }
            PayType.PAY_YH_CARD -> {
                return ContextCompat.getColor(BaseApplication.getContext(), R.color.order_color_EFB655)
            }
        }
        return ContextCompat.getColor(BaseApplication.getContext(), R.color.order_color_EFB655)
    }
}
