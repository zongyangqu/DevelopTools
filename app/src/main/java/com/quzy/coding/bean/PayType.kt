package com.quzy.coding.bean

/**
 * CreateDate:2023/6/17 14:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
object PayType {
    const val PAY_TYPE_BALANCE = "pay.yonghui.balance"
    const val PAY_TYPE_WECHAT_DEFAULT = "pay.weixin.app"
    const val PAY_TYPE_WECHAT = "pay.weixin"
    const val PAY_TYPE_ALIPAY_DEFAULT = "pay.alipay.app"
    const val PAY_TYPE_ALIPAY = "pay.alipay"
    const val PAY_TYPE_ALIPAY_SIGN_AND_PAY = "pay.alipay.pap.app.pay.and.sign"
    const val PAY_TYPE_ALIPAY_WITHOUT_PWD_PAY = "pay.alipay.pap.app"
    const val PAY_TYPE_YHJR = "pay.xhpay.app.pay"
    const val PAY_TYPE_JD = "pay.jd.pay"
    const val PAY_TYPE_UNPAY = "pay.unionpay.app" // 银联支付Key
    const val PAY_YH_CARD = "pay.yhcard.yhapp"
    const val PAY_TYPE_XH_QUICK = "pay.xhpay.qp.app.pay"
    const val PAY_TYPE_WX_GLOBAL_PAY = "pay.swiftpass.global.wx.app"
    const val PAY_TYPE_ALI_GLOBAL_PAY = "pay.aliglobal.app.pay"
    // 中行数字货币
    const val PAY_TYPE_DCEP_PAY = "pay.dcep.boc.app" // 数字币H5页面支付
    const val PAY_TYPE_DCEP_PAY_APP = "pay.dcep.pap.app" // 数字币子钱包免密支付
    const val PAY_TYPE_DCEP_APP = "pay.dcep.app.pay.and.sign" // 数字币唤起APP支付

    const val PAY_TYPE_CMB = "pay.cmb.app" // 招行一网通支付
    const val PAY_TYPE_CMB_WITHOUT_PWD_PAY = "pay.cmb.pap.app" // 招行一网通免密支付
}