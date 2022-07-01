package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.event.DialogEvent
import com.quzy.coding.databinding.ActivityDrawWithRichtextBinding
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager
import org.greenrobot.eventbus.EventBus

/**
 * CreateDate:2022/7/1 9:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 全局弹窗优惠券的富文本展示
 */
class DrawWithRichTextActivity : BaseActivity() {

    var viewBinding: ActivityDrawWithRichtextBinding ? = null
    override fun onViewCreated() {
        // EventBus.getDefault().post(DialogEvent())
        CouponNewCustomerDialogManager.setCurActivity(this)
        CouponNewCustomerDialogManager.doRequestCoupon(this)
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityDrawWithRichtextBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}
