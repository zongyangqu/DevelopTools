package com.quzy.coding.ui.manager

import androidx.appcompat.app.AppCompatActivity
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.ui.widget.CouponNewCustomerUnitaryDialog
import com.quzy.coding.util.JsonUtils
import java.lang.ref.WeakReference

/**
 * CreateDate:2022/7/1 9:44
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.manager
 * @Description: 全局弹窗的管理类
 */
class CouponNewCustomerDialogManager {
    companion object {
        var mActivityWeakReference: WeakReference<AppCompatActivity>? = null

        fun doRequestCoupon(activity: AppCompatActivity?) {
            // 不是最上层的activty就不请求接口也不弹窗
            if (mActivityWeakReference?.get() != null && activity != mActivityWeakReference?.get()) {
                return
            }
            // 如果是真实项目 这里可以进行网络请求  来获取是否展示 优惠券弹窗
            // doNetRequest
            val metaData = JsonUtils.analysisNewCouponsJsonFile(BaseApplication.getContext(), "coupon")
            val dialog = CouponNewCustomerUnitaryDialog()
            dialog.setData(metaData)
            dialog.show(mActivityWeakReference?.get()?.supportFragmentManager ?: return, "")
            mActivityWeakReference = null
        }

        fun setCurActivity(activity: AppCompatActivity?) {
            mActivityWeakReference = WeakReference(activity)
        }
    }
}
