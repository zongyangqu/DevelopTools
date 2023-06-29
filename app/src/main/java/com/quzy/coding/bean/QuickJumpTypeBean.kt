package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class QuickJumpTypeBean : Parcelable {
    var payLogo: String? = null // logo地址
    var payValue: String? = null // 比如：pay.weixin.app
    var payName: String? = null // 微信/支付宝
    var jumpUrl: String? = null // 微信或支付宝付款码页面的跳转协议地址
    var promoText: String? = null // 角标

    /**
     * 865新增小辉付免密付，需要判断免密付签约状态
     * 2-已签约免密付 已开通免密付
     * 1-已开通 已开通该支付方式 （但未签约免密付）
     * 0-未开通 未开通该支付方式
     */
    var openStatus: Int? = 0 // 是否开通
    var balanceDesc: String? = null
    var payType: Int? = null // 支付类型
    var popup: QuickJumpPopupVo? = null // 弹窗
    var bgColor: String? = null // 背景色 870新增
}

/**
 * 弹窗
 */
@Parcelize
class QuickJumpPopupVo : Parcelable {
    var title: String? = null // 主标题
    var popupDesc: String? = null // 弹层描述
    var btnList: MutableList<QuickJumpPopupBtnVo>? = null // 按钮列表
}

@Parcelize
class QuickJumpPopupBtnVo : Parcelable {
    var btnText: String? = null // 按钮文案

    /**
     * 特殊action:
     *  1. openThirdPay  点击跳转至三方支付的开通页面
     *  2. buyCard  点击后关闭弹层，唤起购卡引导；
     */
    var btnJump: String? = null // 按钮跳转
    var btnColor: String? = null // 按钮颜色
    companion object {
        const val JUMP_OPEN_THIRD_PAY = "openThirdPay"
        const val JUMP_BUY_CARD = "buyCard"
    }
}
